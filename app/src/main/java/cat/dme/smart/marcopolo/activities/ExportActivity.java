package cat.dme.smart.marcopolo.activities;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.Manifest;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.business.impl.ExpenseBOImpl;
import cat.dme.smart.marcopolo.business.impl.TripBOImpl;
import cat.dme.smart.marcopolo.model.Expense;
import cat.dme.smart.marcopolo.model.TripConfig;


/**
 * Created by str_dme on 25/07/14.
 */
public class ExportActivity extends BaseMenuActivity {

    private static final String LINEA_END = "\n";
    private static final String SEPARATOR = ";";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        this.configureToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Long selectedTripId = this.getMyApplication().getCurrentTripId();
        TripConfig tripConfig = TripBOImpl.getInstance(null).getTrip(selectedTripId);
        List<Expense> expenses = ExpenseBOImpl.getInstance(null).getByTrip(selectedTripId);
        tripConfig.setExpenses(expenses);

        // File Name
        StringBuffer filename = new StringBuffer("TripExport-");
        filename.append(tripConfig.getTrip().get_id());
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("-yyyyMMdd-HHmm");
        filename.append(sdf.format(now));

	    //JSON
        Gson gson = new Gson();
        String jsonTripConfig = gson.toJson(tripConfig);
        String jsonPath = null;
        try {
            jsonPath = this.objectToFile(jsonTripConfig, filename.toString().concat(".json"));
        } catch (Exception e) {
            e.printStackTrace();
            jsonPath = "No se ha podido crear JSON... " + e.toString();
        }
	
	    //CSV
	    String csvTrip = this.generateCSV(tripConfig); 
	    String csvPath = null;
        try {
            csvPath = this.objectToFile(csvTrip, filename.toString().concat(".csv"));
        } catch (Exception e) {
            e.printStackTrace();
            csvPath = "No se ha podido crear CSV... " + e.toString();
        }

        TextView tvExport = (TextView) this.findViewById(R.id.textExport);
        tvExport.setText("Export JSON to: " + jsonPath + " and CSV to: " + csvPath);
    }

    public String generateCSV(final TripConfig tripConfig) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE dd-MM-yyyy HH:mm");
        DecimalFormat df = new DecimalFormat("#,##");
        StringBuffer csv = new StringBuffer();
        csv.append(tripConfig.getTrip().getDestination()).append(LINEA_END).append(LINEA_END);
        //private Date startDate;
        //private Date endDate;
        //private String description;
        //private TripStatus status;


        csv.append("Date").append(SEPARATOR);
        csv.append("Concept").append(SEPARATOR);
        csv.append("Description").append(SEPARATOR);
        csv.append("Amount").append(SEPARATOR);
        csv.append("Currency").append(SEPARATOR);
        csv.append("Payer").append(SEPARATOR);
        csv.append("PaymentMethod").append(LINEA_END);
        
        for(Expense expense: tripConfig.getExpenses()) {
            csv.append(sdf.format(expense.getDate())).append(SEPARATOR);
            csv.append(expense.getConcept().getName()).append(SEPARATOR);
            csv.append(expense.getDescription()).append(SEPARATOR);
            //csv.append(df.format(expense.getAmount())).append(SEPARATOR);
            csv.append(NumberFormat.getInstance().format(expense.getAmount())).append(SEPARATOR);
            csv.append(expense.getCurrency().getName()).append(SEPARATOR);
            csv.append(expense.getPayer().getName()).append(SEPARATOR);
            csv.append(expense.getPaymentMethod().getName()).append(LINEA_END);
        }
        return csv.toString();
    }

	public  boolean isStoragePermissionGranted() {
	    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
		    if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
		        return true;
		    } else {
		        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
		        return true;
		    }
	    } else { //permission is automatically granted on sdk<23 upon installation
		    return true;
	    }
    }

    public String objectToFile(String fileContent, String filename) {

        String path = Environment.getExternalStorageDirectory() + File.separator + "Marcopolo" + File.separator + "Export" + File.separator;

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        path += filename;
        File data = new File(path);
        if (data.exists()) {
            data.delete();
        } 
	//File data = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), filename);
        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter(data));
            output.write(fileContent);
        } catch (IOException e) {
            return "No se ha podido exportar... " + e.toString(); // + fileContent; //TODO:
        } finally {
            if(output!=null) {
                try {
                    output.close();
                } catch (IOException ioe) {
                    //NOthing
                }
            }
        }
	    return data.getAbsolutePath();
    }

    public static Object objectFromFile(String path) throws IOException, ClassNotFoundException {
        Object object = null;
        File data = new File(path);
        if(data.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(data));
            object = objectInputStream.readObject();
            objectInputStream.close();
        }
        return object;
    }
}


package cat.dme.smart.marcopolo.activities;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Writer;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        this.configureToolbar();
        Long selectedTripId = this.getMyApplication().getCurrentTripId();
        TripConfig tripConfig = TripBOImpl.getInstance(null).getTrip(selectedTripId);
        List<Expense> expenses = ExpenseBOImpl.getInstance(null).getByTrip(selectedTripId);
        tripConfig.setExpenses(expenses);

        Gson gson = new Gson();
        String jsonTripConfig = gson.toJson(tripConfig);

        StringBuffer filename = new StringBuffer("TripExport-");
        filename.append(tripConfig.getTrip().get_id());
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("-yyyyMMdd-HHmm");
        filename.append(sdf.format(now)).append(".txt");
        String path = null;
        try {
            path = this.objectToFile(jsonTripConfig, filename.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //String result = path.concat(jsonTripConfig);

        TextView tvExport = (TextView) this.findViewById(R.id.textExport);
        tvExport.setText("Export to: " + path);
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
        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter(data));
            output.write(fileContent);
        } catch (IOException e) {
            return "NO seha podido exportar"; //TODO:
        } finally {
            if(output!=null) {
                try {
                    output.close();
                } catch (IOException ioe) {
                    //NOthing
                }
            }
        }
        return path;
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

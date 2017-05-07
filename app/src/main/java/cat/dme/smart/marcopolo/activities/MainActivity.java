package cat.dme.smart.marcopolo.activities;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.dao.impl.DbHelper;
import cat.dme.smart.marcopolo.dao.impl.TripDaoImpl;
import cat.dme.smart.marcopolo.fragments.trip.dialog.CreateFirstTripFragment;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Activity that shows main view: It will be a initial dashboard of the active trip.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class MainActivity extends BaseMenuActivity { //implements NoticeDialogFragment.NoticeDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureToolbar();

        Context appContext = this.getApplicationContext();

        //init db
        DbHelper.initDbHelper(appContext);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Load currentTripId
        Long currentTripId = this.getMyApplication().getCurrentTripId();

        String destination;
        String description;
        if(currentTripId>0) {
            Trip currentTrip = TripDaoImpl.getInstance().get(currentTripId);
            if(currentTrip==null) {
                this.getMyApplication().removeCurrentTripId();
                //this.showNoticeDialog();
                return;
            }
            destination = currentTrip.getDestination();
            description = currentTrip.getDescription();
        } else {
            //this.showNoticeDialog();
            destination = this.getString(R.string.main_not_destination);
            description = "";
        }

        TextView tvCurrentTripDestination = (TextView) findViewById(R.id.current_trip_destination);
        tvCurrentTripDestination.setText(destination);

        TextView tvCurrentTripDescription = (TextView) findViewById(R.id.current_trip_description);
        tvCurrentTripDescription.setText(description);

    }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new CreateFirstTripFragment();
        dialog.show(this.getFragmentManager(), "CreateFirstTripDialogFragment");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    //@Override
    public void onDialogPositiveClick(DialogFragment dialog) {

    }

    //@Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

}

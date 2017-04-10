package cat.dme.smart.marcopolo.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.dao.TripDao;
import cat.dme.smart.marcopolo.dao.impl.DbHelper;
import cat.dme.smart.marcopolo.dao.impl.TripDaoImpl;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Activity that shows main view: It will be a initial dashboard of the active trip.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class MainActivity extends BaseMenuActivity {

    public TripDao tripDao;

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
            destination = currentTrip.getDestination();
            description = currentTrip.getDescription();
        } else {
            destination = this.getString(R.string.main_not_destination);
            description = "";
        }

        TextView tvCurrentTripDestination = (TextView) findViewById(R.id.current_trip_destination);
        tvCurrentTripDestination.setText(destination);

        TextView tvCurrentTripDescription = (TextView) findViewById(R.id.current_trip_description);
        tvCurrentTripDescription.setText(description);

    }

}

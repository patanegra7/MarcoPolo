package cat.dme.smart.marcopolo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cat.dme.smart.marcopolo.dao.impl.TripDaoImpl;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Activity to manage a trip configuration. It delegates in different fragments: currency, concepts, pay method, ...
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class TripActivity extends BaseMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        this.configureToolbar();

        Trip currentTrip = (Trip) this.getIntent().getSerializableExtra(this.getString(R.string.global_current_trip));
        this.getMyApplication().setCurrentTripId(currentTrip.get_id());
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

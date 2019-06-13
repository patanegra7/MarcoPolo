/**
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Trip;
import cat.dme.smart.marcopolo.viewmodel.TripViewModel;

/**
 * Activity that shows main view: It will be a initial dashboard of the active trip.
 */
public class MainActivity extends BaseMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Load currentTripId
        Long currentTripId = this.getMyApplication().getCurrentTripId();

        String destination;
        String description;
        if(currentTripId>0) {
            TripViewModel tripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);
            Trip currentTrip = tripViewModel.getTrip(currentTripId);

            if(currentTrip==null) {
                this.getMyApplication().removeCurrentTripId();
                this.getMyApplication().removeCurrentTripDestination();
                return;
            }
            destination = currentTrip.getDestination();
            description = currentTrip.getDescription();
        } else {
            destination = this.getString(R.string.main_not_destination);
            description = "";
        }

        TextView tvCurrentTripDestination = this.findViewById(R.id.current_trip_destination);
        tvCurrentTripDestination.setText(destination);

        TextView tvCurrentTripDescription = this.findViewById(R.id.current_trip_description);
        tvCurrentTripDescription.setText(description);

    }

}

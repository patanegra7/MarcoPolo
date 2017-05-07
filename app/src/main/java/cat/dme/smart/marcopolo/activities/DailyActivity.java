package cat.dme.smart.marcopolo.activities;

import android.os.Bundle;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.fragments.daily.DailyFragment;

/**
 * Activity that shows a list of selected trip expenses.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class DailyActivity extends BaseMenuActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        this.configureToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Retrieves current trip
        //Long currentTripId = this.getIntent().getLongExtra(this.getString(R.string.global_current_trip_id), 0);
        //Trip currentTrip = TripDaoImpl.getInstance().get(currentTripId);

        //Loads  selected TripId
        Long selectedTripId = this.getMyApplication().getCurrentTripId();

        //Changes general trip data fragment.
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_daily, DailyFragment.newInstance(selectedTripId)).commit();
    }

    /*

        btnCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(intent);
            }
        });*/
}

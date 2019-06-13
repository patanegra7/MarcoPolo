/**
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Trip;
import cat.dme.smart.marcopolo.ui.TripRelativeAdapter;
import cat.dme.smart.marcopolo.viewmodel.TripViewModel;

/**
 * Activity that shows a list of trips, to configure them and to select witch one is the active.
 */
public class SettingsActivity extends BaseMenuActivity implements TripRelativeAdapter.OnTripListener {

    private TripViewModel tripViewModel;
    private TripRelativeAdapter tripRelativeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.configureToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Long currentTripId = this.getMyApplication().getCurrentTripId();
        this.tripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);
        this.tripRelativeAdapter = new TripRelativeAdapter(this, currentTripId);
        this.tripViewModel.getTrips().observe(this, tripRelativeAdapter::setData);

        RecyclerView recyclerView = this.findViewById(R.id.trip_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(this.tripRelativeAdapter);

        FloatingActionButton fab = this.findViewById(R.id.fab_add_trip);
        fab.setOnClickListener((View view)-> {
            startActivity(EditTripActivity.runActivity(view.getContext()));
        });
    }

    public void onTripClick(int position) {
        Trip itemValue = this.tripViewModel.getTrips().getValue().get(position);
        this.startActivity(TripActivity.runActivity(this, itemValue.getId()));
    }

    public static Intent runActivity(Context context) {
        return new Intent(context, SettingsActivity.class);
    }
}

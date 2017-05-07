package cat.dme.smart.marcopolo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.dao.TripDao;
import cat.dme.smart.marcopolo.dao.impl.TripDaoImpl;
import cat.dme.smart.marcopolo.adapters.TripArrayAdapter;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Activity that shows a list of trips, to configure them and to select witch one is the active.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class SettingsActivity extends BaseMenuActivity {

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
        TripDao tripDao = TripDaoImpl.getInstance();

        // Get ListView object from xml
        final ListView listView = (ListView) findViewById(R.id.trip_list);

        ArrayAdapter<Trip> adapter = new TripArrayAdapter(this, tripDao.getAll(), currentTripId);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Trip itemValue = (Trip) listView.getItemAtPosition(position);
                startActivity(TripActivity.runActivity(view.getContext(), itemValue.get_id()));
            }

        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_trip);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(EditTripActivity.runActivity(view.getContext()));
            }
        });
    }

    public static Intent runActivity(Context context) {
        return new Intent(context, SettingsActivity.class);
    }
}

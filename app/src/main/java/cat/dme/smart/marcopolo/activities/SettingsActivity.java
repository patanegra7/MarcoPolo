package cat.dme.smart.marcopolo.activities;

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

    ListView listView;
    TripDao tripDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.configureToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Long currentTripId = (Long)this.getIntent().getSerializableExtra(this.getString(R.string.global_current_trip_id));
        Long currentTripId = this.getMyApplication().getCurrentTripId();
        tripDao = TripDaoImpl.getInstance();

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        ArrayAdapter<Trip> adapter = new TripArrayAdapter(this, tripDao.getAll(), currentTripId);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                Trip itemValue = (Trip) listView.getItemAtPosition(position);
                // Show Alert
                //Snackbar.make(view, "Position :" + itemPosition + "  ListItem : " + itemValue, Snackbar.LENGTH_LONG).setAction("Action", null).show();

                Intent intent = new Intent(view.getContext(),TripActivity.class);
                intent.putExtra(view.getContext().getString(R.string.global_current_trip), itemValue);
                startActivity(intent);
            }

        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TripActivity.class);
                startActivity(intent);
            }
        });
    }
}

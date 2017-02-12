package cat.dme.smart.marcopolo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import cat.dme.smart.marcopolo.dao.TripDao;
import cat.dme.smart.marcopolo.dao.impl.TripDaoImpl;
import cat.dme.smart.marcopolo.list.TripArrayAdapter;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Created by str_dme on 25/07/14.
 */
public class SettingsActivity extends BaseMenuActivity {

    ListView listView;
    TripDao tripDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.configureToolbar();

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        tripDao = new TripDaoImpl();

        ArrayAdapter<Trip> adapter = new TripArrayAdapter(this, tripDao.getAll());

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                Trip itemValue = (Trip) listView.getItemAtPosition(position);
                // Show Alert
                Snackbar.make(view, "Position :" + itemPosition + "  ListItem : " + itemValue, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

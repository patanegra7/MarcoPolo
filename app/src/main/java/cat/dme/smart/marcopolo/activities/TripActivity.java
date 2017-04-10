package cat.dme.smart.marcopolo.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.adapters.TripPagerAdapter;
import cat.dme.smart.marcopolo.dao.impl.TripDaoImpl;
import cat.dme.smart.marcopolo.fragments.trip.TripFragment;
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


        //if(currentTrip!=null) { // Lo seleccionamos -- Igual no es la manera
        //    this.getMyApplication().setCurrentTripId(currentTrip.get_id());
        //}

        //Load currentTripId
        Long selectedTripId = this.getMyApplication().getCurrentTripId();

        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_trip, TripFragment.newInstance(currentTrip, selectedTripId)).commit();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_currency));
                //Text(this.getString(R.string.trip_currency)));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_payer));
                //Text(this.getString(R.string.trip_payer)));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_concept));
                //Text(this.getString(R.string.trip_concept)));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_payment_method));
                //Text(this.getString(R.string.trip_payment_method)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final TripPagerAdapter adapter = new TripPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


/*
        Trip currentTrip = (Trip) this.getIntent().getSerializableExtra(this.getString(R.string.global_current_trip));


        //if(currentTrip!=null) { // Lo seleccionamos -- Igual no es la manera
        //    this.getMyApplication().setCurrentTripId(currentTrip.get_id());
        //}

        //Load currentTripId
        Long selectedTripId = this.getMyApplication().getCurrentTripId();

        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_trip, TripFragment.newInstance(currentTrip, selectedTripId)).commit();
*/
        /*
        String destination;
        String description;
        if(currentTrip!=null) {
            //Trip currentTrip = TripDaoImpl.getInstance().get(currentTripId);
            destination = currentTrip.getDestination();
            description = currentTrip.getDescription();

            TextView tvCurrentTripDestination = (TextView) findViewById(R.id.current_trip_destination);
            tvCurrentTripDestination.setText(destination);

            TextView tvCurrentTripDescription = (TextView) findViewById(R.id.current_trip_description);
            tvCurrentTripDescription.setText(description);

            CheckBox tvSelected = (CheckBox) findViewById(R.id.current_trip_selected);
            if(selectedTripId!=null && selectedTripId.equals(currentTrip.get_id())) {
                tvSelected.setChecked(true);
            } else {
                tvSelected.setChecked(false);
            }

        } */
    }


}


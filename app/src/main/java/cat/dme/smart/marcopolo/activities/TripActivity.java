package cat.dme.smart.marcopolo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

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

        // Retrieves current trip
        Long currentTripId = this.getIntent().getLongExtra(this.getString(R.string.global_current_trip_id), 0);

        //Creates tabs titles
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

        //Creates tabs content
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final TripPagerAdapter adapter = new TripPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), currentTripId);
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
        // Retrieves current trip
        Long currentTripId = this.getIntent().getLongExtra(this.getString(R.string.global_current_trip_id), 0);
        Trip currentTrip = TripDaoImpl.getInstance().get(currentTripId);

        //Loads  selected TripId
        Long selectedTripId = this.getMyApplication().getCurrentTripId();

        //Changes general trip data fragment.
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_trip, TripFragment.newInstance(currentTrip, selectedTripId)).commit();
    }

    public static Intent runActivity(Context context) {
        return new Intent(context, TripActivity.class);
    }

    public static Intent runActivity(Context context, Long tripId) {
        Intent intent = new Intent(context, TripActivity.class);
        intent.putExtra(context.getString(R.string.global_current_trip_id), tripId);
        return intent;
    }

}


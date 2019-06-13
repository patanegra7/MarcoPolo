/**
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.fragment.trip.TripFragment;
import cat.dme.smart.marcopolo.model.Trip;
import cat.dme.smart.marcopolo.viewmodel.TripViewModel;

/**
 * Activity to manage a trip configuration. It delegates in different fragments: currency, concepts, pay method, ...
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
        TabLayout tabLayout = this.findViewById(R.id.trip_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(this.getString(R.string.trip_currency))); // Icon(R.drawable.ic_currency));
        tabLayout.addTab(tabLayout.newTab().setText(this.getString(R.string.trip_payer))); //Icon(R.drawable.ic_payer));
        tabLayout.addTab(tabLayout.newTab().setText(this.getString(R.string.trip_concept))); //Icon(R.drawable.ic_concept));
        tabLayout.addTab(tabLayout.newTab().setText(this.getString(R.string.trip_payment_method)));  //Icon(R.drawable.ic_payment_method));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Creates tabs content
        final ViewPager viewPager = this.findViewById(R.id.trip_pager);
   //     final TripPagerAdapter adapter = new TripPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), currentTripId);
   //     viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //viewPager.setCurrentItem(tab.getPosition());
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
        //Trip currentTrip = TripDaoImpl.getInstance().get(currentTripId);
        TripViewModel tripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);

        Trip currentTrip = tripViewModel.getTrip(currentTripId);

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


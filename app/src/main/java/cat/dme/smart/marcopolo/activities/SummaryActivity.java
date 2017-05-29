package cat.dme.smart.marcopolo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.adapters.SummaryPagerAdapter;

/**
 * Activity to manage a trip's statistic. It delegates in different fragments: currency, concepts, pay method, ...
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class SummaryActivity extends BaseMenuActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        this.configureToolbar();

        // Retrieves current trip
        Long currentTripId = this.getMyApplication().getCurrentTripId();

        //Creates tabs titles
        TabLayout tabLayout = (TabLayout) findViewById(R.id.summary_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(this.getString(R.string.trip_currency))); // Icon(R.drawable.ic_currency));
        tabLayout.addTab(tabLayout.newTab().setText(this.getString(R.string.trip_payer))); //Icon(R.drawable.ic_payer));
        tabLayout.addTab(tabLayout.newTab().setText(this.getString(R.string.trip_concept))); //Icon(R.drawable.ic_concept));
        tabLayout.addTab(tabLayout.newTab().setText(this.getString(R.string.trip_payment_method)));  //Icon(R.drawable.ic_payment_method));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Creates tabs content
        final ViewPager viewPager = (ViewPager) findViewById(R.id.summary_pager);
        final SummaryPagerAdapter adapter = new SummaryPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), currentTripId);
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
    }

    public static Intent runActivity(Context context) {
        return new Intent(context, SummaryActivity.class);
    }

}

package cat.dme.smart.marcopolo.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.adapters.SampleFragmentPagerAdapter;

/**
 * Created by str_dme on 25/07/14.
 */
public class SummaryActivity extends BaseMenuActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        this.configureToolbar();

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                SummaryActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
/**
    @Override
    protected void onResume() {
        super.onResume();

        //Loads  selected TripId
        Long selectedTripId = this.getMyApplication().getCurrentTripId();

        //Changes general trip data fragment.
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_daily, DailyFragment.newInstance(selectedTripId)).commit();
    }
    **/
}

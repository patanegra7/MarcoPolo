package cat.dme.smart.marcopolo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cat.dme.smart.marcopolo.fragments.summary.SummaryConceptFragment;
import cat.dme.smart.marcopolo.fragments.summary.SummaryCurrencyFragment;
import cat.dme.smart.marcopolo.fragments.summary.SummaryPayerFragment;
import cat.dme.smart.marcopolo.fragments.summary.SummaryPaymentMethodFragment;

/**
 * TODO: Explain class
 * Created by VIddA Software - DME Creaciones.
 */

public class SummaryPagerAdapter extends FragmentPagerAdapter {
    private int mNumOfTabs;
    private Long tripId;


    public SummaryPagerAdapter(FragmentManager fm, int NumOfTabs, Long tripId) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.tripId = tripId;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return SummaryCurrencyFragment.newInstance(tripId);
            case 1:
                return SummaryPayerFragment.newInstance(tripId);
            case 2:
                return SummaryConceptFragment.newInstance(tripId);
            case 3:
                return SummaryPaymentMethodFragment.newInstance(tripId);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

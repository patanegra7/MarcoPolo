package cat.dme.smart.marcopolo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cat.dme.smart.marcopolo.fragments.trip.ConceptFragment;
import cat.dme.smart.marcopolo.fragments.trip.CurrencyFragment;
import cat.dme.smart.marcopolo.fragments.trip.PayerFragment;
import cat.dme.smart.marcopolo.fragments.trip.PaymentMethodFragment;

/**
 * TODO: Explain class
 * Created by VIddA Software - DME Creaciones.
 */

public class TripPagerAdapter extends FragmentPagerAdapter {
    private int mNumOfTabs;
    private Long tripId;


    public TripPagerAdapter(FragmentManager fm, int NumOfTabs, Long tripId) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.tripId = tripId;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return  CurrencyFragment.newInstance(tripId);
            case 1:
                return PayerFragment.newInstance(tripId);
            case 2:
                return ConceptFragment.newInstance(tripId);
            case 3:
                return PaymentMethodFragment.newInstance(tripId);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

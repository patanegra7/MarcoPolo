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
                CurrencyFragment tab1 = CurrencyFragment.newInstance(tripId);
                return tab1;
            case 1:
                PayerFragment tab2 = PayerFragment.newInstance(tripId);
                return tab2;
            case 2:
                ConceptFragment tab3 = ConceptFragment.newInstance(tripId);
                return tab3;
            case 3:
                PaymentMethodFragment tab4 = PaymentMethodFragment.newInstance(tripId);
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

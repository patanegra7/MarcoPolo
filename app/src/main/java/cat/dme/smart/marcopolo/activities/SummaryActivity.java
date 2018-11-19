/*
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.activities;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.math.BigDecimal;
import java.util.Map;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.adapters.SummaryPagerAdapter;
import cat.dme.smart.marcopolo.fragments.summary.SummaryConceptFragment;
import cat.dme.smart.marcopolo.fragments.summary.SummaryPayerFragment;
import cat.dme.smart.marcopolo.fragments.summary.SummaryPaymentMethodFragment;
import cat.dme.smart.marcopolo.fragments.summary.SummaryTotalFragment;
import cat.dme.smart.marcopolo.fragments.summary.dialog.ConceptPieCharDialogFragment;
import cat.dme.smart.marcopolo.fragments.summary.dialog.PayerPieCharDialogFragment;
import cat.dme.smart.marcopolo.fragments.summary.dialog.PaymentMethodPieCharDialogFragment;
import cat.dme.smart.marcopolo.model.Currency;

/**
 * Activity to manage a trip's statistic. It delegates in different fragments: currency, concepts, pay method, ...
*/
public class SummaryActivity extends BaseMenuActivity implements //SummaryCurrencyFragment.OnSummaryCurrencyFragmentListener,
                                                                 SummaryConceptFragment.OnSummaryConceptFragmentListener,
                                                                 SummaryPayerFragment.OnSummaryPayerFragmentListener,
                                                                 SummaryPaymentMethodFragment.OnSummaryPaymentMethodFragmentListener
{
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

    @Override
    public void onConceptChartShow(Map<String, Float> percentages, Currency currency) {
        DialogFragment dialog = ConceptPieCharDialogFragment.newInstance(percentages, currency);
        dialog.show(this.getFragmentManager(), getString(R.string.summary_concept_percentages_chart_title));
    }

    @Override
    public void onUpdateConceptTotals(Map<Currency, BigDecimal> totals) {
        SummaryTotalFragment totalFragment = (SummaryTotalFragment)this.getSupportFragmentManager().findFragmentById(R.id.fragment_summary_total);
        if (totalFragment != null) {
            // If article frag is available, we're in two-pane layout...
            // Call a method in the ArticleFragment to update its content
            totalFragment.updateTotals(totals);
        } else {
            // Otherwise, we're in the one-pane layout and must swap frags...
            this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_summary_total, SummaryTotalFragment.newInstance(totals)).commit();
        }

    }

    @Override
    public void onPayerChartShow(Map<String, Float> percentages, Currency currency) {
        DialogFragment dialog = PayerPieCharDialogFragment.newInstance(percentages, currency);
        dialog.show(this.getFragmentManager(), getString(R.string.summary_payer_percentages_chart_title));
    }

    @Override
    public void onPaymentMethodChartShow(Map<String, Float> percentages, Currency currency) {
        DialogFragment dialog = PaymentMethodPieCharDialogFragment.newInstance(percentages, currency);
        dialog.show(this.getFragmentManager(), getString(R.string.summary_payment_method_percentages_chart_title));
    }
}

package cat.dme.smart.marcopolo.fragments.summary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Currency;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * link CurrencyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SummaryTotalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SummaryTotalFragment extends Fragment {

    private static final String ARG_PARAM1 = "totals";

    //private Long currentTripId;
    private Map<Currency, BigDecimal> totals;

    //private OnSummaryConceptFragmentListener mListener;

    /**
     * Default constructor
     */
    public SummaryTotalFragment() {
        super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param totals A map with total for each currency.
     * @return A new instance of fragment CurrencyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SummaryTotalFragment newInstance(Map<Currency, BigDecimal> totals) {
        SummaryTotalFragment fragment = new SummaryTotalFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) totals);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.totals = (Map<Currency, BigDecimal>)this.getArguments().get(ARG_PARAM1);
        }
    }
    //  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary_total, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.updateContent();
    }

    public void updateTotals(Map<Currency, BigDecimal> totals){
        this.totals = totals;
        this.updateContent();
    }

    /**
     * Updates content.
     */
    private void updateContent() {
        // Getting the fragment layout
        LinearLayout totalFragmentLayout = (LinearLayout)this.getView().findViewById(R.id.summary_total_layout);
        totalFragmentLayout.removeAllViews();
        // Adding/Updating total info to the fragment
        for(Currency currency: this.totals.keySet()) {
            TextView tvTotal = (TextView) totalFragmentLayout.findViewWithTag("total_concept_" + currency.getName());
            if(tvTotal==null) {
                tvTotal = new TextView(this.getContext());
                tvTotal.setTag("total_concept_" + currency.getName());
                tvTotal.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                totalFragmentLayout.addView(tvTotal);
            }
            tvTotal.setText(this.totals.get(currency).toString() + " " + currency.getSymbol());
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

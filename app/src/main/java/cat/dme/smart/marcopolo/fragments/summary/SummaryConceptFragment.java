package cat.dme.smart.marcopolo.fragments.summary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.adapters.SummaryConceptArrayAdapter;
import cat.dme.smart.marcopolo.business.ExpenseBO;
import cat.dme.smart.marcopolo.business.impl.ExpenseBOImpl;
import cat.dme.smart.marcopolo.model.Currency;
import cat.dme.smart.marcopolo.model.Expense;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * link CurrencyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SummaryConceptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SummaryConceptFragment extends Fragment {

    private static final String ARG_PARAM1 = "currentTripId";

    private Long currentTripId;

    private OnSummaryConceptFragmentListener mListener;

    /**
     * Default constructor
     */
    public SummaryConceptFragment() {
        super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param currentTripId Parameter 1.
     * @return A new instance of fragment CurrencyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SummaryConceptFragment newInstance(Long currentTripId) {
        SummaryConceptFragment fragment = new SummaryConceptFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, currentTripId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.currentTripId = this.getArguments().getLong(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary_concept, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        ExpenseBO expenseBO = ExpenseBOImpl.getInstance(null);
        List<Expense> conceptSummary =  expenseBO.amountByConceptsAndCurrency(this.currentTripId);
        // Get ListView object from xml
        final ListView listView = (ListView) this.getView().findViewById(R.id.summary_concept_list);
        // Getting total by currencies
        Map<Currency, BigDecimal> total = expenseBO.calculateTotalByCurrency(conceptSummary);

        // Getting percentages by currencies
        final Map<Currency, Map<String, Float>> percentages = expenseBO.percentageConcepts(conceptSummary, total);

        ArrayAdapter<Expense> adapter = new SummaryConceptArrayAdapter(this.getContext(), conceptSummary);
        listView.setAdapter(adapter);


        // Adding listener to access to pie chart.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Expense itemValue = (Expense) listView.getItemAtPosition(position);
                mListener.onConceptChartShow(percentages.get(itemValue.getCurrency()), itemValue.getCurrency());
            }
        });


        // Adding/Updating total info to the fragment
        mListener.onUpdateConceptTotals(total);

    }

    public interface OnSummaryConceptFragmentListener {
        void onConceptChartShow(Map<String, Float> percentages, Currency currency);
        void onUpdateConceptTotals(Map<Currency, BigDecimal> totals);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSummaryConceptFragmentListener) {
            mListener = (OnSummaryConceptFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSummaryConceptFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

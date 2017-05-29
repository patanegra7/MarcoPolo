package cat.dme.smart.marcopolo.fragments.summary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.adapters.SummaryPayerArrayAdapter;
import cat.dme.smart.marcopolo.business.impl.ExpenseBOImpl;
import cat.dme.smart.marcopolo.business.impl.TripBOImpl;
import cat.dme.smart.marcopolo.dao.impl.CurrencyDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.TripDaoImpl;
import cat.dme.smart.marcopolo.model.Currency;
import cat.dme.smart.marcopolo.model.Expense;
import cat.dme.smart.marcopolo.model.Payer;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * link CurrencyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SummaryPayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SummaryPayerFragment extends Fragment {

    private static final String ARG_PARAM1 = "currentTripId";

    private Long currentTripId;

    //private OnFragmentInteractionListener mListener;

    /**
     * Default constructor
     */
    public SummaryPayerFragment() {
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
    public static SummaryPayerFragment newInstance(Long currentTripId) {
        SummaryPayerFragment fragment = new SummaryPayerFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary_payer, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Expense> payerSummary = ExpenseBOImpl.getInstance(null).amountByPayerAndCurrency(this.currentTripId);
        // Get ListView object from xml
        final ListView listView = (ListView) this.getView().findViewById(R.id.summary_payer_list);
        ArrayAdapter<Expense> adapter = new SummaryPayerArrayAdapter(this.getContext(), payerSummary);
        listView.setAdapter(adapter);
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

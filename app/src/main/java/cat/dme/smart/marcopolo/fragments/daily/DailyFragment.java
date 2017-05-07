package cat.dme.smart.marcopolo.fragments.daily;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.activities.EditExpenseActivity;
import cat.dme.smart.marcopolo.adapters.ExpenseArrayAdapter;
import cat.dme.smart.marcopolo.business.impl.ExpenseBOImpl;
import cat.dme.smart.marcopolo.dao.impl.ExpenseDaoImpl;
import cat.dme.smart.marcopolo.model.Expense;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * link ConceptFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DailyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyFragment extends Fragment {

    private static final String ARG_PARAM1 = "currentTripId";

    private Long currentTripId;

    /**
     * Default constructor
     */
    public DailyFragment() {
        super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param currentTripId Parameter 1.
     * @return A new instance of fragment DailyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DailyFragment newInstance(Long currentTripId) {
        DailyFragment fragment = new DailyFragment();
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
        return inflater.inflate(R.layout.fragment_daily, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Expense> expenses = ExpenseBOImpl.getInstance(null).getByTrip(this.currentTripId);
        // Get ListView object from xml
        final ListView listView = (ListView) this.getView().findViewById(R.id.expenses_list);
        ArrayAdapter<Expense> adapter = new ExpenseArrayAdapter(this.getContext(), expenses);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Expense itemValue = (Expense) listView.getItemAtPosition(position);
                startActivity(EditExpenseActivity.runActivity(view.getContext(), itemValue));
            }

        });

        FloatingActionButton fab = (FloatingActionButton) this.getView().findViewById(R.id.fab_add_expense);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(EditExpenseActivity.runActivity(view.getContext(), currentTripId));
            }
        });
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

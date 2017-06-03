package cat.dme.smart.marcopolo.fragments.summary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.adapters.CurrencyArrayAdapter;
import cat.dme.smart.marcopolo.adapters.SummaryConceptArrayAdapter;
import cat.dme.smart.marcopolo.business.ExpenseBO;
import cat.dme.smart.marcopolo.business.impl.ExpenseBOImpl;
import cat.dme.smart.marcopolo.dao.impl.CurrencyDaoImpl;
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

    //private OnFragmentInteractionListener mListener;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        ArrayAdapter<Expense> adapter = new SummaryConceptArrayAdapter(this.getContext(), conceptSummary);
        listView.setAdapter(adapter);

        // Getting the fragment layout
        LinearLayout fragmentLayout = (LinearLayout)this.getView().findViewById(R.id.summary_concept_layout);

        // Getting total by currencies
        Map<String, BigDecimal> total = expenseBO.calculateTotalByCurrency(conceptSummary);

        // Adding/Updating total info to the fragment
        for(String currencyName: total.keySet()) {
            TextView tvTotal = (TextView) fragmentLayout.findViewWithTag("total_concept_" + currencyName);
            if(tvTotal==null) {
                tvTotal = new TextView(this.getContext());
                tvTotal.setTag("total_concept_" + currencyName);
                tvTotal.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                fragmentLayout.addView(tvTotal);
            }
            tvTotal.setText(total.get(currencyName).toString() + " " + currencyName);
        }

        // Getting percentages by currencies
        //Map<String, Map<String, Float>> percentages = expenseBO.percentageConcepts(conceptSummary, total);

        // Adding percentage pie chart to the fragment
        /*for(String currencyName: percentages.keySet()) {
            PieChart chart = (PieChart) fragmentLayout.findViewWithTag("percentage_concept_" + currencyName);
            if(chart==null) {
                chart = new PieChart(this.getContext());
                chart.setTag("percentage_concept_" + currencyName);
                chart.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 700));
                fragmentLayout.addView(chart);
            }
            List<PieEntry> entries = new ArrayList<>();
            Map<String, Float> percentage = percentages.get(currencyName);
            for(String key: percentage.keySet()) {
                entries.add(new PieEntry(percentage.get(key), key));
            }
            PieDataSet set = new PieDataSet(entries, null);

            // add a lot of colors

            ArrayList<Integer> colors = new ArrayList<Integer>();

/*
        for (int c : ColorTemplate.VORDIPLOM_COLORS)

            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

            for (int c : ColorTemplate.COLORFUL_COLORS)
                colors.add(c);
/*
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

            //colors.add(ColorTemplate.getHoloBlue());
            set.setColors(colors);

            PieData data = new PieData(set);
            data.setValueFormatter(new PercentFormatter());
            data.setValueTextSize(12f);
            data.setValueTextColor(Color.parseColor("#5F021F"));
            data.setValueTypeface(Typeface.DEFAULT_BOLD);
            chart.setEntryLabelColor(Color.parseColor("#5F021F"));
            chart.setData(data);
            chart.setCenterText(currencyName);
            chart.setCenterTextColor(Color.WHITE);
            chart.setCenterTextSize(14f);
            chart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD);
            chart.setDrawHoleEnabled(true);
            chart.setHoleColor(Color.DKGRAY);
            // undo all highlights
            //chart.highlightValues(null);

            chart.invalidate(); // refresh
        }*/

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

package cat.dme.smart.marcopolo.fragments.summary.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Currency;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * link CurrencyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PaymentMethodPieCharDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentMethodPieCharDialogFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "conceptPercentages";
    private static final String ARG_PARAM2 = "currency";

    private Map<String, Float> percentages;
    private Currency currency;

    //private OnFragmentInteractionListener mListener;

    /**
     * Default constructor
     */
    public PaymentMethodPieCharDialogFragment() {
        super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param percentages TODO.
     * @param currency TODO.
     * @return A new instance of fragment CurrencyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentMethodPieCharDialogFragment newInstance(Map<String, Float> percentages, Currency currency) {
        PaymentMethodPieCharDialogFragment fragment = new PaymentMethodPieCharDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) percentages);
        args.putSerializable(ARG_PARAM2, currency);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.percentages = (Map<String, Float>) this.getArguments().getSerializable(ARG_PARAM1);
            this.currency = (Currency) this.getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.piechart_dialog_fragment, container, false);
        getDialog().setTitle(R.string.summary_payment_method_percentages_chart_title);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        PieChart chart = (PieChart) this.getView().findViewById(R.id.chart);
        List<PieEntry> entries = new ArrayList<>();
        for(String key: percentages.keySet()) {
            entries.add(new PieEntry(percentages.get(key), key));
        }
        PieDataSet set = new PieDataSet(entries, null);
        // add a lot of colors
        List<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.COLORFUL_COLORS) {
            colors.add(c);
        }
        //colors.add(ColorTemplate.getHoloBlue());
        set.setColors(colors);

        PieData data = new PieData(set);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.parseColor("#5F021F"));
        data.setValueTypeface(Typeface.DEFAULT_BOLD);
        chart.setEntryLabelColor(Color.parseColor("#5F021F"));
        chart.setData(data);
        chart.setCenterText(currency.getName());
        chart.setCenterTextColor(Color.WHITE);
        chart.setCenterTextSize(14f);
        chart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD);
        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.DKGRAY);
        // undo all highlights
        //chart.highlightValues(null);
        chart.invalidate(); // refresh

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

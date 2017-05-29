package cat.dme.smart.marcopolo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Expense;

/**
 * ArrayAdapter that retrieve each currency view to show in the currency list. TODO: change
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class SummaryCurrencyArrayAdapter extends ArrayAdapter<Expense> {

    public SummaryCurrencyArrayAdapter(Context context, List<Expense> expenses) {
        super(context, 0, expenses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Expense expense = this.getItem(position);
        convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.list_summary_currencies, parent, false);

        if(expense.getCurrency()!=null) {
            TextView tvCurrency = (TextView) convertView.findViewById(R.id.summary_currency_name);
            tvCurrency.setText(expense.getCurrency().getName());
        }

        if(expense.getAmount()!=null) {
            TextView tvAmount = (TextView) convertView.findViewById(R.id.summary_amount);
            tvAmount.setText(expense.getAmount().toString());
        }
        return convertView;
    }
}

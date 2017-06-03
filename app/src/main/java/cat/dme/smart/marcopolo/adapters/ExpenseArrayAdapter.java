package cat.dme.smart.marcopolo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.contants.Constants;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Expense;

/**
 * ArrayAdapter that retrieve each expense view to show in the daily list.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class ExpenseArrayAdapter extends ArrayAdapter<Expense> {

    public ExpenseArrayAdapter(Context context, List<Expense> expenses) {
        super(context, 0, expenses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Expense expense = this.getItem(position);
        convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.list_expenses, parent, false);

        TextView tvDate = (TextView) convertView.findViewById(R.id.expense_date);
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_HOUR_MASK);
        tvDate.setText(sdf.format(expense.getDate()));

        TextView tvConcept = (TextView) convertView.findViewById(R.id.expense_concept);
        tvConcept.setText(expense.getConcept().getName());

        TextView tvAmount = (TextView) convertView.findViewById(R.id.expense_amount);
        tvAmount.setText(expense.getAmount().toString() + Constants.SPACE + expense.getCurrency().getSymbol());

        TextView tvPaymentMethod = (TextView) convertView.findViewById(R.id.expense_payment_method);
        tvPaymentMethod.setText(expense.getPaymentMethod().getName() + " - " + expense.getPayer().getName());

        return convertView;
    }
}

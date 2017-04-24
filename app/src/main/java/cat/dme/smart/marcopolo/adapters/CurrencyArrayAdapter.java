package cat.dme.smart.marcopolo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Currency;

/**
 * ArrayAdapter that retrieve each currency view to show in the currency list.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class CurrencyArrayAdapter extends ArrayAdapter<Currency> {

    public CurrencyArrayAdapter(Context context, List<Currency> trips) {
        super(context, 0, trips);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Currency currency = this.getItem(position);
        convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.list_currencies, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.currency_name);
        tvName.setText(currency.getName());

        TextView tvCode = (TextView) convertView.findViewById(R.id.currency_code);
        tvCode.setText(currency.getCode());

        TextView tvSymbol = (TextView) convertView.findViewById(R.id.currency_symbol);
        tvSymbol.setText(currency.getSymbol());
        return convertView;
    }
}

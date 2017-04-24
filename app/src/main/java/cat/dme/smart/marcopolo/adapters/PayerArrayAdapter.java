package cat.dme.smart.marcopolo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Payer;

/**
 * ArrayAdapter that retrieve each payer view to show in the payer list.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class PayerArrayAdapter extends ArrayAdapter<Payer> {

    public PayerArrayAdapter(Context context, List<Payer> payers) {
        super(context, 0, payers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Payer payer = this.getItem(position);
        convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.list_payers, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.payer_name);
        tvName.setText(payer.getName());
        return convertView;
    }
}

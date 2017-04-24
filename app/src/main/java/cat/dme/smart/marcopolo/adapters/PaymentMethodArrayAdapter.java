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
import cat.dme.smart.marcopolo.model.PaymentMethod;

/**
 * ArrayAdapter that retrieve each payment method view to show in the payment method list.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class PaymentMethodArrayAdapter extends ArrayAdapter<PaymentMethod> {

    public PaymentMethodArrayAdapter(Context context, List<PaymentMethod> paymentMethods) {
        super(context, 0, paymentMethods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PaymentMethod paymentMethod = this.getItem(position);
        convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.list_payment_methods, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.payment_method_name);
        tvName.setText(paymentMethod.getName());
        return convertView;
    }
}

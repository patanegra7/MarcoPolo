package cat.dme.smart.marcopolo.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Created by dmolina on 11/02/17.
 */

public class TripArrayAdapter extends ArrayAdapter<Trip> {

    //private final List<Trip> trips;

    public TripArrayAdapter(Context context, List<Trip> trips) {
        super(context, 0, trips);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Trip trip = this.getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.list_trips, parent, false);
        }

        TextView tvFisrtLine = (TextView) convertView.findViewById(R.id.firstLine);
        TextView tvSecondline = (TextView) convertView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.icon);

        tvFisrtLine.setText(trip.getDestination());
        tvSecondline.setText(trip.getDescription());
        imageView.setImageResource(android.R.drawable.ic_menu_mapmode);
        return convertView;
    }
}

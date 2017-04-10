package cat.dme.smart.marcopolo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * ArrayAdapter that retrieve each trip view to show in the trip list.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class TripArrayAdapter extends ArrayAdapter<Trip> {

    Long currentTripId;

    //private final List<Trip> trips;

    public TripArrayAdapter(Context context, List<Trip> trips, Long currentTripId) {
        super(context, 0, trips);
        this.currentTripId = currentTripId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Trip trip = this.getItem(position);
        convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.list_trips, parent, false);

        if(currentTripId!=null && currentTripId.equals(trip.get_id())) {
            // Trip selected
            View rowLayout = convertView.findViewById(R.id.tripRow);
            rowLayout.setBackgroundColor(this.getContext().getResources().getColor(R.color.colorPrimaryMark));
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

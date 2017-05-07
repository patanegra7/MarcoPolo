package cat.dme.smart.marcopolo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * ArrayAdapter that retrieve each concept view to show in the concept list.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class ConceptArrayAdapter extends ArrayAdapter<Concept> {

    public ConceptArrayAdapter(Context context, List<Concept> concepts) {
        super(context, 0, concepts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Concept concept = this.getItem(position);
        convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.list_concepts, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.concept_name);
        tvName.setText(concept.getName());
        return convertView;
    }
}

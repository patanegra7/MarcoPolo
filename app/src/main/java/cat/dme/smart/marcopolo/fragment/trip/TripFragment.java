package cat.dme.smart.marcopolo.fragment.trip;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.activity.EditTripActivity;
import cat.dme.smart.marcopolo.application.MarcoPoloApplication;
import cat.dme.smart.marcopolo.constant.Constants;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * TODO: Doc
 */
public class TripFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "currentTrip";
    private static final String ARG_PARAM2 = "selectedTripId";

    private Trip currentTrip;
    private Long selectedTripId;

    //private OnTripFragmentListener mListener;

    /**
     * Default constructor
     */
    public TripFragment() {
       super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param currentTrip {@link Trip} to shows.
     * @param selectedTripId selected {@link Trip} identifier.
     * @return A new instance of fragment TripFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TripFragment newInstance(Trip currentTrip, Long selectedTripId) {
        TripFragment fragment = new TripFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, currentTrip);
        args.putLong(ARG_PARAM2, selectedTripId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.currentTrip = (Trip)this.getArguments().getSerializable(ARG_PARAM1);
            this.selectedTripId = this.getArguments().getLong(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_trip, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(currentTrip!=null) {
            String destination = currentTrip.getDestination();
            String description = currentTrip.getDescription();
            Date startDate = currentTrip.getStartDate();
            Date endDate = currentTrip.getEndDate();

            TextView tvCurrentTripDestination = this.getView().findViewById(R.id.current_trip_destination);
            tvCurrentTripDestination.setText(destination);

            TextView tvCurrentTripDescription = this.getView().findViewById(R.id.current_trip_description);
            tvCurrentTripDescription.setText(description);

            if(startDate!=null && endDate!=null) {
                TextView tvCurrentTripDate = this.getView().findViewById(R.id.current_trip_date);
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_MASK);

                StringBuilder currentTripDate = new StringBuilder(this.getContext().getString(R.string.trip_from))
                        .append(Constants.SPACE).append(sdf.format(startDate)).append(Constants.SPACE)
                        .append(this.getContext().getString(R.string.trip_to)).append(Constants.SPACE)
                        .append(sdf.format(endDate));

                tvCurrentTripDate.setText(currentTripDate.toString());
            }

            CheckBox tvSelected = this.getView().findViewById(R.id.current_trip_selected);
            if(selectedTripId!=null && selectedTripId.equals(currentTrip.getId())) {
                tvSelected.setChecked(true);
            } else {
                tvSelected.setChecked(false);
            }
            tvSelected.setOnClickListener(this);

            ImageView editTripButton = this.getView().findViewById(R.id.edit_button_icon);
            editTripButton.setOnClickListener(this);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
 /*       if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.current_trip_selected:
                this.onClickSelectedTrip(v);
                break;
            case R.id.edit_button_icon:
                this.onClickEditTrip(v);
                break;
            default:
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
/*    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    } */

    public void onClickSelectedTrip(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            Toast.makeText(this.getActivity(), "Trip selected", Toast.LENGTH_SHORT).show();
            ((MarcoPoloApplication)this.getActivity().getApplication()).setCurrentTripId(this.currentTrip.getId());
            ((MarcoPoloApplication)this.getActivity().getApplication()).setCurrentTripDestination(this.currentTrip.getDestination());
        } else {
            Toast.makeText(this.getActivity(), "Deselect trip is invalid", Toast.LENGTH_LONG).show();
            ((CheckBox) view).setChecked(true);
        }
    }

    public void onClickEditTrip(View view) {
        startActivity(EditTripActivity.runActivity(view.getContext(), this.currentTrip));
    }
}

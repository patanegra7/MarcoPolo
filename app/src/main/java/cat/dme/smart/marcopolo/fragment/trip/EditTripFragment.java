package cat.dme.smart.marcopolo.fragment.trip;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.constant.Constants;
import cat.dme.smart.marcopolo.model.Trip;
import cat.dme.smart.marcopolo.model.TripStatus;

/**
 * TODO: Doc
 */
public class EditTripFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "currentTrip";
    private static final String ARG_PARAM2 = "selectedTripId";

    private Trip currentTrip;
    private Long selectedTripId;

    private OnEditTripFragmentListener mListener;

    /**
     * Default constructor
     */
    public EditTripFragment() {
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
    public static EditTripFragment newInstance(Trip currentTrip, Long selectedTripId) {
        EditTripFragment fragment = new EditTripFragment();
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

        View view = inflater.inflate(R.layout.edit_fragment_trip, container, false);

        // Fills the status spinner
        //StatusSpinner
        Spinner  tvCurrentTripStatus = view.findViewById(R.id.edit_trip_status);
        ArrayAdapter<TripStatus> statusSpinnerArrayAdapter = new ArrayAdapter<TripStatus>(getContext(), android.R.layout.simple_spinner_item, TripStatus.values());
        //statusSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        tvCurrentTripStatus.setAdapter(statusSpinnerArrayAdapter);

        // Registers button listeners
        Button editTripButtonSave = view.findViewById(R.id.edit_trip_button_save);
        editTripButtonSave.setOnClickListener(this);

        Button editTripButtonCancel = view.findViewById(R.id.edit_trip_button_cancel);
        editTripButtonCancel.setOnClickListener(this);

        // In edition mode, a current trip values are loaded.
        if(currentTrip!=null) {
            // Shows delete button in edition mode.
            Button editTripButtonDelete = view.findViewById(R.id.edit_trip_button_delete);
            editTripButtonDelete.setVisibility(View.VISIBLE);
            editTripButtonDelete.setOnClickListener(this);

            EditText tvCurrentTripDestination = view.findViewById(R.id.edit_trip_destination);
            tvCurrentTripDestination.setText(currentTrip.getDestination());

            EditText tvCurrentTripDescription = view.findViewById(R.id.edit_trip_description);
            tvCurrentTripDescription.setText(currentTrip.getDescription());

            if(currentTrip.getStartDate()!=null) {
                EditText tvCurrentTripStartDate = view.findViewById(R.id.edit_trip_startDate);
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_MASK);
                tvCurrentTripStartDate.setText(sdf.format(currentTrip.getStartDate()));
            }

            if(currentTrip.getEndDate()!=null) {
                EditText tvCurrentTripEndDate = view.findViewById(R.id.edit_trip_endDate);
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_MASK);
                tvCurrentTripEndDate.setText(sdf.format(currentTrip.getEndDate()));
            }

            if(currentTrip.getStatus()!=null) {
                tvCurrentTripStatus.setSelection(currentTrip.getStatus().ordinal());
            }
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditTripFragmentListener) {
            mListener = (OnEditTripFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEditTripFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_trip_button_save:
                this.onClickSaveTrip(v);
                break;
            case R.id.edit_trip_button_delete:
                this.onClickDeleteTrip(v);
                break;
            case R.id.edit_trip_button_cancel:
                this.onClickCancelTrip(v);
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
    public interface OnEditTripFragmentListener {
        void onEditFragmentCancel();
        void onEditFragmentSaveNew(Trip trip);
        void onEditFragmentUpdate(Trip trip);
        void onEditFragmentDelete(Long tripId);
    }

    public void onClickSaveTrip(View view) {
        if(currentTrip!=null) {
            //Edition mode
            this.fillTripFromView(currentTrip);
            mListener.onEditFragmentUpdate(currentTrip);
        } else {
            //Creation mode
            currentTrip = new Trip();
            this.fillTripFromView(currentTrip);
            mListener.onEditFragmentSaveNew(currentTrip);
        }
    }

    private void fillTripFromView(Trip trip) {
        TextView tvCurrentTripDestination = this.getView().findViewById(R.id.edit_trip_destination);
        if(tvCurrentTripDestination.getText()!=null) {
            trip.setDestination(tvCurrentTripDestination.getText().toString());
        } else {
            trip.setDestination(null);
        }

        TextView tvCurrentTripDescription = this.getView().findViewById(R.id.edit_trip_description);
        if(tvCurrentTripDescription.getText()!=null) {
            trip.setDescription(tvCurrentTripDescription.getText().toString());
        } else {
            trip.setDescription(null);
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_MASK);
            TextView tvCurrentTripStartDate = this.getView().findViewById(R.id.edit_trip_startDate);
            if (tvCurrentTripStartDate.getText() != null) {
                trip.setStartDate(sdf.parse(tvCurrentTripStartDate.getText().toString()));
            } else {
                trip.setStartDate(null);
            }
            TextView tvCurrentTripEndDate = this.getView().findViewById(R.id.edit_trip_endDate);
            if (tvCurrentTripEndDate.getText() != null) {
                trip.setEndDate(sdf.parse(tvCurrentTripEndDate.getText().toString()));
            } else {
                trip.setEndDate(null);
            }
        } catch (ParseException pe) {
            pe.printStackTrace(); //TODO: Improve
        }

        Spinner tvCurrentTripStatus = this.getView().findViewById(R.id.edit_trip_status);
        trip.setStatus(TripStatus.values()[tvCurrentTripStatus.getSelectedItemPosition()]);
    }

    public void onClickDeleteTrip(View view) {
        mListener.onEditFragmentDelete(this.currentTrip.getId());
    }

    public void onClickCancelTrip(View view) {
        mListener.onEditFragmentCancel();
    }
}

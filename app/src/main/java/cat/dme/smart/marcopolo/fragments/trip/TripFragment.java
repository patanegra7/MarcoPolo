package cat.dme.smart.marcopolo.fragments.trip;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.activities.EditTripActivity;
import cat.dme.smart.marcopolo.activities.TripActivity;
import cat.dme.smart.marcopolo.contants.Constants;
import cat.dme.smart.marcopolo.model.Trip;
import cat.dme.smart.marcopolo.model.global.MarcoPoloApplication;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trip, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String destination;
        String description;
        Date startDate;
        Date endDate;
        if(currentTrip!=null) {
            destination = currentTrip.getDestination();
            description = currentTrip.getDescription();
            startDate = currentTrip.getStartDate();
            endDate = currentTrip.getEndDate();

            TextView tvCurrentTripDestination = (TextView) this.getView().findViewById(R.id.current_trip_destination);
            tvCurrentTripDestination.setText(destination);

            TextView tvCurrentTripDescription = (TextView) this.getView().findViewById(R.id.current_trip_description);
            tvCurrentTripDescription.setText(description);

            if(startDate!=null && endDate!=null) {
                TextView tvCurrentTripDate = (TextView) this.getView().findViewById(R.id.current_trip_date);
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_MASK);

                StringBuilder currentTripDate = new StringBuilder(this.getContext().getString(R.string.trip_from))
                        .append(Constants.SPACE).append(sdf.format(startDate)).append(Constants.SPACE)
                        .append(this.getContext().getString(R.string.trip_to)).append(Constants.SPACE)
                        .append(sdf.format(endDate));

                tvCurrentTripDate.setText(currentTripDate.toString());
            }

            CheckBox tvSelected = (CheckBox) this.getView().findViewById(R.id.current_trip_selected);
            if(selectedTripId!=null && selectedTripId.equals(currentTrip.get_id())) {
                tvSelected.setChecked(true);
            } else {
                tvSelected.setChecked(false);
            }
            tvSelected.setOnClickListener(this);

            ImageView editTripButton = (ImageView) this.getView().findViewById(R.id.edit_button_icon);
            editTripButton.setOnClickListener(this);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
  /*      if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        } */
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
 /*   public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/

    public void onClickSelectedTrip(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if(checked) {
            Snackbar.make(view, "selected", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            ((MarcoPoloApplication)this.getActivity().getApplication()).setCurrentTripId(this.currentTrip.get_id());

        } else {
            Snackbar.make(view, "unselected is invalid", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            ((CheckBox) view).setChecked(true);
        }
    }

    public void onClickEditTrip(View view) {

        //Snackbar.make(view, "Edit Trip", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        startActivity(EditTripActivity.runActivity(view.getContext(), this.currentTrip));
    }
}

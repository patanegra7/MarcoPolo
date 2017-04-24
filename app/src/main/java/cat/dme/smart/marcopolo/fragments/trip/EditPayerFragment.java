package cat.dme.smart.marcopolo.fragments.trip;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.exceptions.ValidationException;
import cat.dme.smart.marcopolo.model.Payer;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Fragment to create, delete and update @{link {@link Payer}} details.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class EditPayerFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "currentPayer";
    private static final String ARG_PARAM2 = "currentTripId";

    private Payer currentPayer;
    private Long currentTripId;

    private OnEditPayerFragmentListener mListener;

    /**
     * Default constructor
     */
    public EditPayerFragment() {
       super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param currentPayer {@link Payer} to shows.
     * @param currentTripId current {@link Trip} identifier.
     * @return A new instance of fragment TripFragment.
     */
    public static EditPayerFragment newInstance(Payer currentPayer, Long currentTripId) {
        EditPayerFragment fragment = new EditPayerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, currentPayer);
        args.putLong(ARG_PARAM2, currentTripId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.currentPayer = (Payer) this.getArguments().getSerializable(ARG_PARAM1);
            this.currentTripId = this.getArguments().getLong(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.edit_fragment_payer, container, false);

        // Registers button listeners
        Button editButtonSave = (Button) view.findViewById(R.id.edit_payer_button_save);
        editButtonSave.setOnClickListener(this);

        Button editButtonCancel = (Button) view.findViewById(R.id.edit_payer_button_cancel);
        editButtonCancel.setOnClickListener(this);

        // In edition mode, a current trip values are loaded.
        if(currentPayer!=null) {
            // Shows delete button in edition mode.
            Button editButtonDelete = (Button) view.findViewById(R.id.edit_payer_button_delete);
            editButtonDelete.setVisibility(View.VISIBLE);
            editButtonDelete.setOnClickListener(this);

            EditText tvPayerName = (EditText) view.findViewById(R.id.edit_payer_name);
            tvPayerName.setText(currentPayer.getName());
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditPayerFragmentListener) {
            mListener = (OnEditPayerFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEditPayerFragmentListener");
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
            case R.id.edit_payer_button_save:
                this.onClickSave(v);
                break;
            case R.id.edit_payer_button_delete:
                this.onClickDelete(v);
                break;
            case R.id.edit_payer_button_cancel:
                this.onClickCancel(v);
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
    public interface OnEditPayerFragmentListener {
        void onEditFragmentCancel();
        void onEditFragmentSaveNew(Payer payer);
        void onEditFragmentUpdate(Payer payer);
        void onEditFragmentDelete(Long payerId);
    }

    public void onClickSave(View view) {

        try {
            if (currentPayer != null) {
                //Edition mode
                this.fillTripFromView(currentPayer, view);
                mListener.onEditFragmentUpdate(currentPayer);
            } else {
                //Creation mode
                currentPayer = new Payer();
                currentPayer.setTripId(currentTripId);
                this.fillTripFromView(currentPayer, view);
                mListener.onEditFragmentSaveNew(currentPayer);
            }
        } catch (ValidationException vE) {
            Snackbar.make(view, this.getString(R.string.edit_payer_validation_error), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    private void fillTripFromView(Payer payer, View view) throws ValidationException {
        TextView tvPayerName = (TextView) this.getView().findViewById(R.id.edit_payer_name);
        if(tvPayerName.getText()!=null) {
            payer.setName(tvPayerName.getText().toString());
        } else {
           throw new ValidationException();
        }
    }

    public void onClickDelete(View view) {
        mListener.onEditFragmentDelete(this.currentPayer.get_id());
    }

    public void onClickCancel(View view) {
        mListener.onEditFragmentCancel();
    }
}

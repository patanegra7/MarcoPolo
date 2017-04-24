package cat.dme.smart.marcopolo.fragments.trip;

import android.app.DialogFragment;
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
import cat.dme.smart.marcopolo.fragments.trip.dialog.DeleteConceptDialogFragment;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Fragment to create, delete and update @{link {@link Concept}} details.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class EditConceptFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "currentConcept";
    private static final String ARG_PARAM2 = "currentTripId";

    private Concept currentConcept;
    private Long currentTripId;

    private OnEditConceptFragmentListener mListener;

    /**
     * Default constructor
     */
    public EditConceptFragment() {
       super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param currentConcept {@link Concept} to shows.
     * @param currentTripId current {@link Trip} identifier.
     * @return A new instance of fragment TripFragment.
     */
    public static EditConceptFragment newInstance(Concept currentConcept, Long currentTripId) {
        EditConceptFragment fragment = new EditConceptFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, currentConcept);
        args.putLong(ARG_PARAM2, currentTripId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.currentConcept = (Concept) this.getArguments().getSerializable(ARG_PARAM1);
            this.currentTripId = this.getArguments().getLong(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.edit_fragment_concept, container, false);

        // Registers button listeners
        Button editButtonSave = (Button) view.findViewById(R.id.edit_concept_button_save);
        editButtonSave.setOnClickListener(this);

        Button editButtonCancel = (Button) view.findViewById(R.id.edit_concept_button_cancel);
        editButtonCancel.setOnClickListener(this);

        // In edition mode, a current trip values are loaded.
        if(currentConcept!=null) {
            // Shows delete button in edition mode.
            Button editButtonDelete = (Button) view.findViewById(R.id.edit_concept_button_delete);
            editButtonDelete.setVisibility(View.VISIBLE);
            editButtonDelete.setOnClickListener(this);

            EditText tvConceptName = (EditText) view.findViewById(R.id.edit_concept_name);
            tvConceptName.setText(currentConcept.getName());
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditConceptFragmentListener) {
            mListener = (OnEditConceptFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEditConceptFragmentListener");
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
            case R.id.edit_concept_button_save:
                this.onClickSave(v);
                break;
            case R.id.edit_concept_button_delete:
                this.onClickDelete(v);
                break;
            case R.id.edit_concept_button_cancel:
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
    public interface OnEditConceptFragmentListener {
        void onEditFragmentCancel();
        void onEditFragmentSaveNew(Concept concept);
        void onEditFragmentUpdate(Concept concept);
        void onEditFragmentDelete(Long conceptId);
    }

    public void onClickSave(View view) {

        try {
            if (currentConcept != null) {
                //Edition mode
                this.fillTripFromView(currentConcept, view);
                mListener.onEditFragmentUpdate(currentConcept);
            } else {
                //Creation mode
                currentConcept = new Concept();
                currentConcept.setTripId(currentTripId);
                this.fillTripFromView(currentConcept, view);
                mListener.onEditFragmentSaveNew(currentConcept);
            }
        } catch (ValidationException vE) {
            Snackbar.make(view, this.getString(R.string.edit_concept_validation_error), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    private void fillTripFromView(Concept concept, View view) throws ValidationException {
        TextView tvConceptName = (TextView) this.getView().findViewById(R.id.edit_concept_name);
        if(tvConceptName.getText()!=null) {
            concept.setName(tvConceptName.getText().toString());
        } else {
           throw new ValidationException();
        }
    }

    public void onClickDelete(View view) {
        mListener.onEditFragmentDelete(this.currentConcept.get_id());
    }

    public void onClickCancel(View view) {
        mListener.onEditFragmentCancel();
    }
}

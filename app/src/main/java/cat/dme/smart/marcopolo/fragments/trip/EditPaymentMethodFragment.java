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
import cat.dme.smart.marcopolo.model.PaymentMethod;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Fragment to create, delete and update @{link {@link PaymentMethod}} details.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class EditPaymentMethodFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "currentPaymentMethod";
    private static final String ARG_PARAM2 = "currentTripId";

    private PaymentMethod currentPaymentMethod;
    private Long currentTripId;

    private OnEditPaymentMethodFragmentListener mListener;

    /**
     * Default constructor
     */
    public EditPaymentMethodFragment() {
       super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param currentPaymentMethod {@link PaymentMethod} to shows.
     * @param currentTripId current {@link Trip} identifier.
     * @return A new instance of fragment TripFragment.
     */
    public static EditPaymentMethodFragment newInstance(PaymentMethod currentPaymentMethod, Long currentTripId) {
        EditPaymentMethodFragment fragment = new EditPaymentMethodFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, currentPaymentMethod);
        args.putLong(ARG_PARAM2, currentTripId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.currentPaymentMethod = (PaymentMethod) this.getArguments().getSerializable(ARG_PARAM1);
            this.currentTripId = this.getArguments().getLong(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.edit_fragment_payment_method, container, false);

        // Registers button listeners
        Button editButtonSave = (Button) view.findViewById(R.id.edit_payment_method_button_save);
        editButtonSave.setOnClickListener(this);

        Button editButtonCancel = (Button) view.findViewById(R.id.edit_payment_method_button_cancel);
        editButtonCancel.setOnClickListener(this);

        // In edition mode, a current trip values are loaded.
        if(currentPaymentMethod!=null) {
            // Shows delete button in edition mode.
            Button editButtonDelete = (Button) view.findViewById(R.id.edit_payment_method_button_delete);
            editButtonDelete.setVisibility(View.VISIBLE);
            editButtonDelete.setOnClickListener(this);

            EditText tvPaymentMethodName = (EditText) view.findViewById(R.id.edit_payment_method_name);
            tvPaymentMethodName.setText(currentPaymentMethod.getName());
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditPaymentMethodFragmentListener) {
            mListener = (OnEditPaymentMethodFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEditPaymentMethodFragmentListener");
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
            case R.id.edit_payment_method_button_save:
                this.onClickSave(v);
                break;
            case R.id.edit_payment_method_button_delete:
                this.onClickDelete(v);
                break;
            case R.id.edit_payment_method_button_cancel:
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
    public interface OnEditPaymentMethodFragmentListener {
        void onEditFragmentCancel();
        void onEditFragmentSaveNew(PaymentMethod paymentMethod);
        void onEditFragmentUpdate(PaymentMethod paymentMethod);
        void onEditFragmentDelete(Long paymentMethodId);
    }

    public void onClickSave(View view) {

        try {
            if (currentPaymentMethod != null) {
                //Edition mode
                this.fillTripFromView(currentPaymentMethod, view);
                mListener.onEditFragmentUpdate(currentPaymentMethod);
            } else {
                //Creation mode
                currentPaymentMethod = new PaymentMethod();
                currentPaymentMethod.setTripId(currentTripId);
                this.fillTripFromView(currentPaymentMethod, view);
                mListener.onEditFragmentSaveNew(currentPaymentMethod);
            }
        } catch (ValidationException vE) {
            Snackbar.make(view, this.getString(R.string.edit_payment_method_validation_error), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    private void fillTripFromView(PaymentMethod paymentMethod, View view) throws ValidationException {
        TextView tvPaymentMethodName = (TextView) this.getView().findViewById(R.id.edit_payment_method_name);
        if(tvPaymentMethodName.getText()!=null) {
            paymentMethod.setName(tvPaymentMethodName.getText().toString());
        } else {
           throw new ValidationException();
        }
    }

    public void onClickDelete(View view) {
        mListener.onEditFragmentDelete(this.currentPaymentMethod.get_id());
    }

    public void onClickCancel(View view) {
        mListener.onEditFragmentCancel();
    }
}

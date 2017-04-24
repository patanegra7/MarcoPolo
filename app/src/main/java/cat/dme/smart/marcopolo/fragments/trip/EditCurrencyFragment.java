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
import cat.dme.smart.marcopolo.model.Currency;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Fragment to create, delete and update @{link {@link Currency}} details.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class EditCurrencyFragment extends Fragment implements View.OnClickListener { //, DeleteCurrencyDialogFragment.DeleteCurrencyDialogListener {

    private static final String ARG_PARAM1 = "currentCurrency";
    private static final String ARG_PARAM2 = "currentTripId";

    private Currency currentCurrency;
    private Long currentTripId;

    private OnEditCurrencyFragmentListener mListener;

    /**
     * Default constructor
     */
    public EditCurrencyFragment() {
       super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param currentCurrency {@link Currency} to shows.
     * @param currentTripId current {@link Trip} identifier.
     * @return A new instance of fragment TripFragment.
     */
    public static EditCurrencyFragment newInstance(Currency currentCurrency, Long currentTripId) {
        EditCurrencyFragment fragment = new EditCurrencyFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, currentCurrency);
        args.putLong(ARG_PARAM2, currentTripId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.currentCurrency = (Currency) this.getArguments().getSerializable(ARG_PARAM1);
            this.currentTripId = this.getArguments().getLong(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.edit_fragment_currency, container, false);

        // Registers button listeners
        Button editButtonSave = (Button) view.findViewById(R.id.edit_currency_button_save);
        editButtonSave.setOnClickListener(this);

        Button editButtonCancel = (Button) view.findViewById(R.id.edit_currency_button_cancel);
        editButtonCancel.setOnClickListener(this);

        // In edition mode, a current trip values are loaded.
        if(currentCurrency!=null) {
            // Shows delete button in edition mode.
            Button editButtonDelete = (Button) view.findViewById(R.id.edit_currency_button_delete);
            editButtonDelete.setVisibility(View.VISIBLE);
            editButtonDelete.setOnClickListener(this);

            EditText tvCurrencyName = (EditText) view.findViewById(R.id.edit_currency_name);
            tvCurrencyName.setText(currentCurrency.getName());

            EditText tvCurrencyCode = (EditText) view.findViewById(R.id.edit_currency_code);
            tvCurrencyCode.setText(currentCurrency.getCode());

            EditText tvCurrencySymbol = (EditText) view.findViewById(R.id.edit_currency_symbol);
            tvCurrencySymbol.setText(currentCurrency.getSymbol());
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditCurrencyFragmentListener) {
            mListener = (OnEditCurrencyFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEditCurrencyFragmentListener");
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
            case R.id.edit_currency_button_save:
                this.onClickSave(v);
                break;
            case R.id.edit_currency_button_delete:
                this.onClickDelete(v);
                break;
            case R.id.edit_currency_button_cancel:
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
    public interface OnEditCurrencyFragmentListener {
        void onEditFragmentCancel();
        void onEditFragmentSaveNew(Currency currency);
        void onEditFragmentUpdate(Currency currency);
        void onEditFragmentDelete(Long currencyId);
    }

    public void onClickSave(View view) {

        try {
            if (currentCurrency != null) {
                //Edition mode
                this.fillTripFromView(currentCurrency, view);
                mListener.onEditFragmentUpdate(currentCurrency);
            } else {
                //Creation mode
                currentCurrency = new Currency();
                currentCurrency.setTripId(currentTripId);
                this.fillTripFromView(currentCurrency, view);
                mListener.onEditFragmentSaveNew(currentCurrency);
            }
        } catch (ValidationException vE) {
            Snackbar.make(view, this.getString(R.string.edit_currency_validation_error), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    private void fillTripFromView(Currency currency, View view) throws ValidationException {
        TextView tvCurrencyName = (TextView) this.getView().findViewById(R.id.edit_currency_name);
        if(tvCurrencyName.getText()!=null) {
            currency.setName(tvCurrencyName.getText().toString());
        } else {
           throw new ValidationException();
        }
        TextView tvCurrencyCode = (TextView) this.getView().findViewById(R.id.edit_currency_code);
        currency.setCode(tvCurrencyCode.getText().toString());
        TextView tvCurrencySymbol = (TextView) this.getView().findViewById(R.id.edit_currency_symbol);
        currency.setSymbol(tvCurrencySymbol.getText().toString());
    }

    public void onClickDelete(View view) {
        mListener.onEditFragmentDelete(this.currentCurrency.get_id());
    }

    public void onClickCancel(View view) {
        mListener.onEditFragmentCancel();
    }
}

package cat.dme.smart.marcopolo.fragments.daily;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.contants.Constants;
import cat.dme.smart.marcopolo.dao.impl.ConceptDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.CurrencyDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.PayerDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.PaymentMethodDaoImpl;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Currency;
import cat.dme.smart.marcopolo.model.Expense;
import cat.dme.smart.marcopolo.model.Payer;
import cat.dme.smart.marcopolo.model.PaymentMethod;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Fragment to edit @{link {@link Expense}.
 */
public class EditExpenseFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "currentExpense";
    private static final String ARG_PARAM2 = "selectedTripId";

    private Expense currentExpense;
    private Long selectedTripId;

    private OnEditExpenseFragmentListener mListener;

    /**
     * Default constructor
     */
    public EditExpenseFragment() {
       super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param currentExpense {@link Expense} to shows.
     * @param selectedTripId selected {@link Trip} identifier.
     * @return A new instance of fragment TripFragment.
     */
    public static EditExpenseFragment newInstance(Expense currentExpense, Long selectedTripId) {
        EditExpenseFragment fragment = new EditExpenseFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, currentExpense);
        args.putLong(ARG_PARAM2, selectedTripId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.currentExpense = (Expense)this.getArguments().getSerializable(ARG_PARAM1);
            this.selectedTripId = this.getArguments().getLong(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.edit_fragment_expense, container, false);

        // Fills the spinners
        Spinner sConcept = (Spinner) view.findViewById(R.id.edit_expense_concept);
        final List<Concept> concepts = ConceptDaoImpl.getInstance().getAllByTrip(selectedTripId);
        ArrayAdapter<Concept> conceptsSpinnerArrayAdapter = new ArrayAdapter<Concept>(getContext(), android.R.layout.simple_spinner_item, concepts) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((TextView)view.findViewById(android.R.id.text1)).setText(concepts.get(position).getName());
                return view;
            }
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                ((TextView)view.findViewById(android.R.id.text1)).setText(concepts.get(position).getName());
                return view;
            }
        };
        //statusSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sConcept.setAdapter(conceptsSpinnerArrayAdapter);

        Spinner sCurrency = (Spinner) view.findViewById(R.id.edit_expense_currency);
        final List<Currency> currencies = CurrencyDaoImpl.getInstance().getAllByTrip(selectedTripId);
        ArrayAdapter<Currency> currenciesSpinnerArrayAdapter = new ArrayAdapter<Currency>(getContext(), android.R.layout.simple_spinner_item, currencies) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((TextView)view.findViewById(android.R.id.text1)).setText(currencies.get(position).getName());
                return view;
            }
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                ((TextView)view.findViewById(android.R.id.text1)).setText(currencies.get(position).getName());
                return view;
            }
        };
        sCurrency.setAdapter(currenciesSpinnerArrayAdapter);

        Spinner sPayer = (Spinner) view.findViewById(R.id.edit_expense_payer);
        final List<Payer> payers = PayerDaoImpl.getInstance().getAllByTrip(selectedTripId);
        ArrayAdapter<Payer> payersSpinnerArrayAdapter = new ArrayAdapter<Payer>(getContext(), android.R.layout.simple_spinner_item, payers) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((TextView)view.findViewById(android.R.id.text1)).setText(payers.get(position).getName());
                return view;
            }
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                ((TextView)view.findViewById(android.R.id.text1)).setText(payers.get(position).getName());
                return view;
            }
        };
        sPayer.setAdapter(payersSpinnerArrayAdapter);

        Spinner sPaymentMethod = (Spinner) view.findViewById(R.id.edit_expense_payment_method);
        final List<PaymentMethod> paymentMethods = PaymentMethodDaoImpl.getInstance().getAllByTrip(selectedTripId);
        ArrayAdapter<PaymentMethod> paymentMethodsSpinnerArrayAdapter = new ArrayAdapter<PaymentMethod>(getContext(), android.R.layout.simple_spinner_item, paymentMethods) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((TextView)view.findViewById(android.R.id.text1)).setText(paymentMethods.get(position).getName());
                return view;
            }
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                ((TextView)view.findViewById(android.R.id.text1)).setText(paymentMethods.get(position).getName());
                return view;
            }
        };
        sPaymentMethod.setAdapter(paymentMethodsSpinnerArrayAdapter);

        // Registers button listeners
        Button editExpenseButtonSave = (Button) view.findViewById(R.id.edit_expense_button_save);
        editExpenseButtonSave.setOnClickListener(this);

        Button editExpenseButtonCancel = (Button) view.findViewById(R.id.edit_expense_button_cancel);
        editExpenseButtonCancel.setOnClickListener(this);

        // In edition mode, a current trip values are loaded.
        if(this.currentExpense!=null) {
            // Shows delete button in edition mode.
            Button editExpenseButtonDelete = (Button) view.findViewById(R.id.edit_expense_button_delete);
            editExpenseButtonDelete.setVisibility(View.VISIBLE);
            editExpenseButtonDelete.setOnClickListener(this);

            if (this.currentExpense.getDate() != null) {
                EditText tvExpenseDate = (EditText) view.findViewById(R.id.edit_expense_date);
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_HOUR_MASK);
                tvExpenseDate.setText(sdf.format(this.currentExpense.getDate()));
            }

            if (this.currentExpense.getConcept() != null) {
                sConcept.setSelection(conceptsSpinnerArrayAdapter.getPosition(this.currentExpense.getConcept()));
            }

            if (this.currentExpense.getDescription() != null) {
                EditText tvExpenseDescription = (EditText) view.findViewById(R.id.edit_expense_description);
                tvExpenseDescription.setText(this.currentExpense.getDescription());
            }

            if (this.currentExpense.getAmount() != null) {
                EditText tvExpenseAmount = (EditText) view.findViewById(R.id.edit_expense_amount);
                tvExpenseAmount.setText(this.currentExpense.getAmount().toString());
            }

            if (this.currentExpense.getCurrency() != null) {
                sCurrency.setSelection(currenciesSpinnerArrayAdapter.getPosition(this.currentExpense.getCurrency()));
            }

            if (this.currentExpense.getPayer() != null) {
                sPayer.setSelection(payersSpinnerArrayAdapter.getPosition(this.currentExpense.getPayer()));
            }

            if (currentExpense.getPaymentMethod() != null) {
                sPaymentMethod.setSelection(paymentMethodsSpinnerArrayAdapter.getPosition(currentExpense.getPaymentMethod()));
            }

        } else {
            EditText tvExpenseDate = (EditText) view.findViewById(R.id.edit_expense_date);
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_HOUR_MASK);
            tvExpenseDate.setText(sdf.format(new Date()));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditExpenseFragmentListener) {
            mListener = (OnEditExpenseFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEditExpenseFragmentListener");
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
            case R.id.edit_expense_button_save:
                this.onClickSaveExpense(v);
                break;
            case R.id.edit_expense_button_delete:
                this.onClickDeleteExpense(v);
                break;
            case R.id.edit_expense_button_cancel:
                this.onClickCancelExpense(v);
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
    public interface OnEditExpenseFragmentListener {
        void onEditFragmentCancel();
        void onEditFragmentSaveNew(Expense expense);
        void onEditFragmentUpdate(Expense expense);
        void onEditFragmentDelete(Long expenseId);
    }

    public void onClickSaveExpense(View view) {
        if(this.currentExpense!=null) {
            //Edition mode
            this.fillExpenseFromView(this.currentExpense, view);
            mListener.onEditFragmentUpdate(this.currentExpense);
        } else {
            //Creation mode
            this.currentExpense = new Expense();
            this.currentExpense.setTripId(this.selectedTripId);
            this.fillExpenseFromView(this.currentExpense, view);
            mListener.onEditFragmentSaveNew(this.currentExpense);
        }
    }

    private void fillExpenseFromView(Expense expense, View view) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_HOUR_MASK);
            TextView tvExpenseDate = (TextView) this.getView().findViewById(R.id.edit_expense_date);
            if (tvExpenseDate.getText() != null) {
                expense.setDate(sdf.parse(tvExpenseDate.getText().toString()));
            } else {
                expense.setDate(null);
            }
        } catch (ParseException pe) {
            pe.printStackTrace(); //TODO: Improve
        }

        Spinner sConcept = (Spinner) this.getView().findViewById(R.id.edit_expense_concept);
        expense.setConcept((Concept)sConcept.getSelectedItem());

        EditText tvExpenseDescription = (EditText) this.getView().findViewById(R.id.edit_expense_description);
        if(tvExpenseDescription.getText()!=null) {
            expense.setDescription(tvExpenseDescription.getText().toString());
        } else {
            expense.setDescription(null);
        }

        EditText tvExpenseAmount = (EditText) this.getView().findViewById(R.id.edit_expense_amount);
        if(tvExpenseAmount.getText()!=null && tvExpenseAmount.getText().length()>0) {
            expense.setAmount(new BigDecimal(tvExpenseAmount.getText().toString()));
        } else {
            expense.setAmount(BigDecimal.ZERO);
        }

        Spinner sCurrency = (Spinner) this.getView().findViewById(R.id.edit_expense_currency);
        expense.setCurrency((Currency) sCurrency.getSelectedItem());

        Spinner sPayer = (Spinner) this.getView().findViewById(R.id.edit_expense_payer);
        expense.setPayer((Payer) sPayer.getSelectedItem());

        Spinner sPaymentMethod = (Spinner) this.getView().findViewById(R.id.edit_expense_payment_method);
        expense.setPaymentMethod((PaymentMethod) sPaymentMethod.getSelectedItem());

    }

    public void onClickDeleteExpense(View view) {
        mListener.onEditFragmentDelete(this.currentExpense.get_id());
    }

    public void onClickCancelExpense(View view) {
        mListener.onEditFragmentCancel();
    }
}

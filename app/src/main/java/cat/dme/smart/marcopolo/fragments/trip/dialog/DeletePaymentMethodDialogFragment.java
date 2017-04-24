package cat.dme.smart.marcopolo.fragments.trip.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.PaymentMethod;


/**
 * Dialog to confirm @{link {@link PaymentMethod}} deletion.
 *
 * Created by VIddA Software - DME Creaciones.
 */

public class DeletePaymentMethodDialogFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "currentPaymentMethodId";

    private Long currentPaymentMethodId;

    private DeletePaymentMethodDialogListener mListener;

    public static DeletePaymentMethodDialogFragment newInstance(Long currentPaymentMethodId) {
        DeletePaymentMethodDialogFragment fragment = new DeletePaymentMethodDialogFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, currentPaymentMethodId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.currentPaymentMethodId = this.getArguments().getLong(ARG_PARAM1);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DeletePaymentMethodDialogFragment.DeletePaymentMethodDialogListener) {
            mListener = (DeletePaymentMethodDialogFragment.DeletePaymentMethodDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DeletePaymentMethodDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        if (getActivity() instanceof DeletePaymentMethodDialogFragment.DeletePaymentMethodDialogListener) {
            mListener = (DeletePaymentMethodDialogFragment.DeletePaymentMethodDialogListener) getActivity();
        } else {
            throw new RuntimeException(getActivity().toString()
                    + " must implement DeletePaymentMethodDialogListener");
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.edit_payment_method_delete_confirm)
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDeleteConfirmClick(DeletePaymentMethodDialogFragment.this, currentPaymentMethodId);
                    }
                })
                .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public interface DeletePaymentMethodDialogListener {
        public void onDeleteConfirmClick(DialogFragment dialog, Long currentPaymentMethodId);
    }

}
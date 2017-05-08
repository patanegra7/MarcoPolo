package cat.dme.smart.marcopolo.fragments.trip.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Currency;


/**
 * Dialog to confirm @{link {@link Currency}} deletion.
 *
 * Created by VIddA Software - DME Creaciones.
 */

public class DeleteCurrencyDialogFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "currentCurrencyId";

    private Long currentCurrencyId;

    private DeleteCurrencyDialogListener mListener;

    public static DeleteCurrencyDialogFragment newInstance(Long currentCurrencyId) {
        DeleteCurrencyDialogFragment fragment = new DeleteCurrencyDialogFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, currentCurrencyId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.currentCurrencyId = this.getArguments().getLong(ARG_PARAM1);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DeleteCurrencyDialogFragment.DeleteCurrencyDialogListener) {
            mListener = (DeleteCurrencyDialogFragment.DeleteCurrencyDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DeleteCurrencyDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        if (getActivity() instanceof DeleteCurrencyDialogFragment.DeleteCurrencyDialogListener) {
            mListener = (DeleteCurrencyDialogFragment.DeleteCurrencyDialogListener) getActivity();
        } else {
            throw new RuntimeException(getActivity().toString()
                    + " must implement DeleteCurrencyDialogListener");
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.edit_currency_delete_confirm)
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDeleteConfirmClick(DeleteCurrencyDialogFragment.this, currentCurrencyId);
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

    public interface DeleteCurrencyDialogListener {
        void onDeleteConfirmClick(DialogFragment dialog, Long currentCurrencyId);
    }

}
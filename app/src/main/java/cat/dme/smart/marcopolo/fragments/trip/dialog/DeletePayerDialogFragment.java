package cat.dme.smart.marcopolo.fragments.trip.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Payer;


/**
 * Dialog to confirm @{link {@link Payer}} deletion.
 *
 * Created by VIddA Software - DME Creaciones.
 */

public class DeletePayerDialogFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "currentPayerId";

    private Long currentPayerId;

    private DeletePayerDialogListener mListener;

    public static DeletePayerDialogFragment newInstance(Long currentPayerId) {
        DeletePayerDialogFragment fragment = new DeletePayerDialogFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, currentPayerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.currentPayerId = this.getArguments().getLong(ARG_PARAM1);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DeletePayerDialogFragment.DeletePayerDialogListener) {
            mListener = (DeletePayerDialogFragment.DeletePayerDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DeletePayerDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        if (getActivity() instanceof DeletePayerDialogFragment.DeletePayerDialogListener) {
            mListener = (DeletePayerDialogFragment.DeletePayerDialogListener) getActivity();
        } else {
            throw new RuntimeException(getActivity().toString()
                    + " must implement DeletePayerDialogListener");
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.edit_payer_delete_confirm)
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDeleteConfirmClick(DeletePayerDialogFragment.this, currentPayerId);
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

    public interface DeletePayerDialogListener {
        void onDeleteConfirmClick(DialogFragment dialog, Long currentPayerId);
    }

}
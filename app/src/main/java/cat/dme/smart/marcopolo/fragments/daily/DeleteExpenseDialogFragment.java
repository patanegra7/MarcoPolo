package cat.dme.smart.marcopolo.fragments.daily;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Expense;

/**
 * Dialog to confirm {@link Expense} deletion.
 *
 * Created by VIddA Software - DME Creaciones.
 */

public class DeleteExpenseDialogFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "currentExpenseId";

    private Long currentExpenseId;

    private DeleteExpenseDialogListener mListener;

    public static DeleteExpenseDialogFragment newInstance(Long currentExpenseId) {
        DeleteExpenseDialogFragment fragment = new DeleteExpenseDialogFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, currentExpenseId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.currentExpenseId = this.getArguments().getLong(ARG_PARAM1);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DeleteExpenseDialogFragment.DeleteExpenseDialogListener) {
            mListener = (DeleteExpenseDialogFragment.DeleteExpenseDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DeleteExpenseDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        if (getActivity() instanceof DeleteExpenseDialogFragment.DeleteExpenseDialogListener) {
            mListener = (DeleteExpenseDialogFragment.DeleteExpenseDialogListener) getActivity();
        } else {
            throw new RuntimeException(getActivity().toString()
                    + " must implement DeleteExpenseDialogListener");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.edit_expense_delete_confirm)
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDeleteConfirmClick(DeleteExpenseDialogFragment.this, currentExpenseId);
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

    public interface DeleteExpenseDialogListener {
        void onDeleteConfirmClick(DialogFragment dialog, Long currentExpenseId);
    }

}
package cat.dme.smart.marcopolo.fragments.trip.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Concept;


/**
 * Dialog to confirm @{link {@link Concept}} deletion.
 *
 * Created by VIddA Software - DME Creaciones.
 */

public class DeleteConceptDialogFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "currentConceptId";

    private Long currentConceptId;

    private DeleteConceptDialogListener mListener;

    public static DeleteConceptDialogFragment newInstance(Long currentConceptId) {
        DeleteConceptDialogFragment fragment = new DeleteConceptDialogFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, currentConceptId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.currentConceptId = this.getArguments().getLong(ARG_PARAM1);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DeleteConceptDialogFragment.DeleteConceptDialogListener) {
            mListener = (DeleteConceptDialogFragment.DeleteConceptDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DeleteConceptDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        if (getActivity() instanceof DeleteConceptDialogFragment.DeleteConceptDialogListener) {
            mListener = (DeleteConceptDialogFragment.DeleteConceptDialogListener) getActivity();
        } else {
            throw new RuntimeException(getActivity().toString()
                    + " must implement DeleteConceptDialogListener");
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.edit_concept_delete_confirm)
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDeleteConfirmClick(DeleteConceptDialogFragment.this, currentConceptId);
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

    public interface DeleteConceptDialogListener {
        void onDeleteConfirmClick(DialogFragment dialog, Long currentConceptId);
    }

}
package cat.dme.smart.marcopolo.fragments.trip.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Trip;


/**
 * Dialog to confirm @{link {@link Trip}} deletion.
 *
 * Created by VIddA Software - DME Creaciones.
 */

public class DeleteTripDialogFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "currentTripId";

    private Long currentTripId;

    private DeleteTripDialogListener mListener;

    public static DeleteTripDialogFragment newInstance(Long currentTripId) {
        DeleteTripDialogFragment fragment = new DeleteTripDialogFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, currentTripId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.currentTripId = this.getArguments().getLong(ARG_PARAM1);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DeleteTripDialogFragment.DeleteTripDialogListener) {
            mListener = (DeleteTripDialogFragment.DeleteTripDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DeleteTripDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        if (getActivity() instanceof DeleteTripDialogFragment.DeleteTripDialogListener) {
            mListener = (DeleteTripDialogFragment.DeleteTripDialogListener) getActivity();
        } else {
            throw new RuntimeException(getActivity().toString()
                    + " must implement DeleteTripDialogListener");
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.edit_trip_delete_confirm)
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDeleteConfirmClick(DeleteTripDialogFragment.this, currentTripId);
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

    public interface DeleteTripDialogListener {
        void onDeleteConfirmClick(DialogFragment dialog, Long currentTripId);
    }

}
package cat.dme.smart.marcopolo.activities;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.dao.impl.PayerDaoImpl;
import cat.dme.smart.marcopolo.fragments.trip.EditPayerFragment;
import cat.dme.smart.marcopolo.fragments.trip.dialog.DeletePayerDialogFragment;
import cat.dme.smart.marcopolo.model.Payer;

/**
 * Activity to create, delete and update @{link {@link Payer}} details.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class EditPayerActivity extends BaseMenuActivity implements EditPayerFragment.OnEditPayerFragmentListener,
                                                                    DeletePayerDialogFragment.DeletePayerDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity_payer);
        this.configureToolbar();

        // Retrieves current payer and trip
        Payer currentPayer = (Payer) this.getIntent().getSerializableExtra(this.getString(R.string.global_current_payer));
        Long currentTripId = null;
        if (currentPayer!=null) {
            currentTripId = currentPayer.getTripId();
        } else {
            currentTripId = this.getIntent().getLongExtra(this.getString(R.string.global_current_trip_id), 0);
        }

        //Changues edit trip data fragment.
        this.getSupportFragmentManager().beginTransaction().replace(R.id.edit_fragment_payer, EditPayerFragment.newInstance(currentPayer, currentTripId)).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public static Intent runActivity(Context context, Long currentTripId) {
        Intent intent = new Intent(context, EditPayerActivity.class);
        intent.putExtra(context.getString(R.string.global_current_trip_id), currentTripId);
        return intent;
    }

    public static Intent runActivity(Context context, Payer payer) {
        Intent intent = new Intent(context, EditPayerActivity.class);
        intent.putExtra(context.getString(R.string.global_current_payer), payer);
        return intent;
    }

    @Override
    public void onEditFragmentCancel() {
        finish();
    }

    @Override
    public void onEditFragmentSaveNew(Payer payer) {
        Long newPayerId = PayerDaoImpl.getInstance().save(payer);
        payer.set_id(newPayerId);
        finish();
        startActivity(TripActivity.runActivity(this, payer.get_id()));
    }

    @Override
    public void onEditFragmentUpdate(Payer payer) {
        PayerDaoImpl.getInstance().update(payer);
        finish();
    }

    @Override
    public void onEditFragmentDelete(Long payerId) {
        DialogFragment dialog = DeletePayerDialogFragment.newInstance(payerId);
        dialog.show(this.getFragmentManager(), getString(R.string.edit_payer_delete_confirm));
    }

    @Override
    public void onDeleteConfirmClick(DialogFragment dialog, Long currentPayerId) {
        PayerDaoImpl.getInstance().delete(currentPayerId);
        finish();
    }

}


package cat.dme.smart.marcopolo.activities;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.business.impl.TripBOImpl;
import cat.dme.smart.marcopolo.dao.impl.PaymentMethodDaoImpl;
import cat.dme.smart.marcopolo.fragments.trip.EditPaymentMethodFragment;
import cat.dme.smart.marcopolo.fragments.trip.dialog.DeletePaymentMethodDialogFragment;
import cat.dme.smart.marcopolo.model.PaymentMethod;
import cat.dme.smart.marcopolo.model.Payer;

/**
 * Activity to create, delete and update @{link {@link PaymentMethod}} details.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class EditPaymentMethodActivity extends BaseMenuActivity implements EditPaymentMethodFragment.OnEditPaymentMethodFragmentListener,
                                                                     DeletePaymentMethodDialogFragment.DeletePaymentMethodDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity_payment_method);
        this.configureToolbar();

        // Retrieves current paymentMethod and trip
        PaymentMethod currentPaymentMethod = (PaymentMethod) this.getIntent().getSerializableExtra(this.getString(R.string.global_current_payment_method));
        Long currentTripId = null;
        if (currentPaymentMethod!=null) {
            currentTripId = currentPaymentMethod.getTripId();
        } else {
            currentTripId = this.getIntent().getLongExtra(this.getString(R.string.global_current_trip_id), 0);
        }

        //Changues edit trip data fragment.
        this.getSupportFragmentManager().beginTransaction().replace(R.id.edit_fragment_payment_method, EditPaymentMethodFragment.newInstance(currentPaymentMethod, currentTripId)).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public static Intent runActivity(Context context, Long currentTripId) {
        Intent intent = new Intent(context, EditPaymentMethodActivity.class);
        intent.putExtra(context.getString(R.string.global_current_trip_id), currentTripId);
        return intent;
    }

    public static Intent runActivity(Context context, PaymentMethod paymentMethod) {
        Intent intent = new Intent(context, EditPaymentMethodActivity.class);
        intent.putExtra(context.getString(R.string.global_current_payment_method), paymentMethod);
        return intent;
    }

    @Override
    public void onEditFragmentCancel() {
        finish();
    }

    @Override
    public void onEditFragmentSaveNew(PaymentMethod paymentMethod) {
        Long newPaymentMethodId = PaymentMethodDaoImpl.getInstance().save(paymentMethod);
        paymentMethod.set_id(newPaymentMethodId);
        finish();
        startActivity(TripActivity.runActivity(this, paymentMethod.get_id()));
    }

    @Override
    public void onEditFragmentUpdate(PaymentMethod paymentMethod) {
        PaymentMethodDaoImpl.getInstance().update(paymentMethod);
        finish();
    }

    @Override
    public void onEditFragmentDelete(Long paymentMethodId) {
        DialogFragment dialog = DeletePaymentMethodDialogFragment.newInstance(paymentMethodId);
        dialog.show(this.getFragmentManager(), getString(R.string.edit_payment_method_delete_confirm));
    }

    @Override
    public void onDeleteConfirmClick(DialogFragment dialog, Long currentPaymentMethodId) {
        TripBOImpl.getInstance(null).deletePaymentMethod(currentPaymentMethodId);
        finish();
    }

}


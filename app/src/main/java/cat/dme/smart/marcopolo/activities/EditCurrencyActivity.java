package cat.dme.smart.marcopolo.activities;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.business.impl.TripBOImpl;
import cat.dme.smart.marcopolo.dao.impl.CurrencyDaoImpl;
import cat.dme.smart.marcopolo.fragments.trip.EditCurrencyFragment;
import cat.dme.smart.marcopolo.fragments.trip.dialog.DeleteCurrencyDialogFragment;
import cat.dme.smart.marcopolo.model.Currency;
import cat.dme.smart.marcopolo.model.Payer;

/**
 * Activity to create, delete and update @{link {@link Currency}} details.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class EditCurrencyActivity extends BaseMenuActivity implements EditCurrencyFragment.OnEditCurrencyFragmentListener,
                                                                     DeleteCurrencyDialogFragment.DeleteCurrencyDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity_currency);
        this.configureToolbar();

        // Retrieves current currency and trip
        Currency currentCurrency = (Currency) this.getIntent().getSerializableExtra(this.getString(R.string.global_current_currency));
        Long currentTripId = null;
        if (currentCurrency!=null) {
            currentTripId = currentCurrency.getTripId();
        } else {
            currentTripId = this.getIntent().getLongExtra(this.getString(R.string.global_current_trip_id), 0);
        }

        //Changues edit trip data fragment.
        this.getSupportFragmentManager().beginTransaction().replace(R.id.edit_fragment_currency, EditCurrencyFragment.newInstance(currentCurrency, currentTripId)).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public static Intent runActivity(Context context, Long currentTripId) {
        Intent intent = new Intent(context, EditCurrencyActivity.class);
        intent.putExtra(context.getString(R.string.global_current_trip_id), currentTripId);
        return intent;
    }

    public static Intent runActivity(Context context, Currency currency) {
        Intent intent = new Intent(context, EditCurrencyActivity.class);
        intent.putExtra(context.getString(R.string.global_current_currency), currency);
        return intent;
    }

    @Override
    public void onEditFragmentCancel() {
        finish();
    }

    @Override
    public void onEditFragmentSaveNew(Currency currency) {
        Long newCurrencyId = CurrencyDaoImpl.getInstance().save(currency);
        currency.set_id(newCurrencyId);
        finish();
        startActivity(TripActivity.runActivity(this, currency.get_id()));
    }

    @Override
    public void onEditFragmentUpdate(Currency currency) {
        CurrencyDaoImpl.getInstance().update(currency);
        finish();
    }

    @Override
    public void onEditFragmentDelete(Long currencyId) {
        DialogFragment dialog = DeleteCurrencyDialogFragment.newInstance(currencyId);
        dialog.show(this.getFragmentManager(), getString(R.string.edit_currency_delete_confirm));
    }

    @Override
    public void onDeleteConfirmClick(DialogFragment dialog, Long currentCurrencyId) {
        TripBOImpl.getInstance(null).deleteCurrency(currentCurrencyId);
        finish();
    }

}


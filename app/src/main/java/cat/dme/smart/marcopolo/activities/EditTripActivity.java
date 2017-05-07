package cat.dme.smart.marcopolo.activities;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.business.impl.TripBOImpl;
import cat.dme.smart.marcopolo.dao.impl.TripDaoImpl;
import cat.dme.smart.marcopolo.fragments.trip.EditTripFragment;
import cat.dme.smart.marcopolo.fragments.trip.dialog.DeleteTripDialogFragment;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Activity to edit a trip basic configuration.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class EditTripActivity extends BaseMenuActivity implements EditTripFragment.OnEditTripFragmentListener,
                                                                    DeleteTripDialogFragment.DeleteTripDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity_trip);
        this.configureToolbar();

        // Retrieves current trip
        Trip currentTrip = (Trip) this.getIntent().getSerializableExtra(this.getString(R.string.global_current_trip));

        //Loads  selected TripId
        Long selectedTripId = this.getMyApplication().getCurrentTripId();

        //Changes edit trip data fragment.
        this.getSupportFragmentManager().beginTransaction().replace(R.id.edit_fragment_trip, EditTripFragment.newInstance(currentTrip, selectedTripId)).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public static Intent runActivity(Context context) {
        return new Intent(context, EditTripActivity.class);
    }

    public static Intent runActivity(Context context, Trip trip) {
        Intent intent = new Intent(context, EditTripActivity.class);
        intent.putExtra(context.getString(R.string.global_current_trip), trip);
        return intent;
    }

    @Override
    public void onEditFragmentCancel() {
        finish();
    }

    @Override
    public void onEditFragmentSaveNew(Trip trip) {
        Long newTripId = TripBOImpl.getInstance(this).createTrip(trip);
        trip.set_id(newTripId);
        finish();
        startActivity(TripActivity.runActivity(this, trip.get_id()));
    }

    @Override
    public void onEditFragmentUpdate(Trip trip) {
        TripDaoImpl.getInstance().update(trip);
        finish();
    }

    @Override
    public void onEditFragmentDelete(Long tripId) {
        DialogFragment dialog = DeleteTripDialogFragment.newInstance(tripId);
        dialog.show(this.getFragmentManager(), getString(R.string.edit_trip_delete_confirm));
    }

    @Override
    public void onDeleteConfirmClick(DialogFragment dialog, Long tripId) {
        TripBOImpl.getInstance(this).deleteTrip(tripId);
        finish();
        this.startActivity(SettingsActivity.runActivity(this));
    }

}


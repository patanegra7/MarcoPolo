package cat.dme.smart.marcopolo.activities;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.business.impl.TripBOImpl;
import cat.dme.smart.marcopolo.dao.impl.ConceptDaoImpl;
import cat.dme.smart.marcopolo.fragments.trip.EditConceptFragment;
import cat.dme.smart.marcopolo.fragments.trip.dialog.DeleteConceptDialogFragment;
import cat.dme.smart.marcopolo.model.Concept;

/**
 * Activity to create, delete and update @{link {@link Concept}} details.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class EditConceptActivity extends BaseMenuActivity implements EditConceptFragment.OnEditConceptFragmentListener,
                                                                     DeleteConceptDialogFragment.DeleteConceptDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity_concept);
        this.configureToolbar();

        // Retrieves current concept and trip
        Concept currentConcept = (Concept) this.getIntent().getSerializableExtra(this.getString(R.string.global_current_concept));
        Long currentTripId = null;
        if (currentConcept!=null) {
            currentTripId = currentConcept.getTripId();
        } else {
            currentTripId = this.getIntent().getLongExtra(this.getString(R.string.global_current_trip_id), 0);
        }

        //Changues edit trip data fragment.
        this.getSupportFragmentManager().beginTransaction().replace(R.id.edit_fragment_concept, EditConceptFragment.newInstance(currentConcept, currentTripId)).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public static Intent runActivity(Context context, Long currentTripId) {
        Intent intent = new Intent(context, EditConceptActivity.class);
        intent.putExtra(context.getString(R.string.global_current_trip_id), currentTripId);
        return intent;
    }

    public static Intent runActivity(Context context, Concept concept) {
        Intent intent = new Intent(context, EditConceptActivity.class);
        intent.putExtra(context.getString(R.string.global_current_concept), concept);
        return intent;
    }

    @Override
    public void onEditFragmentCancel() {
        finish();
    }

    @Override
    public void onEditFragmentSaveNew(Concept concept) {
        Long newConceptId = ConceptDaoImpl.getInstance().save(concept);
        concept.set_id(newConceptId);
        finish();
        startActivity(TripActivity.runActivity(this, concept.get_id()));
    }

    @Override
    public void onEditFragmentUpdate(Concept concept) {
        ConceptDaoImpl.getInstance().update(concept);
        finish();
    }

    @Override
    public void onEditFragmentDelete(Long conceptId) {
        DialogFragment dialog = DeleteConceptDialogFragment.newInstance(conceptId);
        dialog.show(this.getFragmentManager(), getString(R.string.edit_concept_delete_confirm));
    }

    @Override
    public void onDeleteConfirmClick(DialogFragment dialog, Long currentConceptId) {
        TripBOImpl.getInstance(null).deleteConcept(currentConceptId);
        finish();
    }

}


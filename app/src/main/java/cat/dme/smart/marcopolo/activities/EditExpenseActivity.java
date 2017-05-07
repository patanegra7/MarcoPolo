package cat.dme.smart.marcopolo.activities;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.dao.impl.ExpenseDaoImpl;
import cat.dme.smart.marcopolo.fragments.daily.DeleteExpenseDialogFragment;
import cat.dme.smart.marcopolo.fragments.daily.EditExpenseFragment;
import cat.dme.smart.marcopolo.model.Expense;

/**
 * Activity to create, delete and update @{link {@link Expense}} details.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class EditExpenseActivity extends BaseMenuActivity implements EditExpenseFragment.OnEditExpenseFragmentListener, DeleteExpenseDialogFragment.DeleteExpenseDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity_expense);
        this.configureToolbar();

        // Retrieves current concept and trip
        Expense currentExpense = (Expense) this.getIntent().getSerializableExtra(this.getString(R.string.global_current_expense));
        Long currentTripId = null;
        if (currentExpense!=null) {
            currentTripId = currentExpense.getTripId();
        } else {
            currentTripId = this.getIntent().getLongExtra(this.getString(R.string.global_current_trip_id), 0);
        }

        //Changes edit expense data fragment.
        this.getSupportFragmentManager().beginTransaction().replace(R.id.edit_fragment_expense, EditExpenseFragment.newInstance(currentExpense, currentTripId)).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public static Intent runActivity(Context context, Long currentTripId) {
        Intent intent = new Intent(context, EditExpenseActivity.class);
        intent.putExtra(context.getString(R.string.global_current_trip_id), currentTripId);
        return intent;
    }

    public static Intent runActivity(Context context, Expense expense) {
        Intent intent = new Intent(context, EditExpenseActivity.class);
        intent.putExtra(context.getString(R.string.global_current_expense), expense);
        return intent;
    }

    @Override
    public void onEditFragmentCancel() {
        finish();
    }

    @Override
    public void onEditFragmentSaveNew(Expense expense) {
        Long newExpenseId = ExpenseDaoImpl.getInstance().save(expense);
        expense.set_id(newExpenseId);
        finish();
        //startActivity(TripActivity.runActivity(this, expense.get_id()));
    }

    @Override
    public void onEditFragmentUpdate(Expense expense) {
        ExpenseDaoImpl.getInstance().update(expense);
        finish();
    }

    @Override
    public void onEditFragmentDelete(Long expenseId) {
        DialogFragment dialog = DeleteExpenseDialogFragment.newInstance(expenseId);
        dialog.show(this.getFragmentManager(), getString(R.string.edit_expense_delete_confirm));
    }

    @Override
    public void onDeleteConfirmClick(DialogFragment dialog, Long expenseId) {
        ExpenseDaoImpl.getInstance().delete(expenseId);
        finish();
    }

}


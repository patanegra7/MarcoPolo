package cat.dme.smart.marcopolo.business;

import java.util.List;

import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Currency;
import cat.dme.smart.marcopolo.model.Expense;
import cat.dme.smart.marcopolo.model.Payer;
import cat.dme.smart.marcopolo.model.PaymentMethod;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Expense Business object interface.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public interface ExpenseBO {

    /**
     * Gets a list of expenses with all its relationships
     *
     * @param tripId a {@link Trip} unique identifier.
     * @return a list of {@link Expense}.
     */
    List<Expense> getByTrip(Long tripId);

}

package cat.dme.smart.marcopolo.business;

import android.database.Cursor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cat.dme.smart.marcopolo.dao.impl.DbHelper;
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

    /**
     * Gets a list of trip expenses by trip and grouped by currencies.
     *
     * @param tripId a {@link Trip} unique identifier.
     * @return a list of {@link Expense}.
     */
    List<Expense> amountByCurrency(Long tripId);

    /**
     * Gets a list of trip expenses by trip and grouped by payers and currencies.
     *
     * @param tripId a {@link Trip} unique identifier.
     * @return a list of {@link Expense}.
     */
    List<Expense> amountByPayerAndCurrency(Long tripId);

    /**
     * Gets a list of trip expenses by trip and grouped by concepts and currencies.
     *
     * @param tripId a {@link Trip} unique identifier.
     * @return a list of {@link Expense}.
     */
    List<Expense> amountByConceptsAndCurrency(Long tripId);

    /**
     * Gets a list of trip expenses by trip and grouped by payment methods and currencies.
     *
     * @param tripId a {@link Trip} unique identifier.
     * @return a list of {@link Expense}.
     */
    List<Expense> amountByPaymentMethodsAndCurrency(Long tripId);

    /**
     * Calculates the total amount by currency from a list of {@link Expense}.
     *
     * @param expenses a list of {@link Expense}.
     * @return a Map with total amount by currency.
     */
    Map<String, BigDecimal> calculateTotalByCurrency(List<Expense> expenses);

    /**
     * TODO: JAVADOC
     * @param expenses
     * @param total
     * @return
     */
    Map<String, Map<String, Float>> percentageConcepts(List<Expense> expenses, Map<String, BigDecimal> total);

    //Map<String, Float> percentageCurrencies(List<Expense> expenses, Map<String, BigDecimal> total);

    Map<String, Map<String, Float>> percentagePayers(List<Expense> expenses, Map<String, BigDecimal> total);

    Map<String, Map<String, Float>> percentagePaymentMethods(List<Expense> expenses, Map<String, BigDecimal> total);
}

/**
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.business;

import cat.dme.smart.marcopolo.model.Trip;
import cat.dme.smart.marcopolo.model.Currency;
import cat.dme.smart.marcopolo.model.Payer;
import cat.dme.smart.marcopolo.model.PaymentMethod;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.TripConfig;

/**
 * Trip Business object interface.
 */
public interface TripBO {
    /**
     * Creates a @{link Trip} with its dependencies:
     * <i>{@link Currency}</i>
     * <i>{@link Payer}</i>
     * <i>{@link PaymentMethod}</i>
     * <i>{@link Concept}</i>
     * @param trip a {@link Trip} details to create.
     * @return a new {@link Trip} unique identifier.
     */
    Long createTrip(Trip trip);

    /**
     * Deletes a @{link Trip} with its dependencies:
     * <i>{@link Currency}</i>
     * <i>{@link Payer}</i>
     * <i>{@link PaymentMethod}</i>
     * <i>{@link Concept}</i>
     * @param tripId a {@link Trip} unique identifier.
     */
    void deleteTrip(Long tripId);

    /**
     * Returns a @{link Trip} with its dependencies:
     * <i>{@link Currency}</i>
     * <i>{@link Payer}</i>
     * <i>{@link PaymentMethod}</i>
     * <i>{@link Concept}</i>
     * @param tripId a {@link Trip} unique identifier..
     * @return a {@link TripConfig}.
     */
    TripConfig getTrip(Long tripId);

    /**
     * Deletes a concept if it isn't used by any trip diary entry.
     *
     * @param conceptId a {@link Concept} unique identifier.
     */
    void deleteConcept(Long conceptId);

    /**
     * Deletes a currency if it isn't used by any trip diary entry.
     *
     * @param currencyId a {@link Currency} unique identifier.
     */
    void deleteCurrency(Long currencyId);

    /**
     * Deletes a payer if it isn't used by any trip diary entry.
     *
     * @param payerId a {@link Payer} unique identifier.
     */
    void deletePayer(Long payerId);

    /**
     * Deletes a payment method if it isn't used by any trip diary entry..
     *
     * @param paymentMethodId a {@link PaymentMethod} unique identifier.
     */
    void deletePaymentMethod(Long paymentMethodId);

}

package cat.dme.smart.marcopolo.business;

import cat.dme.smart.marcopolo.dao.BaseDao;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Trip Business object interface.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public interface TripBO {
    /**
     * Creates a @{link Trip} with its dependencies:
     * <i>{@link cat.dme.smart.marcopolo.model.Currency}</i>
     * <i>{@link cat.dme.smart.marcopolo.model.Payer}</i>
     * <i>{@link cat.dme.smart.marcopolo.model.PaymentMethod}</i>
     * <i>{@link cat.dme.smart.marcopolo.model.Concept}</i>
     * @param trip a {@link Trip} details to create.
     * @return a new {@link Trip} unique identifier.
     */
    Long createTrip(Trip trip);

    /**
     * Deletes a @{link Trip} with its dependencies:
     * <i>{@link cat.dme.smart.marcopolo.model.Currency}</i>
     * <i>{@link cat.dme.smart.marcopolo.model.Payer}</i>
     * <i>{@link cat.dme.smart.marcopolo.model.PaymentMethod}</i>
     * <i>{@link cat.dme.smart.marcopolo.model.Concept}</i>
     * @param tripId a {@link Trip} unique identifier.
     */
    void deleteTrip(Long tripId);
}

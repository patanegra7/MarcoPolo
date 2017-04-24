package cat.dme.smart.marcopolo.dao;

import java.util.List;

import cat.dme.smart.marcopolo.model.PaymentMethod;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * PaymentMethod DAO interface.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public interface PaymentMethodDao extends BaseDao <PaymentMethod> {
    /**
     * TODO: Pendent JAVADOC
     * @param tripId
     * @return
     */
    List<PaymentMethod> getAllByTrip(Long tripId);

    /**
     * TODO: Pendent JAVADOC
     * @param tripId
     */
    void deleteByTrip(Long tripId);
}

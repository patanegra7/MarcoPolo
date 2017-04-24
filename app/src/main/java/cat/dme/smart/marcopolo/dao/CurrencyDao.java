package cat.dme.smart.marcopolo.dao;

import java.util.List;

import cat.dme.smart.marcopolo.model.Currency;

/**
 * Currency DAO interface.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public interface CurrencyDao extends BaseDao <Currency> {
    /**
     * TODO: Pendent JAVADOC
     * @param tripId
     * @return
     */
    List<Currency> getAllByTrip(Long tripId);

    /**
     * TODO: Pendent JAVADOC
     * @param tripId
     */
    void deleteByTrip(Long tripId);
}

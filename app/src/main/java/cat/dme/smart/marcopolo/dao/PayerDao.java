package cat.dme.smart.marcopolo.dao;

import java.util.List;

import cat.dme.smart.marcopolo.model.Payer;

/**
 * Payer DAO interface.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public interface PayerDao extends BaseDao <Payer> {
    /**
     * TODO: Pendent JAVADOC
     * @param tripId
     * @return
     */
    List<Payer> getAllByTrip(Long tripId);

    /**
     * TODO: Pendent JAVADOC
     * @param tripId
     */
    void deleteByTrip(Long tripId);
}

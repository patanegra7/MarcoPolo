package cat.dme.smart.marcopolo.dao;

import java.util.List;

import cat.dme.smart.marcopolo.model.Concept;

/**
 * Concept DAO interface.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public interface ConceptDao extends BaseDao<Concept> {
    /**
     * TODO: Pendent JAVADOC
     * @param tripId
     * @return
     */
    List<Concept> getAllByTrip(Long tripId);

    /**
     * TODO: Pendent JAVADOC
     * @param tripId
     */
    void deleteByTrip(Long tripId);
}

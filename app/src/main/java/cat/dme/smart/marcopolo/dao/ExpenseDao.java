package cat.dme.smart.marcopolo.dao;

import java.util.List;

import cat.dme.smart.marcopolo.model.Expense;

/**
 * Expense DAO interface.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public interface ExpenseDao extends BaseDao <Expense> {

    /**
     * TODO: Pendent JAVADOC
     * @param tripId
     * @return
     */
    List<Expense> getAllByTrip(Long tripId);

    /**
     * TODO: Pendent JAVADOC
     * @param tripId
     */
    void deleteByTrip(Long tripId);

}

/*
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cat.dme.smart.marcopolo.model.Expense;

/**
 * Expense DAO interface.
 */
public interface ExpenseDao {

/*    @Query("SELECT * from expense WHERE id = :id")
    LiveData<Expense> get(Long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long save(Expense expense);

    @Update
    void update(Expense expense);

    @Delete
    void delete(Expense expense);

    @Query("SELECT * from expense WHERE tripId = :tripId")
    LiveData<List<Expense>> getAll(Long tripId);

    @Query("DELETE FROM expense WHERE tripId = :tripId")
    void deleteAll(Long tripId);*/

}

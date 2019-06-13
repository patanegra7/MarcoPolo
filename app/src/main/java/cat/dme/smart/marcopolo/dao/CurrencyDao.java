/*
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cat.dme.smart.marcopolo.model.Currency;

/**
 * Currency DAO interface.
 */
@Dao
public interface CurrencyDao {

    @Query("SELECT * from currency WHERE id = :id")
    LiveData<Currency> get(Long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long save(Currency currency);

    @Update
    void update(Currency currency);

    @Delete
    void delete(Currency currency);

    @Query("SELECT * from currency WHERE tripId = :tripId")
    LiveData<List<Currency>> getAll(Long tripId);

    @Query("DELETE FROM currency WHERE tripId = :tripId")
    void deleteAll(Long tripId);

}

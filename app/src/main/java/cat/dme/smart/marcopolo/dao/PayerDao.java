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

import cat.dme.smart.marcopolo.model.Payer;

/**
 * Payer DAO interface.
 */
@Dao
public interface PayerDao {

    @Query("SELECT * from payer WHERE id = :id")
    LiveData<Payer> get(Long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long save(Payer payer);

    @Update
    void update(Payer payer);

    @Delete
    void delete(Payer payer);

    @Query("SELECT * from payer WHERE tripId = :tripId")
    LiveData<List<Payer>> getAll(Long tripId);

    @Query("DELETE FROM payer WHERE tripId = :tripId")
    void deleteAll(Long tripId);

}

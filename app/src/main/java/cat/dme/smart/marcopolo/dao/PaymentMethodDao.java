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

import cat.dme.smart.marcopolo.model.PaymentMethod;

/**
 * PaymentMethod DAO interface.
 */
@Dao
public interface PaymentMethodDao {

    @Query("SELECT * from paymentmethod WHERE id = :id")
    LiveData<PaymentMethod> get(Long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long save(PaymentMethod paymentMethod);

    @Update
    void update(PaymentMethod paymentMethod);

    @Delete
    void delete(PaymentMethod paymentMethod);

    @Query("SELECT * from paymentmethod WHERE tripId = :tripId")
    LiveData<List<PaymentMethod>> getAll(Long tripId);

    @Query("DELETE FROM paymentmethod WHERE tripId = :tripId")
    void deleteAll(Long tripId);

}

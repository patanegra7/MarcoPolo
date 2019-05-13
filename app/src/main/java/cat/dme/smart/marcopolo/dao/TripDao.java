/**
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

import cat.dme.smart.marcopolo.model.Trip;

/**
 * Trip DAO.
 */
@Dao
public interface TripDao { //extends BaseDao <Trip> {

    @Query("SELECT * from trip ORDER BY destination ASC")
    LiveData<List<Trip>> getAll();

    @Query("SELECT * from trip WHERE id= :id")
    LiveData<Trip> get(Long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long save(Trip trip);

    @Update
    void update(Trip trip);

    @Delete
    void delete(Trip id);

}

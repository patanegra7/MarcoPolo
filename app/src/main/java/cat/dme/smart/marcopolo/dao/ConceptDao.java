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

import cat.dme.smart.marcopolo.model.Concept;

/**
 * Concept DAO interface.
 */
@Dao
public interface ConceptDao {

    @Query("SELECT * from concept WHERE id = :id")
    LiveData<Concept> get(Long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long save(Concept concept);

    @Update
    void update(Concept concept);

    @Delete
    void delete(Concept concept);

    @Query("SELECT * from concept WHERE tripId = :tripId")
    LiveData<List<Concept>> getAll(Long tripId);

    @Query("DELETE FROM concept WHERE tripId = :tripId")
    void deleteAll(Long tripId);
}

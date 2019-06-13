/**
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cat.dme.smart.marcopolo.dao.ConceptDao;
import cat.dme.smart.marcopolo.dao.MarcoPoloDatabase;
import cat.dme.smart.marcopolo.dao.TripDao;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Concept repository.
 */
public class ConceptRepository {
    private ConceptDao conceptDao;

    private ExecutorService executorService;

    /**
     * Default constructor.
     */
    public ConceptRepository(Application application) {
        MarcoPoloDatabase db = MarcoPoloDatabase.getDatabase(application);
        this.conceptDao = db.conceptDao();
        //this.trips = this.tripDao.getAll();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Concept>> getConcepts(Long tripId) {
        return this.conceptDao.getAll(tripId);
    }

    public Long insert(final Concept concept) {
        Future<Long> result = executorService.submit(new Callable<Long>() {
            @Override
            public Long call() {
                return conceptDao.save(concept);
            }
        });
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("No puedorrr!", e);
        }

    }

}





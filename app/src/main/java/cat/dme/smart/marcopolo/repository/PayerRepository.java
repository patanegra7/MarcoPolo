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
import cat.dme.smart.marcopolo.dao.PayerDao;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Payer;

/**
 * Payer repository.
 */
public class PayerRepository {
    private PayerDao payerDao;

    private ExecutorService executorService;

    /**
     * Default constructor.
     */
    public PayerRepository(Application application) {
        MarcoPoloDatabase db = MarcoPoloDatabase.getDatabase(application);
        this.payerDao = db.payerDao();
        //this.trips = this.tripDao.getAll();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Payer>> getPayers(Long tripId) {
        return this.payerDao.getAll(tripId);
    }

    public Long insert(final Payer payer) {
        Future<Long> result = executorService.submit(new Callable<Long>() {
            @Override
            public Long call() {
                return payerDao.save(payer);
            }
        });
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("No puedorrr!", e);
        }
    }
}





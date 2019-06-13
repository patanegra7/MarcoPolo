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
import cat.dme.smart.marcopolo.dao.CurrencyDao;
import cat.dme.smart.marcopolo.dao.MarcoPoloDatabase;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Currency;

/**
 * Currency repository.
 */
public class CurrencyRepository {
    private CurrencyDao currencyDao;

    private ExecutorService executorService;

    /**
     * Default constructor.
     */
    public CurrencyRepository(Application application) {
        MarcoPoloDatabase db = MarcoPoloDatabase.getDatabase(application);
        this.currencyDao = db.currencyDao();
        //this.trips = this.tripDao.getAll();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Currency>> getCurrencies(Long tripId) {
        return this.currencyDao.getAll(tripId);
    }

    public Long insert(final Currency currency) {
        Future<Long> result = executorService.submit(new Callable<Long>() {
            @Override
            public Long call() {
                return currencyDao.save(currency);
            }
        });
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("No puedorrr!", e);
        }
    }

}





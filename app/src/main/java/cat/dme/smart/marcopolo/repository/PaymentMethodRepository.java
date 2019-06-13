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
import cat.dme.smart.marcopolo.dao.PaymentMethodDao;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.PaymentMethod;

/**
 * PaymentMethod repository.
 */
public class PaymentMethodRepository {
    private PaymentMethodDao paymentMethodDao;

    private ExecutorService executorService;

    /**
     * Default constructor.
     */
    public PaymentMethodRepository(Application application) {
        MarcoPoloDatabase db = MarcoPoloDatabase.getDatabase(application);
        this.paymentMethodDao = db.paymentMethodDao();
        //this.trips = this.tripDao.getAll();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<PaymentMethod>> getPaymentMethods(Long tripId) {
        return this.paymentMethodDao.getAll(tripId);
    }


    public Long insert(final PaymentMethod paymentMethod) {
        Future<Long> result = executorService.submit(new Callable<Long>() {
            @Override
            public Long call() {
                return paymentMethodDao.save(paymentMethod);
            }
        });
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("No puedorrr!", e);
        }
    }
}





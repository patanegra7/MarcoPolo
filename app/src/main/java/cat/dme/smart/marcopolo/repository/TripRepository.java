/**
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cat.dme.smart.marcopolo.dao.MarcoPoloDatabase;
import cat.dme.smart.marcopolo.dao.TripDao;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Trip repository.
 */
public class TripRepository {
    private TripDao tripDao;
    //private LiveData<List<Trip>> trips;
    private ExecutorService executorService;

    /**
     * Default constructor.
     */
    public TripRepository(Application application) {
        MarcoPoloDatabase db = MarcoPoloDatabase.getDatabase(application);
        this.tripDao = db.tripDao();
        //this.trips = this.tripDao.getAll();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Trip>> getTrips() {
        return this.tripDao.getAll();
    }

    public Trip getTrip(Long id) {
        Future<Trip> result = executorService.submit(new Callable<Trip>() {
            @Override
            public Trip call() {
                return tripDao.get(id);
            }
        });
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("No puedorrr!", e);
        }


    }


    public Long insert(final Trip trip) {
        Future<Long> result = executorService.submit(new Callable<Long>() {
            @Override
            public Long call() {
                return tripDao.save(trip);
            }
        });
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("No puedorrr!", e);
        }
    }

    public void update(final Trip trip) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                tripDao.update(trip);
            }
        });
    }

    public void delete(final Trip trip) {
        executorService.execute(() -> tripDao.delete(trip));
    }

/*    public void insert(Trip trip) {
        new insertAsyncTask(this.tripDao).execute(trip); //.get()get();
    }

    private static class insertAsyncTask extends AsyncTask<Trip, Void, Long> {

        private TripDao mAsyncTaskDao;

        insertAsyncTask(TripDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(final Trip... params) {
            return mAsyncTaskDao.save(params[0]);
        }
    }

    public void update(Trip trip) {
        new updateAsyncTask(this.tripDao).execute(trip);
    }

    private static class updateAsyncTask extends AsyncTask<Trip, Void, Void> {

        private TripDao mAsyncTaskDao;

        updateAsyncTask(TripDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Trip... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }*/
}





package cat.dme.smart.marcopolo.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import cat.dme.smart.marcopolo.dao.MarcoPoloDatabase;
import cat.dme.smart.marcopolo.dao.TripDao;
import cat.dme.smart.marcopolo.model.Trip;

public class TripRepository {
    private TripDao tripDao;
    private LiveData<List<Trip>> trips;

    /**
     * Default constructor.
     */
    public TripRepository(Application application) {
        MarcoPoloDatabase db = MarcoPoloDatabase.getDatabase(application);
        this.tripDao = db.tripDao();
        this.trips = this.tripDao.getAll();
    }

    public LiveData<List<Trip>> getTrips() {
        return this.trips;
    }

    public void insert(Trip trip) {
        new insertAsyncTask(this.tripDao).execute(trip);
    }

    private static class insertAsyncTask extends AsyncTask<Trip, Void, Void> {

        private TripDao mAsyncTaskDao;

        insertAsyncTask(TripDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Trip... params) {
            mAsyncTaskDao.save(params[0]);
            return null;
        }
    }
}


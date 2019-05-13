package cat.dme.smart.marcopolo.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import cat.dme.smart.marcopolo.model.Trip;
import cat.dme.smart.marcopolo.repository.TripRepository;

/**
 * A {@link Trip} view model.
 */
public class TripViewModel extends AndroidViewModel {

    private TripRepository mRepository;

    private LiveData<List<Trip>> trips;

    /**
     * Default constructor.
     * @param application
     */
    public TripViewModel (Application application) {
        super(application);
        mRepository = new TripRepository(application);
        trips = mRepository.getTrips();
    }

    LiveData<List<Trip>> getTrips() { return this.trips; }

    public void insert(Trip trip) { mRepository.insert(trip); }

}

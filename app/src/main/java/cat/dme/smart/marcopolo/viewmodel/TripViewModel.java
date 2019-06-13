package cat.dme.smart.marcopolo.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Currency;
import cat.dme.smart.marcopolo.model.Payer;
import cat.dme.smart.marcopolo.model.PaymentMethod;
import cat.dme.smart.marcopolo.model.Trip;
import cat.dme.smart.marcopolo.model.TripVO;
import cat.dme.smart.marcopolo.repository.ConceptRepository;
import cat.dme.smart.marcopolo.repository.CurrencyRepository;
import cat.dme.smart.marcopolo.repository.PayerRepository;
import cat.dme.smart.marcopolo.repository.PaymentMethodRepository;
import cat.dme.smart.marcopolo.repository.TripRepository;

/**
 * A {@link Trip} view model.
 */
public class TripViewModel extends AndroidViewModel {

    private TripRepository mRepository;

    private ConceptRepository conceptRepository;

    private CurrencyRepository currencyRepository;

    private PayerRepository payerRepository;

    private PaymentMethodRepository paymentMethodRepository;

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

    public LiveData<List<Trip>> getTrips() { return this.trips; }

    public Trip getTrip(Long id) { return this.mRepository.getTrip(id); }

    public Long insert(Trip trip) {

        Long tripId = mRepository.insert(trip);

        //Currencies
        String[] currencyValues = this.getApplication().getResources().getStringArray(R.array.currencies_0);
        Currency currency1 = new Currency();
        currency1.setCode(currencyValues[0]);
        currency1.setName(currencyValues[1]);
        currency1.setSymbol(currencyValues[2]);
        currency1.setTripId(tripId);
        this.currencyRepository.insert(currency1);
        currencyValues = this.getApplication().getResources().getStringArray(R.array.currencies_1);
        Currency currency2 = new Currency();
        currency2.setCode(currencyValues[0]);
        currency2.setName(currencyValues[1]);
        currency2.setSymbol(currencyValues[2]);
        currency2.setTripId(tripId);
        this.currencyRepository.insert(currency2);

        //Concepts
        String[] conceptValues = this.getApplication().getResources().getStringArray(R.array.concepts);
        for (int i = 0; i < conceptValues.length; i++) {
            Concept concept = new Concept();
            concept.setName(conceptValues[i]);
            concept.setTripId(tripId);
            conceptRepository.insert(concept);
        }

        //Payers
        String[] payerValues = this.getApplication().getResources().getStringArray(R.array.payers);
        for (int i = 0; i < payerValues.length; i++) {
            Payer payer = new Payer();
            payer.setName(payerValues[i]);
            payer.setTripId(tripId);
            payerRepository.insert(payer);
        }

        //PayMethods
        String[] paymentMethodValues = this.getApplication().getResources().getStringArray(R.array.payment_methods);
        for (int i = 0; i < paymentMethodValues.length; i++) {
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setName(paymentMethodValues[i]);
            paymentMethod.setTripId(tripId);
            paymentMethodRepository.insert(paymentMethod);
        }

        return tripId;
    }

    public void update(Trip trip) { mRepository.update(trip); }

    public void delete(Long tripId) {
        mRepository.delete(this.getTrip(tripId));
    }

}
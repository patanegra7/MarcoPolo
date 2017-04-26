package cat.dme.smart.marcopolo.business.impl;

import android.content.Context;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.business.TripBO;
import cat.dme.smart.marcopolo.dao.impl.ConceptDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.CurrencyDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.PayerDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.PaymentMethodDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.TripDaoImpl;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Currency;
import cat.dme.smart.marcopolo.model.Payer;
import cat.dme.smart.marcopolo.model.PaymentMethod;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Trip Business object implementation.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class TripBOImpl implements TripBO {

    /**
     * Default constructor. Singleton implementation.
     */
    private TripBOImpl() {
        super();
    }

    /**
     * Singleton instance.
     */
    private static TripBO instance;

    /**
     * An application context.
     */
    private Context aContext;

    /**
     * Singleton implementation.
     *
     * @return an instance of {@link TripBOImpl}.
     */
    public static TripBO getInstance(Context context) {
        if(instance==null) {
            instance = new TripBOImpl();
        }
        if(context!=null) {
            ((TripBOImpl) instance).aContext = context;
        }
        return instance;
    }

    @Override
    public Long createTrip(Trip trip) {
        Long tripId = TripDaoImpl.getInstance().save(trip);
        //Currencies
        String[] currencyValues = this.aContext.getResources().getStringArray(R.array.currencies_0);
        Currency currency = new Currency();
        currency.setCode(currencyValues[0]);
        currency.setName(currencyValues[1]);
        currency.setSymbol(currencyValues[2]);
        currency.setTripId(tripId);
        CurrencyDaoImpl.getInstance().save(currency);
        currencyValues = this.aContext.getResources().getStringArray(R.array.currencies_1);
        currency = new Currency();
        currency.setCode(currencyValues[0]);
        currency.setName(currencyValues[1]);
        currency.setSymbol(currencyValues[2]);
        currency.setTripId(tripId);
        CurrencyDaoImpl.getInstance().save(currency);

        //Concepts
        String[] concepts = this.aContext.getResources().getStringArray(R.array.concepts);
        for (int i = 0; i < concepts.length; i++) {
            Concept concept = new Concept();
            concept.setName(concepts[i]);
            concept.setTripId(tripId);
            ConceptDaoImpl.getInstance().save(concept);
        }
        //Payers
        String[] payers = this.aContext.getResources().getStringArray(R.array.payers);
        for (int i = 0; i < payers.length; i++) {
            Payer payer = new Payer();
            payer.setName(payers[i]);
            payer.setTripId(tripId);
            PayerDaoImpl.getInstance().save(payer);
        }
        //PayMethods
        String[] paymentMethods = this.aContext.getResources().getStringArray(R.array.payment_methods);
        for (int i = 0; i < paymentMethods.length; i++) {
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setName(paymentMethods[i]);
            paymentMethod.setTripId(tripId);
            PaymentMethodDaoImpl.getInstance().save(paymentMethod);
        }
        return tripId;
    }

    @Override
    public void deleteTrip(Long tripId) {
        //Currencies
        CurrencyDaoImpl.getInstance().deleteByTrip(tripId);
        //Concepts
        ConceptDaoImpl.getInstance().deleteByTrip(tripId);
        //Payers
        PayerDaoImpl.getInstance().deleteByTrip(tripId);
        //PayMethods
        PaymentMethodDaoImpl.getInstance().deleteByTrip(tripId);
        //Trip
        TripDaoImpl.getInstance().delete(tripId);
    }

    @Override
    public void deleteConcept(Long conceptId) {
        ConceptDaoImpl.getInstance().delete(conceptId);
    }

    @Override
    public void deleteCurrency(Long currecyId) {
        CurrencyDaoImpl.getInstance().delete(currecyId);
    }

    @Override
    public void deletePayer(Long payerId) {
        PayerDaoImpl.getInstance().delete(payerId);
    }

    @Override
    public void deletePaymentMethod(Long paymentMethodId) {
        PaymentMethodDaoImpl.getInstance().delete(paymentMethodId);
    }
}

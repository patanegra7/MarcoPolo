package cat.dme.smart.marcopolo.dao.impl;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import cat.dme.smart.marcopolo.dao.PaymentMethodDao;
import cat.dme.smart.marcopolo.model.PaymentMethod;
import nl.qbusict.cupboard.CupboardFactory;
import nl.qbusict.cupboard.QueryResultIterable;

/**
 * PaymentMethod DAO implementation.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class PaymentMethodDaoImpl implements PaymentMethodDao {

    /**
     * Default constructor. Singleton implementation.
     */
    private PaymentMethodDaoImpl() {
        super();
    }

    /**
     * Singleton instance.
     */
    private static PaymentMethodDao instance;

    /**
     * Singleton implementation.
     *
     * @return an instance of {@link PaymentMethodDaoImpl}.
     */
    public static PaymentMethodDao getInstance() {
        if(instance==null) {
            instance = new PaymentMethodDaoImpl();
        }
        return instance;
    }

    @Override
    public List<PaymentMethod> getAll() {
        // Get the cursor for this query
        Cursor paymentMethodsCursor = CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).query(PaymentMethod.class).getCursor();
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        try {
            // Iterate trips
            QueryResultIterable<PaymentMethod> itr = CupboardFactory.cupboard().withCursor(paymentMethodsCursor).iterate(PaymentMethod.class);
            for (PaymentMethod paymentMethod : itr) {
                paymentMethods.add(paymentMethod);
            }
        } finally {
            // close the cursor
            paymentMethodsCursor.close();
        }
        return paymentMethods;
    }

    @Override
    public List<PaymentMethod> getAllByTrip(Long tripId) {
        // Get the cursor for this query
        Cursor paymentMethodsCursor = CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).
                query(PaymentMethod.class).withSelection("tripId = ?", tripId.toString()).getCursor();
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        try {
            // Iterate trips
            QueryResultIterable<PaymentMethod> itr = CupboardFactory.cupboard().withCursor(paymentMethodsCursor).iterate(PaymentMethod.class);
            for (PaymentMethod paymentMethod : itr) {
                paymentMethods.add(paymentMethod);
            }
        } finally {
            // close the cursor
            paymentMethodsCursor.close();
        }
        return paymentMethods;
    }

    @Override
    public PaymentMethod get(Long id) {
        return CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).get(PaymentMethod.class, id);
    }

    @Override
    public Long save(PaymentMethod paymentMethod) {
        return CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).put(paymentMethod);
    }

    @Override
    public void update(PaymentMethod paymentMethod) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).put(paymentMethod);
    }

    @Override
    public void delete(Long id) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).delete(PaymentMethod.class, id);
    }

    @Override
    public void deleteByTrip(Long tripId) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).delete(PaymentMethod.class, "tripId = ?", tripId.toString());
    }
}

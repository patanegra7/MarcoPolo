package cat.dme.smart.marcopolo.dao.impl;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import cat.dme.smart.marcopolo.dao.PayerDao;
import cat.dme.smart.marcopolo.model.Payer;
import nl.qbusict.cupboard.CupboardFactory;
import nl.qbusict.cupboard.QueryResultIterable;

/**
 * Payer DAO implementation.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class PayerDaoImpl implements PayerDao {

    /**
     * Default constructor. Singleton implementation.
     */
    private PayerDaoImpl() {
        super();
    }

    /**
     * Singleton instance.
     */
    private static PayerDao instance;

    /**
     * Singleton implementation.
     *
     * @return an instance of {@link PayerDaoImpl}.
     */
    public static PayerDao getInstance() {
        if(instance==null) {
            instance = new PayerDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Payer> getAll() {
        // Get the cursor for this query
        Cursor payersCursor = CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).query(Payer.class).getCursor();
        List<Payer> payers = new ArrayList<>();
        try {
            // Iterate trips
            QueryResultIterable<Payer> itr = CupboardFactory.cupboard().withCursor(payersCursor).iterate(Payer.class);
            for (Payer payer : itr) {
                payers.add(payer);
            }
        } finally {
            // close the cursor
            payersCursor.close();
        }
        return payers;
    }

    @Override
    public List<Payer> getAllByTrip(Long tripId) {
        // Get the cursor for this query
        Cursor payersCursor = CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).
                query(Payer.class).withSelection("tripId = ?", tripId.toString()).getCursor();
        List<Payer> payers = new ArrayList<>();
        try {
            // Iterate trips
            QueryResultIterable<Payer> itr = CupboardFactory.cupboard().withCursor(payersCursor).iterate(Payer.class);
            for (Payer payer : itr) {
                payers.add(payer);
            }
        } finally {
            // close the cursor
            payersCursor.close();
        }
        return payers;
    }

    @Override
    public Payer get(Long id) {
        return CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).get(Payer.class, id);
    }

    @Override
    public Long save(Payer payer) {
        return CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).put(payer);
    }

    @Override
    public void update(Payer payer) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).put(payer);
    }

    @Override
    public void delete(Long id) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).delete(Payer.class, id);
    }

    @Override
    public void deleteByTrip(Long tripId) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).delete(Payer.class, "tripId = ?", tripId.toString());
    }
}

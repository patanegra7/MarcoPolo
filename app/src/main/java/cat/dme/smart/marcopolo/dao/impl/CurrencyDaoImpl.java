package cat.dme.smart.marcopolo.dao.impl;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import cat.dme.smart.marcopolo.dao.CurrencyDao;
import cat.dme.smart.marcopolo.dao.TripDao;
import cat.dme.smart.marcopolo.model.Currency;
import cat.dme.smart.marcopolo.model.Trip;
import nl.qbusict.cupboard.CupboardFactory;
import nl.qbusict.cupboard.QueryResultIterable;

/**
 * Currency DAO implementation.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class CurrencyDaoImpl implements CurrencyDao {

    /**
     * Default constructor. Singleton implementation.
     */
    private CurrencyDaoImpl() {
        super();
    }

    /**
     * Singleton instance.
     */
    private static CurrencyDao instance;

    /**
     * Singleton implementation.
     *
     * @return an instance of {@link CurrencyDaoImpl}.
     */
    public static CurrencyDao getInstance() {
        if(instance==null) {
            instance = new CurrencyDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Currency> getAll() {
        // Get the cursor for this query
        Cursor currenciesCursor = CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).query(Currency.class).getCursor();
        List<Currency> currencies = new ArrayList<>();
        try {
            // Iterate trips
            QueryResultIterable<Currency> itr = CupboardFactory.cupboard().withCursor(currenciesCursor).iterate(Currency.class);
            for (Currency currency : itr) {
                currencies.add(currency);
            }
        } finally {
            // close the cursor
            currenciesCursor.close();
        }
        return currencies;
    }

    @Override
    public List<Currency> getAllByTrip(Long tripId) {
        // Get the cursor for this query
        Cursor currenciesCursor = CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).
                query(Currency.class).withSelection("tripId = ?", tripId.toString()).getCursor();
        List<Currency> currencies = new ArrayList<>();
        try {
            // Iterate trips
            QueryResultIterable<Currency> itr = CupboardFactory.cupboard().withCursor(currenciesCursor).iterate(Currency.class);
            for (Currency currency : itr) {
                currencies.add(currency);
            }
        } finally {
            // close the cursor
            currenciesCursor.close();
        }
        return currencies;
    }

    @Override
    public Currency get(Long id) {
        return CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).get(Currency.class, id);
    }

    @Override
    public Long save(Currency currency) {
        return CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).put(currency);
    }

    @Override
    public void update(Currency currency) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).put(currency);
    }

    @Override
    public void delete(Long id) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).delete(Currency.class, id);
    }

    @Override
    public void deleteByTrip(Long tripId) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).delete(Currency.class, "tripId = ?", tripId.toString());
    }
}

package cat.dme.smart.marcopolo.dao.impl;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import cat.dme.smart.marcopolo.dao.ConceptDao;
import cat.dme.smart.marcopolo.dao.CurrencyDao;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Currency;
import nl.qbusict.cupboard.CupboardFactory;
import nl.qbusict.cupboard.QueryResultIterable;

/**
 * Concept DAO implementation.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class ConceptDaoImpl implements ConceptDao {

    /**
     * Default constructor. Singleton implementation.
     */
    private ConceptDaoImpl() {
        super();
    }

    /**
     * Singleton instance.
     */
    private static ConceptDao instance;

    /**
     * Singleton implementation.
     *
     * @return an instance of {@link ConceptDaoImpl}.
     */
    public static ConceptDao getInstance() {
        if(instance==null) {
            instance = new ConceptDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Concept> getAll() {
        // Get the cursor for this query
        Cursor conceptsCursor = CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).query(Concept.class).getCursor();
        List<Concept> concepts = new ArrayList<>();
        try {
            // Iterate trips
            QueryResultIterable<Concept> itr = CupboardFactory.cupboard().withCursor(conceptsCursor).iterate(Concept.class);
            for (Concept concept : itr) {
                concepts.add(concept);
            }
        } finally {
            // close the cursor
            conceptsCursor.close();
        }
        return concepts;
    }

    @Override
    public List<Concept> getAllByTrip(Long tripId) {
        // Get the cursor for this query
        Cursor conceptsCursor = CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).
                query(Concept.class).withSelection("tripId = ?", tripId.toString()).getCursor();
        List<Concept> concepts = new ArrayList<>();
        try {
            // Iterate trips
            QueryResultIterable<Concept> itr = CupboardFactory.cupboard().withCursor(conceptsCursor).iterate(Concept.class);
            for (Concept concept : itr) {
                concepts.add(concept);
            }
        } finally {
            // close the cursor
            conceptsCursor.close();
        }
        return concepts;
    }

    @Override
    public Concept get(Long id) {
        return CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).get(Concept.class, id);
    }

    @Override
    public Long save(Concept concept) {
        return CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).put(concept);
    }

    @Override
    public void update(Concept concept) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).put(concept);
    }

    @Override
    public void delete(Long id) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).delete(Concept.class, id);
    }

    @Override
    public void deleteByTrip(Long tripId) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).delete(Concept.class, "tripId = ?", tripId.toString());
    }
}

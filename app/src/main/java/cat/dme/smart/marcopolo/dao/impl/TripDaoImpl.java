package cat.dme.smart.marcopolo.dao.impl;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cat.dme.smart.marcopolo.contants.TripStatus;
import cat.dme.smart.marcopolo.dao.TripDao;
import cat.dme.smart.marcopolo.model.Trip;
import nl.qbusict.cupboard.CupboardFactory;
import nl.qbusict.cupboard.QueryResultIterable;

/**
 * Trip DAO implementation.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class TripDaoImpl implements TripDao {

    /**
     * Default constructor. Singleton implementation.
     */
    private TripDaoImpl() {
        super();
    }

    /**
     * Singleton instance.
     */
    private static TripDao instance;

    /**
     * Singleton implementation.
     *
     * @return an instance of {@link TripDaoImpl}.
     */
    public static TripDao getInstance() {
        if(instance==null) {
            instance = new TripDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Trip> getAll() {
        /*
        Trip trip = new Trip();
        trip.set_id(new Long(1));
        trip.setDestination("Delta de l'Ebre");
        trip.setDescription("Escapada de fin de semana a ver aves");
        trip.setEndDate(new Date());
        trip.setStartDate(new Date());
        trip.setStatus(TripStatus.FINISH);

        Trip trip2 = new Trip();
        trip2.set_id(new Long(2));
        trip2.setDestination("Roma");
        trip2.setDescription("Escapada de fin de semana");
        trip2.setEndDate(new Date());
        trip2.setStartDate(new Date());
        trip2.setStatus(TripStatus.LIVE);

        Trip trip3 = new Trip();
        trip3.set_id(new Long(3));
        trip3.setDestination("Suiza");
        trip3.setDescription("A pasar frío");
        trip3.setEndDate(new Date());
        trip3.setStartDate(new Date());
        trip3.setStatus(TripStatus.COMING_SOON);

        List<Trip> trips = new ArrayList<>();
        trips.add(trip);
        trips.add(trip2);
        trips.add(trip3);
*/
        // Get the cursor for this query
        Cursor tripsCursor = CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).query(Trip.class).getCursor();
        List<Trip> trips = new ArrayList<>();
        try {
            // Iterate trips
            QueryResultIterable<Trip> itr = CupboardFactory.cupboard().withCursor(tripsCursor).iterate(Trip.class);
            for (Trip trip : itr) {
                trips.add(trip);
            }
        } finally {
            // close the cursor
            tripsCursor.close();
        }
        return trips;
    }

    @Override
    public Trip get(Long id) {
        /*
        Trip trip = new Trip();
        trip.set_id(new Long(1));
        trip.setDestination("Delta de l'Ebre");
        trip.setDescription("Escapada de fin de semana a ver aves");
        trip.setEndDate(new Date());
        trip.setStartDate(new Date());
        trip.setStatus(TripStatus.FINISH);

        Trip trip2 = new Trip();
        trip2.set_id(new Long(2));
        trip2.setDestination("Roma");
        trip2.setDescription("Escapada de fin de semana");
        trip2.setEndDate(new Date());
        trip2.setStartDate(new Date());
        trip2.setStatus(TripStatus.LIVE);

        Trip trip3 = new Trip();
        trip3.set_id(new Long(3));
        trip3.setDestination("Suiza");
        trip3.setDescription("A pasar frío");
        trip3.setEndDate(new Date());
        trip3.setStartDate(new Date());
        trip3.setStatus(TripStatus.COMING_SOON);

        List<Trip> trips = new ArrayList<>();
        trips.add(trip);
        trips.add(trip2);
        trips.add(trip3);
        */
        return CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).get(Trip.class, id);
        //return trips.get(id.intValue()-1);
    }
// Get the first matching Bunny with name Max
 //       Bunny bunny = CupboardFactory.cupboard().withDatabase(db).query(Bunny.class).withSelection( "name = ?", "Max").get();

    @Override
    public Long save(Trip trip) {
        return CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).put(trip);
    }

    @Override
    public void update(Trip trip) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).put(trip);
/*        //Let's consider the problem in which we've forgotten to capitalize the 'M' in all bunnies named 'Max'
        ContentValues values = new ContentValues(1);
        values.put("name", "Max")
// update all bunnies where the name is 'max' to 'Max', thus capitalizing the name
        cupboard().withDatabase(db).update(Bunny.class, values, "name = ?", "max");*/
    }


    @Override
    public void delete(Long id) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).delete(Trip.class, id);

//        cupboard().withDatabase(db).delete(bunny);
//        cupboard().withDatabase(db).delete(Bunny.class, "name = ?", "Max");
    }
}

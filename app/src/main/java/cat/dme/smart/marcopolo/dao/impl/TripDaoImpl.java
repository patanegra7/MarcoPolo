package cat.dme.smart.marcopolo.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cat.dme.smart.marcopolo.contants.TripStatus;
import cat.dme.smart.marcopolo.dao.TripDao;
import cat.dme.smart.marcopolo.model.Trip;
import nl.qbusict.cupboard.CupboardFactory;

/**
 * Created by dmolina on 11/02/17.
 */
public class TripDaoImpl implements TripDao {
    @Override
    public List<Trip> getAll() {
        Trip trip = new Trip();
        trip.set_id(new Long(1));
        trip.setDestination("Roma");
        trip.setDescription("Escapada de fin de semana");
        trip.setEndDate(new Date());
        trip.setStartDate(new Date());
        trip.setStatus(TripStatus.LIVE);

        Trip trip2 = new Trip();
        trip2.set_id(new Long(2));
        trip2.setDestination("Suiza");
        trip2.setDescription("A pasar frío");
        trip2.setEndDate(new Date());
        trip2.setStartDate(new Date());
        trip2.setStatus(TripStatus.LIVE);

        List<Trip> trips = new ArrayList<>();
        trips.add(trip);
        trips.add(trip2);

/*        // Get the cursor for this query
        Cursor bunnies = cupboard().withDatabase(db).query(Bunny.class).getCursor();
        try {
            // Iterate Bunnys
            QueryResultIterable<Bunny> itr = cupboard().withCursor(bunnies).iterate(Bunny.class);
            for (Bunny bunny : itr) {
                // do something with bunny
            }
        } finally {
            // close the cursor
            bunnies.close();
        }*/

        return trips;
    }

    @Override
    public Trip get(Long id) {
        return CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).get(Trip.class, id);
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

/**
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import cat.dme.smart.marcopolo.model.Trip;

/**
 * Database configuration based in {@link Room}.
 */
@Database(entities = {Trip.class}, version = MarcoPoloDatabase.DATABASE_VERSION, exportSchema = false)
public abstract class MarcoPoloDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "marcopolo-db";
    public static final int DATABASE_VERSION = 1;

    public abstract TripDao tripDao();

    private static volatile MarcoPoloDatabase INSTANCE;

    public static MarcoPoloDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MarcoPoloDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MarcoPoloDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return INSTANCE;
    }
}
package cat.dme.smart.marcopolo.dao.converter;

import androidx.room.TypeConverter;

import java.util.Date;

import cat.dme.smart.marcopolo.model.TripStatus;

public class TripStatusConverter {

    @TypeConverter
    public static TripStatus toEnum(int number){
        return TripStatus.values()[number];
    }

    @TypeConverter
    public static int fromEnume(TripStatus status){
        return status.ordinal();
    }
}

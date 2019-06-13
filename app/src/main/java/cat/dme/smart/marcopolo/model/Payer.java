/**
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Payer model bean.
 */
@Entity
public class Payer implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;
    private String name;
    @NonNull
    private Long tripId;

    public Long getId() {
        return id;
    }

    public void setId(Long _id) {
        this.id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    @Override
    public String toString() {
        return "Payer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tripId=" + tripId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payer payer = (Payer) o;
        return id.equals(payer.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

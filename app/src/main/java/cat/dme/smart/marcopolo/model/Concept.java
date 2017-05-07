package cat.dme.smart.marcopolo.model;

import java.io.Serializable;

/**
 * Concept model bean.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class Concept implements Serializable {
    private Long _id;
    private String name;
    private Long tripId;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
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
        return "Concept{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", tripId=" + tripId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concept concept = (Concept) o;
        return _id.equals(concept._id);
    }

    @Override
    public int hashCode() {
        return _id.hashCode();
    }
}

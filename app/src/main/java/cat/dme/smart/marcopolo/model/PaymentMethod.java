package cat.dme.smart.marcopolo.model;

import java.io.Serializable;

/**
 * Payment method model bean.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class PaymentMethod implements Serializable {
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
        return "PaymentMethod{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", tripId=" + tripId +
                '}';
    }
}

package cat.dme.smart.marcopolo.model;

import java.io.Serializable;

/**
 * Currency model bean.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class Currency implements Serializable {
    private Long _id;
    private String code;
    private String symbol;
    private String name;
    private Long tripId;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
        return "Currency{" +
                "_id=" + _id +
                ", code='" + code + '\'' +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", tripId=" + tripId +
                '}';
    }
}

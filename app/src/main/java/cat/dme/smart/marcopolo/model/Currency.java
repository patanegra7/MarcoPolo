package cat.dme.smart.marcopolo.model;

import java.io.Serializable;
import java.math.BigDecimal;

import nl.qbusict.cupboard.annotation.Ignore;

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
    @Ignore
    private Boolean main;
    @Ignore
    private BigDecimal changeRate;
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

    public Boolean getMain() {
        return this.main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public BigDecimal getChangeRate() {
        return this.changeRate;
    }

    public void setChangeRate(BigDecimal changeRate) {
        this.changeRate = changeRate;
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
                ", main='" + main + '\'' +
                ", changeRate='" + changeRate + '\'' +
                ", tripId=" + tripId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return _id.equals(currency._id);
    }

    @Override
    public int hashCode() {
        return _id.hashCode();
    }
}

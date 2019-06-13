/*
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Currency model bean.
 */
@Entity
public class Currency implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String code;
    private String symbol;
    private String name;
    @NonNull
    private Long tripId = -1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", code='" + code + '\'' +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", tripId=" + tripId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return id.equals(currency.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

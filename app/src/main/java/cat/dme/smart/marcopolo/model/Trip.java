/*
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

import cat.dme.smart.marcopolo.dao.converter.DateConverter;
import cat.dme.smart.marcopolo.dao.converter.TripStatusConverter;

/**
 * Trip model bean.
 */
@Entity
@TypeConverters({DateConverter.class, TripStatusConverter.class})
public class Trip implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;
    private String destination;
    private Date startDate;
    private Date endDate;
    private String description;
    private TripStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Trip{" +
                " id=" + id +
                ", destination='" + destination + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id.equals(trip.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

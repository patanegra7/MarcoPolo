package cat.dme.smart.marcopolo.model;

import java.io.Serializable;
import java.util.Date;

import cat.dme.smart.marcopolo.contants.TripStatus;

/**
 * Trip model bean.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class Trip implements Serializable {

    private Long _id;
    private String destination;
    private Date startDate;
    private Date endDate;
    private String description;
    private TripStatus status;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
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
                "_id=" + _id +
                ", destination='" + destination + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}

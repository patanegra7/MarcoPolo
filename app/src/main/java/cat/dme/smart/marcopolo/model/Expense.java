/**
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cat.dme.smart.marcopolo.dao.converter.DateConverter;

/**
 * Expense model bean.
 */
@Entity
@TypeConverters({DateConverter.class})
public class Expense implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;
    @NonNull
    private Long tripId;
    private Date date;

    private Long conceptId;
    private String description;
    private BigDecimal amount;
    private Long currencyId;
    private Long payerId;
    private Long paymentMethodId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getConceptId() {
        return conceptId;
    }

    public void setConceptId(Long conceptId) {
        this.conceptId = conceptId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", tripId=" + tripId +
                ", date=" + date +
                ", conceptId=" + conceptId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", currencyId=" + currencyId +
                ", payerId=" + payerId +
                ", paymentMethodId=" + paymentMethodId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return id.equals(expense.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

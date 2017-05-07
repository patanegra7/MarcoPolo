package cat.dme.smart.marcopolo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Expense model bean.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class Expense implements Serializable {

    private Long _id;
    private Long tripId;
    private Date date;
    private Concept concept;
    private String description;
    private BigDecimal amount;
    private Currency currency;
    private Payer payer;
    private PaymentMethod paymentMethod;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
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

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "_id=" + _id +
                ", tripId=" + tripId +
                ", date=" + date +
                ", concept=" + concept +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", currency=" + currency +
                ", payer=" + payer +
                ", paymentMethod=" + paymentMethod +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return _id.equals(expense._id);
    }

    @Override
    public int hashCode() {
        return _id.hashCode();
    }
}

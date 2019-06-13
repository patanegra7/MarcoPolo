/**
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Expense value object.
 */
public class ExpenseVO implements Serializable {

    private Long id;
    private Long tripId;
    private Date date;
    private Concept concept;
    private String description;
    private BigDecimal amount;
    private Currency currency;
    private Payer payer;
    private PaymentMethod paymentMethod;

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
                "id=" + id +
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
        ExpenseVO expense = (ExpenseVO) o;
        return id.equals(expense.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

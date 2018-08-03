package cat.dme.smart.marcopolo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cat.dme.smart.marcopolo.contants.TripStatus;

/**
 * Trip configuration model bean, that contains reference of all related trip beans:
 * <li>@{@link List} of {@link Currency}</li>
 * <li>@{@link List} of {@link Concept}</li>
 * <li>@{@link List} of {@link Payer}</li>
 * <li>@{@link List} of {@link PaymentMethod}</li>
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class TripConfig implements Serializable {

    private Trip trip;
    private List<Concept> concepts;
    private List<Currency> currencies;
    private List<Payer> payers;
    private List<PaymentMethod> paymentMethods;
    private List<Expense> expenses;

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public List<Concept> getConcepts() {
        return concepts;
    }

    public void setConcepts(List<Concept> concepts) {
        this.concepts = concepts;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Payer> getPayers() {
        return payers;
    }

    public void setPayers(List<Payer> payers) {
        this.payers = payers;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "TripConfig{" +
                "trip=" + trip +
                ", concepts=" + concepts +
                ", currencies=" + currencies +
                ", payers=" + payers +
                ", paymentMethods=" + paymentMethods +
                ", expenses=" + expenses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TripConfig that = (TripConfig) o;

        if (trip != null ? !trip.equals(that.trip) : that.trip != null) return false;
        if (concepts != null ? !concepts.equals(that.concepts) : that.concepts != null)
            return false;
        if (currencies != null ? !currencies.equals(that.currencies) : that.currencies != null)
            return false;
        if (payers != null ? !payers.equals(that.payers) : that.payers != null) return false;
        if (paymentMethods != null ? !paymentMethods.equals(that.paymentMethods) : that.paymentMethods != null)
            return false;
        return expenses != null ? expenses.equals(that.expenses) : that.expenses == null;
    }

    @Override
    public int hashCode() {
        int result = trip != null ? trip.hashCode() : 0;
        result = 31 * result + (concepts != null ? concepts.hashCode() : 0);
        result = 31 * result + (currencies != null ? currencies.hashCode() : 0);
        result = 31 * result + (payers != null ? payers.hashCode() : 0);
        result = 31 * result + (paymentMethods != null ? paymentMethods.hashCode() : 0);
        result = 31 * result + (expenses != null ? expenses.hashCode() : 0);
        return result;
    }
}

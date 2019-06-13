/**
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

/**
 * Trip configuration model bean, that contains reference of all related trip beans:
 *
 * <li>@{@link List} of {@link Currency}</li>
 * <li>@{@link List} of {@link Concept}</li>
 * <li>@{@link List} of {@link Payer}</li>
 * <li>@{@link List} of {@link PaymentMethod}</li>
 */
public class TripVO implements Serializable {

    @Embedded
    private Trip trip;
    @Relation(parentColumn = "id", entityColumn = "tripId", entity = Concept.class)
    private List<Concept> concepts;
    @Relation(parentColumn = "id", entityColumn = "tripId", entity = Currency.class)
    private List<Currency> currencies;
    @Relation(parentColumn = "id", entityColumn = "tripId", entity = Payer.class)
    private List<Payer> payers;
    @Relation(parentColumn = "id", entityColumn = "tripId", entity = PaymentMethod.class)
    private List<PaymentMethod> paymentMethods;
/*    @Relation(parentColumn = "id", entityColumn = "tripId", entity = Expense.class)
    private List<Expense> expenses;*/

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

/*    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }*/

    @Override
    public String toString() {
        return "TripVO{" +
                "trip=" + trip +
                ", concepts=" + concepts +
                ", currencies=" + currencies +
                ", payers=" + payers +
                ", paymentMethods=" + paymentMethods +
 //               ", expenses=" + expenses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TripVO that = (TripVO) o;

        if (trip != null ? !trip.equals(that.trip) : that.trip != null) return false;
        if (concepts != null ? !concepts.equals(that.concepts) : that.concepts != null)
            return false;
        if (currencies != null ? !currencies.equals(that.currencies) : that.currencies != null)
            return false;
        if (payers != null ? !payers.equals(that.payers) : that.payers != null) return false;
        return paymentMethods != null ? paymentMethods.equals(that.paymentMethods) : that.paymentMethods == null;//return expenses != null ? expenses.equals(that.expenses) : that.expenses == null;
    }

    @Override
    public int hashCode() {
        int result = trip != null ? trip.hashCode() : 0;
        result = 31 * result + (concepts != null ? concepts.hashCode() : 0);
        result = 31 * result + (currencies != null ? currencies.hashCode() : 0);
        result = 31 * result + (payers != null ? payers.hashCode() : 0);
        result = 31 * result + (paymentMethods != null ? paymentMethods.hashCode() : 0);
//        result = 31 * result + (expenses != null ? expenses.hashCode() : 0);
        return result;
    }
}

package cat.dme.smart.marcopolo.business.impl;

import android.content.Context;

import java.util.List;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.business.ExpenseBO;
import cat.dme.smart.marcopolo.business.TripBO;
import cat.dme.smart.marcopolo.dao.impl.ConceptDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.CurrencyDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.ExpenseDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.PayerDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.PaymentMethodDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.TripDaoImpl;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Currency;
import cat.dme.smart.marcopolo.model.Expense;
import cat.dme.smart.marcopolo.model.Payer;
import cat.dme.smart.marcopolo.model.PaymentMethod;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * Expense Business object implementation.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class ExpenseBOImpl implements ExpenseBO {

    /**
     * Default constructor. Singleton implementation.
     */
    private ExpenseBOImpl() {
        super();
    }

    /**
     * Singleton instance.
     */
    private static ExpenseBO instance;

    /**
     * An application context.
     */
    private Context aContext;

    /**
     * Singleton implementation.
     *
     * @return an instance of {@link ExpenseBOImpl}.
     */
    public static ExpenseBO getInstance(Context context) {
        if(instance==null) {
            instance = new ExpenseBOImpl();
        }
        if(context!=null) {
            ((ExpenseBOImpl) instance).aContext = context;
        }
        return instance;
    }


    @Override
    public List<Expense> getByTrip(Long tripId) {
        List<Expense> expenses = ExpenseDaoImpl.getInstance().getAllByTrip(tripId);
        for (Expense expense : expenses) {
            expense.setConcept(ConceptDaoImpl.getInstance().get(expense.getConcept().get_id()));
            expense.setCurrency(CurrencyDaoImpl.getInstance().get(expense.getCurrency().get_id()));
            expense.setPayer(PayerDaoImpl.getInstance().get(expense.getPayer().get_id()));
            expense.setPaymentMethod(PaymentMethodDaoImpl.getInstance().get(expense.getPaymentMethod().get_id()));
        }
        return expenses;
    }
}

package cat.dme.smart.marcopolo.business.impl;

import android.content.Context;
import android.database.Cursor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.business.ExpenseBO;
import cat.dme.smart.marcopolo.business.TripBO;
import cat.dme.smart.marcopolo.dao.impl.ConceptDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.CurrencyDaoImpl;
import cat.dme.smart.marcopolo.dao.impl.DbHelper;
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
     * Query to get all trip expenses by trip and grouped by currency.
     */
    String AMOUNT_BY_CURRENCY = "select sum(amount), currency from Expense where tripId = ? group by currency;";

    /**
     * Query to get all trip expenses by trip and grouped by payers and currencies.
     */
    String AMOUNT_BY_PAYERS = "select sum(amount), payer, currency from Expense where tripId = ? group by payer, currency;";

    /**
     * Query to get all trip expenses by trip and grouped by concepts and currencies.
     */
    String AMOUNT_BY_CONCEPTS = "select sum(amount), concept, currency from Expense where tripId = ? group by concept, currency;";

    /**
     * Query to get all trip expenses by trip and grouped by payment methods and currencies.
     */
    String AMOUNT_BY_PAYMENT_METHODS = "select sum(amount), paymentMethod, currency from Expense where tripId = ? group by paymentMethod, currency;";

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

    @Override
    public List<Expense> amountByCurrency(Long tripId) {
        List<Expense> expenses = new ArrayList<>();
        Cursor expensesCursor = null;
        try {
            expensesCursor = DbHelper.getDbHelper().getWritableDatabase().rawQuery(AMOUNT_BY_CURRENCY, new String[]{tripId.toString()});
            while(expensesCursor.moveToNext()) {
                Expense expense = new Expense();
                expense.setAmount(new BigDecimal(expensesCursor.getString(0)));
                expense.setCurrency(CurrencyDaoImpl.getInstance().get(expensesCursor.getLong(1)));
                expenses.add(expense);
            }
        } finally {
            // close the cursor
            expensesCursor.close();
        }
        return expenses;
    }

    @Override
    public List<Expense> amountByPayerAndCurrency(Long tripId) {
        List<Expense> expenses = new ArrayList<>();
        Cursor expensesCursor = null;
        try {
            expensesCursor = DbHelper.getDbHelper().getWritableDatabase().rawQuery(AMOUNT_BY_PAYERS, new String[]{tripId.toString()});
            while(expensesCursor.moveToNext()) {
                Expense expense = new Expense();
                expense.setAmount(new BigDecimal(expensesCursor.getString(0)));
                expense.setPayer(PayerDaoImpl.getInstance().get(expensesCursor.getLong(1)));
                expense.setCurrency(CurrencyDaoImpl.getInstance().get(expensesCursor.getLong(2)));
                expenses.add(expense);
            }
        } finally {
            // close the cursor
            expensesCursor.close();
        }
        return expenses;
    }

    @Override
    public List<Expense> amountByConceptsAndCurrency(Long tripId) {
        List<Expense> expenses = new ArrayList<>();
        Cursor expensesCursor = null;
        try {
            expensesCursor = DbHelper.getDbHelper().getWritableDatabase().rawQuery(AMOUNT_BY_CONCEPTS, new String[]{tripId.toString()});
            while(expensesCursor.moveToNext()) {
                Expense expense = new Expense();
                expense.setAmount(new BigDecimal(expensesCursor.getString(0)));
                expense.setConcept(ConceptDaoImpl.getInstance().get(expensesCursor.getLong(1)));
                expense.setCurrency(CurrencyDaoImpl.getInstance().get(expensesCursor.getLong(2)));
                expenses.add(expense);
            }
        } finally {
            // close the cursor
            expensesCursor.close();
        }
        return expenses;
    }

    @Override
    public List<Expense> amountByPaymentMethodsAndCurrency(Long tripId) {
        List<Expense> expenses = new ArrayList<>();
        Cursor expensesCursor = null;
        try {
            expensesCursor = DbHelper.getDbHelper().getWritableDatabase().rawQuery(AMOUNT_BY_PAYMENT_METHODS, new String[]{tripId.toString()});
            while(expensesCursor.moveToNext()) {
                Expense expense = new Expense();
                expense.setAmount(new BigDecimal(expensesCursor.getString(0)));
                expense.setPaymentMethod(PaymentMethodDaoImpl.getInstance().get(expensesCursor.getLong(1)));
                expense.setCurrency(CurrencyDaoImpl.getInstance().get(expensesCursor.getLong(2)));
                expenses.add(expense);
            }
        } finally {
            // close the cursor
            expensesCursor.close();
        }
        return expenses;
    }

    @Override
    public Map<Currency, BigDecimal> calculateTotalByCurrency(List<Expense> expenses) {
        Map<Currency, BigDecimal> total = new HashMap<>();
        for (Expense expense : expenses) {
            Currency currency = expense.getCurrency();
            if (total.containsKey(currency)) {
                total.put(currency, total.get(currency).add(expense.getAmount()));
            } else {
                total.put(currency, expense.getAmount());
            }
        }
        return total;
    }

    private Float calculatePercentage(Expense expense, BigDecimal total) {
        return expense.getAmount().multiply(new BigDecimal("100").divide(total, MathContext.DECIMAL32)).floatValue();
    }

    @Override
    public Map<Currency, Map<String, Float>> percentageConcepts(List<Expense> expenses, Map<Currency, BigDecimal> total) {
        Map<Currency, Map<String, Float>> percentages = new HashMap<>();
        for(Expense expense: expenses) {
            Currency currency = expense.getCurrency();
            Map<String, Float> percentage = percentages.get(currency);
            if (percentage==null) {
                percentage = new HashMap<>();
                percentages.put(currency, percentage);
            }
            percentage.put(expense.getConcept().getName(), this.calculatePercentage(expense, total.get(currency)));
        }
        return percentages;
    }

    @Override
    public Map<Currency, Map<String, Float>> percentagePayers(List<Expense> expenses, Map<Currency, BigDecimal> total) {
        Map<Currency, Map<String, Float>> percentages = new HashMap<>();
        for(Expense expense: expenses) {
            Currency currency = expense.getCurrency();
            Map<String, Float> percentage = percentages.get(currency);
            if (percentage==null) {
                percentage = new HashMap<>();
                percentages.put(currency, percentage);
            }
            percentage.put(expense.getPayer().getName(), this.calculatePercentage(expense, total.get(currency)));
        }
        return percentages;
    }

    @Override
    public Map<Currency, Map<String, Float>> percentagePaymentMethods(List<Expense> expenses, Map<Currency, BigDecimal> total) {
        Map<Currency, Map<String, Float>> percentages = new HashMap<>();
        for(Expense expense: expenses) {
            Currency currency = expense.getCurrency();
            Map<String, Float> percentage = percentages.get(currency);
            if (percentage==null) {
                percentage = new HashMap<>();
                percentages.put(currency, percentage);
            }
            percentage.put(expense.getPaymentMethod().getName(), this.calculatePercentage(expense, total.get(currency)));
        }
        return percentages;
    }

}

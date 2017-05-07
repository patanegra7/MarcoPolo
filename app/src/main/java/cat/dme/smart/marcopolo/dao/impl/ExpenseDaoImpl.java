package cat.dme.smart.marcopolo.dao.impl;

import android.database.Cursor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cat.dme.smart.marcopolo.dao.ExpenseDao;
import cat.dme.smart.marcopolo.dao.TripDao;
import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Currency;
import cat.dme.smart.marcopolo.model.Expense;
import cat.dme.smart.marcopolo.model.Payer;
import cat.dme.smart.marcopolo.model.Trip;
import nl.qbusict.cupboard.CupboardFactory;
import nl.qbusict.cupboard.QueryResultIterable;

/**
 * Expense DAO implementation.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public class ExpenseDaoImpl implements ExpenseDao {

    /**
     * Default constructor. Singleton implementation.
     */
    private ExpenseDaoImpl() {
        super();
    }

    /**
     * Singleton instance.
     */
    private static ExpenseDao instance;

    /**
     * Singleton implementation.
     *
     * @return an instance of {@link ExpenseDaoImpl}.
     */
    public static ExpenseDao getInstance() {
        if(instance==null) {
            instance = new ExpenseDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Expense> getAll() {

        // Get the cursor for this query
        Cursor expensesCursor = CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).query(Expense.class).getCursor();
        List<Expense> expenses = new ArrayList<>();
        try {
            // Iterate expenses
            QueryResultIterable<Expense> itr = CupboardFactory.cupboard().withCursor(expensesCursor).iterate(Expense.class);
            for (Expense expense : itr) {
                expenses.add(expense);
            }
        } finally {
            // close the cursor
            expensesCursor.close();
        }
        return expenses;
    }

    @Override
    public List<Expense> getAllByTrip(Long tripId) {

        // Get the cursor for this query
        Cursor expensesCursor = CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).
                query(Expense.class).withSelection("tripId = ?", tripId.toString()).orderBy("date desc").getCursor();
        List<Expense> expenses = new ArrayList<>();
        try {
            // Iterate expenses
            QueryResultIterable<Expense> itr = CupboardFactory.cupboard().withCursor(expensesCursor).iterate(Expense.class);
            for (Expense expense : itr) {
                expenses.add(expense);
            }
        } finally {
            // close the cursor
            expensesCursor.close();
        }
        return expenses;
/*
        List<Expense> expenses = new ArrayList<>();

        Expense expense = new Expense();
        expense.setDate(new Date());
        Concept c = new Concept();
        c.setName("transport");
        expense.setConcept(c);
        Currency cu = new Currency();
        cu.setSymbol("$");
        expense.setCurrency(cu);
        expense.setAmount(new BigDecimal("12.5"));
        expense.setTripId(1L);

        Expense expense2 = new Expense();
        expense2.setDate(new Date());
        expense2.setConcept(c);
        expense2.setCurrency(cu);
        expense2.setAmount(new BigDecimal("10.5"));
        expense2.setTripId(1L);

        expenses.add(expense);
        expenses.add(expense2);

        return expenses;
        */
    }

    @Override
    public Expense get(Long id) {

        return CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getReadableDatabase()).get(Expense.class, id);

    }

    @Override
    public Long save(Expense expense) {
        return CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).put(expense);
    }

    @Override
    public void update(Expense expense) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).put(expense);
    }

    @Override
    public void delete(Long id) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).delete(Expense.class, id);
    }

    @Override
    public void deleteByTrip(Long tripId) {
        CupboardFactory.cupboard().withDatabase(DbHelper.getDbHelper().getWritableDatabase()).delete(Payer.class, "tripId = ?", tripId.toString());
    }
}

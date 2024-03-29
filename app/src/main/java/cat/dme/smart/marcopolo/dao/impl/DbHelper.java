package cat.dme.smart.marcopolo.dao.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cat.dme.smart.marcopolo.model.Concept;
import cat.dme.smart.marcopolo.model.Currency;
import cat.dme.smart.marcopolo.model.Expense;
import cat.dme.smart.marcopolo.model.Payer;
import cat.dme.smart.marcopolo.model.PaymentMethod;
import cat.dme.smart.marcopolo.model.Trip;
import nl.qbusict.cupboard.CupboardBuilder;
import nl.qbusict.cupboard.CupboardFactory;


/**
 * Created by dmolina on 12/02/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "marcopolo.db";
    private static final int DATABASE_VERSION = 3;
    private static DbHelper dbHelper;

    public static void initDbHelper(Context context) {
        dbHelper = new DbHelper(context);
    }

    public static DbHelper getDbHelper() {
        return dbHelper;
    }

    private DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        // register our models
        CupboardFactory.setCupboard(new CupboardBuilder().useAnnotations().build());
        CupboardFactory.cupboard().register(Trip.class);
        CupboardFactory.cupboard().register(Currency.class);
        CupboardFactory.cupboard().register(Payer.class);
        CupboardFactory.cupboard().register(PaymentMethod.class);
        CupboardFactory.cupboard().register(Concept.class);
        CupboardFactory.cupboard().register(Expense.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // this will ensure that all tables are created
        CupboardFactory.cupboard().withDatabase(db).createTables();
        // add indexes and other database tweaks in this method if you want

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this will upgrade tables, adding columns and new tables.
        // Note that existing columns will not be converted
        CupboardFactory.cupboard().withDatabase(db).upgradeTables();
        // do migration work if you have an alteration to make to your schema here

    }
}

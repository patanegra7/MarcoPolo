package cat.dme.smart.marcopolo;

import android.os.Bundle;
import android.widget.TextView;

import cat.dme.smart.marcopolo.dao.BaseDao;
import cat.dme.smart.marcopolo.dao.impl.DbHelper;

public class MainActivity extends BaseMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureToolbar();

        TextView tvCurrentTrip = (TextView) findViewById(R.id.current_trip);
        tvCurrentTrip.setText("Roma");

        //init db
        DbHelper.initDbHelper(this.getApplicationContext());

    }

}

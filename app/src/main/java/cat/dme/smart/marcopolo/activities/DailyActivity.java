package cat.dme.smart.marcopolo.activities;

import android.os.Bundle;

import cat.dme.smart.marcopolo.R;

/**
 * Created by str_dme on 25/07/14.
 */
public class DailyActivity extends BaseMenuActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        this.configureToolbar();
    }

    /*

        btnCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(intent);
            }
        });*/
}

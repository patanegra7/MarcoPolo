package cat.dme.smart.marcopolo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.dao.impl.TripDaoImpl;
import cat.dme.smart.marcopolo.model.global.MarcoPoloApplication;

/**
 * Base activity with common main menu.
 *
 * Created by VIddA Software - DME Creaciones.
 */
public abstract class BaseMenuActivity extends AppCompatActivity {

    protected MarcoPoloApplication getMyApplication() {
        return (MarcoPoloApplication)this.getApplication();
    }

    protected void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        //this.getSupportActionBar().setDisplayShowTitleEnabled(false);

        //this.getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        //this.getSupportActionBar().setDisplayUseLogoEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        String destination = this.getMyApplication().getCurrentTripDestination();
        if (destination!=null && destination.length()>0) {
            mTitle.setText(destination);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch(item.getItemId()) {
            case R.id.daily:
                if(this.getMyApplication().getCurrentTripId()!=0L) {
                    intent = new Intent(this, DailyActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.main_not_destination,  Toast.LENGTH_SHORT);
                    toast.show();
                }
                return (true);
            case R.id.summary:
                intent = new Intent(this, SummaryActivity.class);
                startActivity(intent);
                return(true);
            case R.id.settings:
                this.startActivity(SettingsActivity.runActivity(this));
                return(true);
            case R.id.export:
                intent = new Intent(this, ExportActivity.class);
                startActivity(intent);
                return(true);
            case R.id.about:
                this.startActivity(new Intent(this, CreditsActivity.class));
                return(true);
            case R.id.exit:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }
}

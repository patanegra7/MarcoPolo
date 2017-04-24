package cat.dme.smart.marcopolo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import cat.dme.smart.marcopolo.R;
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.daily:
                Intent intent = new Intent(this, DailyActivity.class);
                startActivity(intent);
                return(true);
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

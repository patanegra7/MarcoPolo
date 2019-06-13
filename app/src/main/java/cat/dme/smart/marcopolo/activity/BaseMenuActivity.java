/*
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.application.MarcoPoloApplication;

/**
 * Base activity with common main menu.
 */
public abstract class BaseMenuActivity extends AppCompatActivity {

    /**
     * @return A custom {@link android.app.Application}.
     */
    protected MarcoPoloApplication getMyApplication() {
        return (MarcoPoloApplication)this.getApplication();
    }

    protected void configureToolbar() {
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayShowTitleEnabled(true);

        //this.getSupportActionBar().setLogo(getDrawable(R.drawable.ic_launcher));
        //this.getSupportActionBar().setDisplayUseLogoEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update toolbar with trip destination
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
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
        Intent intent;
        switch(item.getItemId()) {
            case R.id.daily:
               if(this.getMyApplication().getCurrentTripId()!=0L) {
  /*                  intent = new Intent(this, DailyActivity.class);
                    startActivity(intent);*/
                   Toast.makeText(this.getApplicationContext(), "Not implemented", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.main_not_destination,  Toast.LENGTH_SHORT).show();
                }
                return (true);
            case R.id.summary:
                /*startActivity(SummaryActivity.runActivity(this));*/
                Toast.makeText(this.getApplicationContext(), "Not implemented", Toast.LENGTH_SHORT).show();
                return(true);
            case R.id.settings:
                this.startActivity(SettingsActivity.runActivity(this));
                return(true);
            case R.id.export:
                /*intent = new Intent(this, ExportActivity.class);
                startActivity(intent);*/
                Toast.makeText(this.getApplicationContext(), "Not implemented", Toast.LENGTH_SHORT).show();
                return(true);
            case R.id.about:
                this.startActivity(CreditsActivity.runActivity(this));
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

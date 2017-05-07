package cat.dme.smart.marcopolo.model.global;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import cat.dme.smart.marcopolo.R;
import cat.dme.smart.marcopolo.model.Trip;

/**
 * TODO: Explain class
 * Created by VIddA Software - DME Creaciones.
 */

public class MarcoPoloApplication extends Application {

    private Long currentTripId;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate ()
    {
        super.onCreate();
        // Setup handler for uncaught exceptions.
        Thread.setDefaultUncaughtExceptionHandler (new Thread.UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException (Thread thread, Throwable e)
            {
                handleUncaughtException (thread, e);
            }
        });
    }

    public void handleUncaughtException (Thread thread, Throwable e)
    {
        e.printStackTrace(); // not all Android versions will print the stack trace automatically

        System.exit(1); // kill off the crashed app
    }




    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     *
     * @return
     */
    public Long getCurrentTripId() {
        if(this.currentTripId==null) {
            SharedPreferences sharedPref = this.getApplicationContext().getSharedPreferences(this.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            this.currentTripId = sharedPref.getLong(this.getString(R.string.global_current_trip_id), 0);
        }
        return this.currentTripId;
    }

    /**
     *
     * @param currentTripId
     */
    public void setCurrentTripId(Long currentTripId) {
        this.currentTripId = currentTripId;
        SharedPreferences sharedPref = this.getApplicationContext().getSharedPreferences(this.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(this.getString(R.string.global_current_trip_id), currentTripId);
        editor.commit();
    }

    /**
     *
     */
    public void removeCurrentTripId() {
        this.currentTripId = null;
        SharedPreferences sharedPref = this.getApplicationContext().getSharedPreferences(this.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(this.getString(R.string.global_current_trip_id));
        editor.commit();
    }
}

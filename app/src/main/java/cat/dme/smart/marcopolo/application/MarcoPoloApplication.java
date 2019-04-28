/**
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import cat.dme.smart.marcopolo.R;

/**
 * Specific bean that maintains the application global status. Saves the current trip identifier and destination as shared preferences.
 */

public class MarcoPoloApplication extends Application {

    /**
     * Current trip identifier.
     */
    private Long currentTripId;

    /**
     * Current trip destination.
     */
    private String currentTripDestination;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate () {
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

    /**
     *  Global exception handler that print the stack trace qurh an error occurs.
     * @param thread the thread where the problem occurs.
     * @param e The exception.
     */
    public void handleUncaughtException (Thread thread, Throwable e) {
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
     * @return Returns the current trip identifier.
     */
    public Long getCurrentTripId() {
        if(this.currentTripId==null) {
            SharedPreferences sharedPref = this.getApplicationContext().getSharedPreferences(this.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            this.currentTripId = sharedPref.getLong(this.getString(R.string.global_current_trip_id), 0);
        }
        return this.currentTripId;
    }

    /**
     * Sets a new current trip identifier.
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
     * Removes the current trip identifier.
     */
    public void removeCurrentTripId() {
        this.currentTripId = null;
        SharedPreferences sharedPref = this.getApplicationContext().getSharedPreferences(this.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(this.getString(R.string.global_current_trip_id));
        editor.commit();
    }

    /**
     * @return Returns the current trip destination.
     */
    public String getCurrentTripDestination() {
        if(this.currentTripDestination==null) {
            SharedPreferences sharedPref = this.getApplicationContext().getSharedPreferences(this.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            this.currentTripDestination = sharedPref.getString(this.getString(R.string.global_current_trip_destination), "");
        }
        return this.currentTripDestination;
    }

    /**
     * Sets a new current trip destination.
     * @param currentTripDestination
     */
    public void setCurrentTripDestination(String currentTripDestination) {
        this.currentTripDestination = currentTripDestination;
        SharedPreferences sharedPref = this.getApplicationContext().getSharedPreferences(this.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(this.getString(R.string.global_current_trip_destination), currentTripDestination);
        editor.commit();
    }

    /**
     * Removes the current trip destination.
     */
    public void removeCurrentTripDestination() {
        this.currentTripDestination = null;
        SharedPreferences sharedPref = this.getApplicationContext().getSharedPreferences(this.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(this.getString(R.string.global_current_trip_destination));
        editor.commit();
    }
}

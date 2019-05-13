/**
 * Created by VIddA Software - DME Creaciones.
 */
package cat.dme.smart.marcopolo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import cat.dme.smart.marcopolo.BuildConfig;
import cat.dme.smart.marcopolo.R;

/**
 * Activity that shows credits.
 */
public class CreditsActivity extends BaseMenuActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        this.configureToolbar();

        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;

        TextView tvVersion = (TextView) findViewById(R.id.textVersion);
        tvVersion.setText(this.getString(R.string.app_name) + " " + versionName);
    }

    public static Intent runActivity(Context context) {
        return new Intent(context, CreditsActivity.class);
    }
}

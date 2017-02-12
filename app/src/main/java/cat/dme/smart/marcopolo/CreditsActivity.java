package cat.dme.smart.marcopolo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by str_dme on 25/07/14.
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
        tvVersion.setText(versionName);

    }
}

package cat.dme.smart.marcopolo.activities;

import android.os.Bundle;

import cat.dme.smart.marcopolo.R;

/**
 * Created by str_dme on 25/07/14.
 */
public class ExportActivity extends BaseMenuActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
        this.configureToolbar();
    }
}

package com.mat.hyb.school.kgk.sas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getFragmentManager().beginTransaction()
                .add(R.id.container, new StartFragment())
                .commit();
        PreferencesProvider preferencesProvider = new PreferencesProvider(getApplicationContext());
        if (!preferencesProvider.isFirstRun()) {
            new ClassDialogBuilder(this).build();
        }
        assert getActionBar() != null;
        getActionBar().setDisplayUseLogoEnabled(true);
        if(!isInternet()){
            new NetworkDialogBuilder(this).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                return true;
            case android.R.id.home:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new StartFragment()).commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!(getFragmentManager().findFragmentById(R.id.container) instanceof StartFragment)) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new StartFragment()).commit();
            } else {
                this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}

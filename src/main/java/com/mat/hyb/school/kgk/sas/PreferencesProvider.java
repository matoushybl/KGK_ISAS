package com.mat.hyb.school.kgk.sas;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by matous on 5.12.13.
 */
public class PreferencesProvider {
    private SharedPreferences sharedPreferences;
    private static final String CLASS = "class";
    private static final String FIRST_RUN = "first";

    public PreferencesProvider(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public int getDefaultClass() {
        return sharedPreferences.getInt(CLASS, 13);
    }

    public void setDefaultClass(int id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(CLASS, id);
        editor.commit();
    }

    public void setFirstRun() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FIRST_RUN, true);
        editor.commit();
    }

    public boolean isFirstRun() {
        return sharedPreferences.getBoolean(FIRST_RUN, false);
    }
}

package com.mat.hyb.school.kgk.sas;

import android.content.Context;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by matous on 5.12.13.
 */
public class UrlProvider {
    private static final String MOODLE_URL = "http://www.gymkyjov.cz/moodle";
    private static final String CANTEEN_URL = "http://www.gymkyjov.cz:8082";
    private static final String SUBSTITUTION_URL =
            "http://www.gymkyjov.cz/isas/suplovani.php?zobraz=tridy-1&suplovani=";
    private static final String DATE = "&rezim=den&datum=";
    private static final String MARKS = "http://www.gymkyjov.cz/isas/prubezna-klasifikace.php";
    private static final String TIMETABLE =
            "http://www.gymkyjov.cz/isas/rozvrh-hodin.php?zobraz=tridy-1&rozvrh=";
    public static final String WEBSITE = "http://www.gymkyjov.cz/";

    private PreferencesProvider preferencesProvider;

    public UrlProvider() {
    }

    public UrlProvider(Context context) {
        preferencesProvider = new PreferencesProvider(context);
    }

    public String getMoodleUrl() {
        return MOODLE_URL;
    }

    public String getCanteenUrl() {
        return CANTEEN_URL;
    }

    public String getSubstitutionTodayUrl() {
        return SUBSTITUTION_URL + String.valueOf(new ClassIdProvider().getClassId(preferencesProvider.getDefaultClass()));
    }

    public String getSubstitutionTomorrowUrl() {
        return SUBSTITUTION_URL + getSavedClass() + DATE + getTomorrowDate();
    }

    public String getMarksUrl() {
        return MARKS;
    }

    private String getTomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Log.d("month", String.valueOf(calendar.get(Calendar.MONTH)));
        return String.valueOf(calendar.get(Calendar.YEAR)) + "-"
                + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-"
                + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    private String getSavedClass() {
        return String.valueOf(new ClassIdProvider().getClassId(preferencesProvider.getDefaultClass()));
    }

    private int getSavedClassId() {
        return new ClassIdProvider().getClassId(preferencesProvider.getDefaultClass());
    }

    public String getTimetableUrl(int id) {
        return TIMETABLE + Integer.valueOf(id);
    }


    public String getOurTimetableUrl() {
        return getTimetableUrl(getSavedClassId());
    }

}

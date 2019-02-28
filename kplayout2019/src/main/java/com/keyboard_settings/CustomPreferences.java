package com.keyboard_settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

public class CustomPreferences  {

    private final Context mContext;
    private final SharedPreferences mSharedPreferences;
    private static CustomPreferences mInstance;

    private final String PREF_CURRENT_INDEX_THEME = "pref_current_index_theme";

    List<String> mKeys = new ArrayList<>();

    private int mCurrentThemeIndex = 0;




    private CustomPreferences(Context nContext) {
        mContext = nContext.getApplicationContext();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(nContext);

        mKeys.add(PREF_CURRENT_INDEX_THEME);

        loadPreferences();
    }

    public static void initialize(Context nContext) {
        if (mInstance != null) {
            throw new IllegalStateException();
        } mInstance = new CustomPreferences(nContext);
    }

    public static CustomPreferences getmInstance() {
        if (mInstance == null) {
            throw new IllegalStateException();
        }
        return mInstance;
    }

    private void loadPreferences() {
        for (String mKey : mKeys) {
            loadPreference(mKey);
        }
    }

    private void loadPreference(String mKey) {

        if (mKey==null) { return; }

        switch (mKey) {
            case PREF_CURRENT_INDEX_THEME : mCurrentThemeIndex = mSharedPreferences.getInt(mKey,0); break;
        }
    }

    public int getCurrentThemeIndex() {
        return mCurrentThemeIndex;
    }

    public void saveCurrentThemeIndex(int nIndex) {
        mCurrentThemeIndex = nIndex;
        mSharedPreferences.edit().putInt(PREF_CURRENT_INDEX_THEME,nIndex).apply();
    }
}

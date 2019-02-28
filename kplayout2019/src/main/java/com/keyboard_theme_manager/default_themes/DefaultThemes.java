package com.keyboard_theme_manager.default_themes;


import android.content.Context;
import android.util.Log;

import com.android.inputmethod.latin.settings.Settings;
import com.keyboard_settings.CustomPreferences;
import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource;
import com.keyboard_theme_manager.default_themes.model.DefatulThemesBase;
import com.keyboard_theme_manager.default_themes.model.ThemeSettings;

import java.util.ArrayList;
import java.util.List;

public class DefaultThemes extends DefatulThemesBase {

    private static DefaultThemes mInstance;

    private DefaultThemes(Context nContext) {
        this.mContext = nContext;
        CustomPreferences.initialize(nContext);
        mCustomPreferences = CustomPreferences.getmInstance();
        mCurrentIndexTheme = mCustomPreferences.getCurrentThemeIndex();
        reloadThemes();
    }

    public static DefaultThemes getmInstance() {
        Log.d("aefwafsd","0" );
        return mInstance;
    }

    public static void initialize(Context nContext) {
        mInstance = new DefaultThemes(nContext);
    }

    private void reloadThemes() {
        mAllThemes = new ArrayList<>();
        load_default_themes();
    }

    public ThemeResource getCurrentTheme() {
        if (mCurrentTheme == null) {
            Log.d("aefwafsd","1" );
            selectTheme(mCurrentIndexTheme);
            Log.d("aefwafsd","2" );
        }
        return mCurrentTheme;
    }

    public List<ThemeSettings> getAllThemes() {
        return mAllThemes;
    }

    public List<ThemeResource> getAllInstalledThemes() {
        int nrThemes = mAllThemes.size();
        List<ThemeResource> mReturn = new ArrayList<>();

        for (int i=0; i<nrThemes; i++) {
            mReturn.add(loadIntalledThemes(i));
        }

        return mReturn;
    }

    public void selectTheme(int nIndex) {
        Log.d("aefwafsd","3" );
        if (nIndex<0 || nIndex>=mAllThemes.size()) {
            Log.d("aefwafsd","4" );
            nIndex = 0;
        }
        Log.d("aefwafsd","5" );
        mCurrentIndexTheme = nIndex;
        Log.d("aefwafsd","6 " + mAllThemes.get(nIndex).theme_type);
        if (mAllThemes.get(nIndex).theme_type.equals("theme_default")) {
            Log.d("aefwafsd","7" );
            mCurrentTheme = loadDefaultTheme(nIndex);
        }
        Log.d("aefwafsd","8" );
        mCustomPreferences.saveCurrentThemeIndex(nIndex);
    }
}

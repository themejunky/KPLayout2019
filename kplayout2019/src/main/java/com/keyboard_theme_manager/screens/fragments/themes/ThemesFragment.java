package com.keyboard_theme_manager.screens.fragments.themes;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.inputmethod.latin.LatinIME;
import com.keyboard_settings.CustomPreferences;
import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource;
import com.keyboard_theme_manager.default_themes.DefaultThemes;
import com.keyboard_theme_manager.screens.activity.previewKeyboard.PreviewKeyboard;
import com.kplayout2019.R;

public class ThemesFragment extends ThemesBaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View rootView = inflater.inflate(R.layout.fragment_themes,container,false);
        mRootView = rootView;
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);
        fetchViewsAndInits();
    }

    public void onCustomResume() {
        onCustomResumeCode();
    }
    public void onCustomPause() {
        onCustomPauseCode();
    }

    @Override
    public void onThemeClicked(ThemeResource nTheme) {
        CustomPreferences.getmInstance().saveCurrentThemeIndex(nTheme.listing.themeNo);
        DefaultThemes.getmInstance().selectTheme(nTheme.listing.themeNo);
        LatinIME.mNewTheme = true;

        startActivity(new Intent(activity, PreviewKeyboard.class));
    }
}

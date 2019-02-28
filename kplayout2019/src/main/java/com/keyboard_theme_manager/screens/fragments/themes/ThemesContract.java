package com.keyboard_theme_manager.screens.fragments.themes;

import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource;
import com.keyboard_theme_manager.screens.fragments.utils.BasePresenter;
public interface ThemesContract {

    interface View {

    }

    interface Presenter extends BasePresenter {

    }

    interface Adapter {
        void onThemeClicked(ThemeResource nTheme);
    }

}

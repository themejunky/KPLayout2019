package com.keyboard_theme_manager.screens.fragments.home;

public class HomeContract  {

    public interface View {
        void noInternetConnection();
        void showAllPack();
        void onPackClicked();
    }

    interface Presenter {
    }
}

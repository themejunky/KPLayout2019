package com.keyboard_theme_manager.screens.fragments.home;



public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;

    HomePresenter(HomeContract.View view) {
        this.view = view;
    }
}

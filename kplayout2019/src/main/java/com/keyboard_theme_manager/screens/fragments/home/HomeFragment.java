package com.keyboard_theme_manager.screens.fragments.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kplayout2019.R;


public class HomeFragment extends HomeBaseFragment implements HomeContract.Presenter {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tj_fragment_home, container, false);
        fetchViewsAndInits(rootView);
        Log.d("incercare","fetchViewsAndInits");
        return rootView;
    }

    /*** @Custom : onResume & onPause ***/
    public void onCustomResume() {
        Log.d("incercare","onresume");
        onCustomResumeCode();
    }

    public void onCustomPause() {
        Log.d("incercare","onpause");
        onCustomPauseCode();
    }

    @Override
    public void noInternetConnection() {
        noInternetLogic(this);
    }
}
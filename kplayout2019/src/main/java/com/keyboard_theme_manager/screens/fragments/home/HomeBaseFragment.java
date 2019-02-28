package com.keyboard_theme_manager.screens.fragments.home;


import android.util.Log;
import android.view.View;

import com.keyboard_theme_manager.custom_views.YellowBadge;
import com.keyboard_theme_manager.screens.fragments.SlaveFragment;

public class HomeBaseFragment extends SlaveFragment {

    /* General */
    public HomePresenter presenter;


    protected void fetchViewsAndInits(View rootView) {
        Log.d("cevaada","onresume11111");

       // mYellowBadge = (YellowBadge) rootView.findViewById(R.id.mYellowBadge);

        preInitialization(this);
    }

    void onCustomResumeCode() {
        Log.d("cevaada","onresume2222");
        preInitialization(this);
    }

    void onCustomPauseCode() {
        isPreviewShowing = false;
    }

    public void initializations()
    {
        presenter = new HomePresenter(this);
        activity.onChangeFragment(this);

     //   mYellowBadge.setHomeFunctionality(activity,this);


    }
}

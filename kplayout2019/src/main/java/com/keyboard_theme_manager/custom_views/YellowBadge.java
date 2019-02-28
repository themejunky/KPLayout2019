package com.keyboard_theme_manager.custom_views;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.keyboard_onboarding.OnBoardingLogic;
import com.keyboard_theme_manager.MainActivity;
import com.keyboard_theme_manager.screens.fragments.SlaveFragment;

import com.kplayout2019.R;
import module.themejunky.com.tj_gae.utils.Stuff;


public class YellowBadge extends LinearLayout implements View.OnClickListener
{
    private int TYPE_HOME_TUTORIAL = 1;
    private Activity mActivity;
    private LinearLayout mYellowBadgeContainer;
    private int mYellowBadgeType;
    private SlaveFragment mSlaveFragment;

    public YellowBadge(Context context) {
        super(context);
    }

    public YellowBadge(Context nContext,AttributeSet nAttrs) {
        super(nContext, nAttrs);

        TypedArray mTypedarray = nContext.obtainStyledAttributes(nAttrs, R.styleable.YellowBadgeSettings);
        inflate(nContext, R.layout.tj_customview_yellowbadge, this);

        mYellowBadgeContainer = (LinearLayout) findViewById(R.id.mContainer);
        mYellowBadgeContainer.setOnClickListener(this);

        mTypedarray.recycle();
    }

    @Override
    public void onClick(View v) {
        if (mYellowBadgeType == TYPE_HOME_TUTORIAL) {
        new OnBoardingLogic(mYellowBadgeContainer,mActivity,mSlaveFragment); }
    }

    public void setFunctionality(Activity activity, SlaveFragment nSlaveFragment) {
        mActivity = activity;
        mSlaveFragment =  nSlaveFragment;
        mYellowBadgeType = TYPE_HOME_TUTORIAL;
        new OnBoardingLogic(mYellowBadgeContainer,mActivity,mSlaveFragment);
    }

}

package com.keyboard_onboarding;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.keyboard_theme_manager.screens.fragments.SlaveFragment;

import java.util.Timer;
import java.util.TimerTask;

import com.kplayout2019.R;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * This class contains and manage all the logic for the current status implementation of the keyboard;
 * Based on all 3 step this class trigger the corect step/pop-up implementation
 */

public class OnBoardingLogic implements View.OnClickListener {

    private LinearLayout mYellowBadgeContainer;
    private Activity mActivity;
    private SlaveFragment mSlaveFragment;

    private OnBoardingUtils mOnBoardingUtils;

    private Boolean mIsPopUpAbleToShow = true;

    private int TUTORIAL_SMALL_TITLE = 1;
    private int TUTORIAL_BIG_TITLE = 2;
    private int TUTORIAL_MESSAGE = 3;
    private int TUTORIAL_BTN = 4;

    private MaterialDialog mDialog;

    private Timer timer;

    public OnBoardingLogic(LinearLayout nYellowBadgeContainer, Activity nActivity, SlaveFragment nSlaveFragment) {
        startUp(nYellowBadgeContainer, nActivity, nSlaveFragment);
    }



    /**
     * Internal startUp method for the constructor and other places
     *
     * @param nYellowBadgeContainer - the root/container of the yellow badge
     * @param nActivity             - activity as context and for step 1 fallback on user interaction (cast!)
     * @param nSlaveFragment        - instance of home base fragment
     */
    private void startUp(LinearLayout nYellowBadgeContainer, Activity nActivity, SlaveFragment nSlaveFragment) {
        mActivity = nActivity;
        mYellowBadgeContainer = nYellowBadgeContainer;
        mSlaveFragment = nSlaveFragment;

        if (mOnBoardingUtils == null) mOnBoardingUtils = new OnBoardingUtils(mActivity);

        Resources mRes = mActivity.getResources();

        switch (mOnBoardingUtils.determineSetupStepNumber()) {
            case OnBoardingUtils.ONBOARDING_STEP_1:
                setFunctionality(String.format(mRes.getString(R.string.home_sub_tabs_tutorial_step_1),mRes.getString(R.string.app_name)));


                break;
            case OnBoardingUtils.ONBOARDING_STEP_2:
                setFunctionality(String.format(mRes.getString(R.string.home_sub_tabs_tutorial_step_2),mRes.getString(R.string.app_name)));
                break;
            case OnBoardingUtils.ONBOARDING_STEP_3:
                closeHomeFunctionality();
                break;
        }
    }

    /**
     * Set the current text for Yellow Badge and sets witch tutorial popUp showld be trigger
     *
     * @param nText - id of the text to show on yellow badge
     */
    private void setFunctionality(String nText) {
        ((TextView) mYellowBadgeContainer.findViewById(R.id.mText)).setText(nText);
        mYellowBadgeContainer.setVisibility(View.VISIBLE);

        if (mIsPopUpAbleToShow) {
            showPopUp();
            mIsPopUpAbleToShow = false;
        }
    }

    /**
     * Construct and show tutorial popup based on current status tutorial implementation
     * CustomView layout with dynamic texts
     */
    private void showPopUp() {
        mDialog = new MaterialDialog(mActivity);

        LayoutInflater factory = LayoutInflater.from(mActivity);
        View DialogInflateView = factory.inflate(R.layout.tj_onboarding_pop, null);
        LinearLayout mView = (LinearLayout) DialogInflateView.findViewById(R.id.mContainer);

        ((AnimationDrawable) (mView.findViewById(R.id.animation)).getBackground()).start();

        ((TextView) mView.findViewById(R.id.nSmallTitle)).setText(getTutorialText(TUTORIAL_SMALL_TITLE));
        ((TextView) mView.findViewById(R.id.nBigTitle)).setText(getTutorialText(TUTORIAL_BIG_TITLE));
        ((TextView) mView.findViewById(R.id.nMessage)).setText(getTutorialText(TUTORIAL_MESSAGE));
        ((TextView) mView.findViewById(R.id.nBtn)).setText(getTutorialText(TUTORIAL_BTN));

        mView.findViewById(R.id.nBtnClick).setOnClickListener(this);

        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setContentView(mView);
        mDialog.show();
    }

    /**
     * Get the neccesary string for the current location and bassed on current tutorial implementation steps
     *
     * @param type - the place where the string is needed in popup
     * @return Full string
     */
    private String getTutorialText(int type) {

        Resources mRes = mActivity.getResources();

        if (type == TUTORIAL_SMALL_TITLE) {
            switch (mOnBoardingUtils.determineSetupStepNumber()) {
                case OnBoardingUtils.ONBOARDING_STEP_1:
                    return getStringFromInt(R.string.tutorial_step_1_small);
                case OnBoardingUtils.ONBOARDING_STEP_2:
                    return getStringFromInt(R.string.tutorial_step_2_small);
            }
        } else if (type == TUTORIAL_BIG_TITLE) {
            switch (mOnBoardingUtils.determineSetupStepNumber()) {
                case OnBoardingUtils.ONBOARDING_STEP_1:
                    return String.format(mRes.getString(R.string.tutorial_step_1_big),mRes.getString(R.string.app_name));
                case OnBoardingUtils.ONBOARDING_STEP_2:
                    return String.format(mRes.getString(R.string.tutorial_step_2_big),mRes.getString(R.string.app_name));
            }
        } else if (type == TUTORIAL_MESSAGE) {
            switch (mOnBoardingUtils.determineSetupStepNumber()) {
                case OnBoardingUtils.ONBOARDING_STEP_1:
                    return String.format(mRes.getString(R.string.tutorial_step_1_message),mRes.getString(R.string.app_name));
                case OnBoardingUtils.ONBOARDING_STEP_2:
                    return String.format(mRes.getString(R.string.tutorial_step_2_message),mRes.getString(R.string.app_name));
            }
        } else if (type == TUTORIAL_BTN) {
            switch (mOnBoardingUtils.determineSetupStepNumber()) {
                case OnBoardingUtils.ONBOARDING_STEP_1:
                    return getStringFromInt(R.string.tutorial_step_1_btn);
                case OnBoardingUtils.ONBOARDING_STEP_2:
                    return getStringFromInt(R.string.tutorial_step_2_btn);
            }
        }
        return "";
    }

    /**
     * Get the coresponding string out of the interger param
     *
     * @param stringInt - desired string specified by id
     * @return coresponding string
     */
    private String getStringFromInt(int stringInt) {
        return mActivity.getResources().getString(stringInt);
    }

    /**
     * If the user succed on all tutorial steps => just hide the home yellow badge
     */
    private void closeHomeFunctionality() {
        mYellowBadgeContainer.setVisibility(View.GONE);
    }

    /**
     * Trigger the next step implementation for keyboard
     */
    private void performHomeFunctionality() {
        switch (mOnBoardingUtils.determineSetupStepNumber()) {
            case OnBoardingUtils.ONBOARDING_STEP_1:
                mOnBoardingUtils.invokeLanguageAndInputSettings();
                break;
            case OnBoardingUtils.ONBOARDING_STEP_2:
                mOnBoardingUtils.invokeInputMethodPicker();
                checkMyWindowHasFocus();
                break;
        }
    }

    /**
     * Timer repeated task to check on Home Tab focus;
     * If Home Tab has focus no popUp is showing;
     * if Home Tab does not have focus popUp for Step 2 is showing
     */
    private void checkMyWindowHasFocus() {
        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mSlaveFragment.mYellowBadge.hasWindowFocus()) {
                            mYellowBadgeContainer.setVisibility(View.GONE);
                            timer.cancel();
                            timer.purge();

                            startUp(mYellowBadgeContainer, mActivity, mSlaveFragment);
                        }
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timertask, 250, 250);
    }

    @Override
    public void onClick(View v) {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        performHomeFunctionality();
    }
}

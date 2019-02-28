package com.keyboard_settings.screens.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.inputmethod.latin.settings.Settings;
import com.android.inputmethod.latin.settings.SettingsValues;
import com.intro.utils.OnBoardingUtilsMain;
import com.keyboard_core.utils.CommonFunc;
import com.keyboard_onboarding.OnBoardingUtils;
import com.keyboard_settings.screens.activity.Sync.SyncGmail.GmailSyncActivity;
import com.keyboard_settings.screens.activity.Sync.SyncSMS.SmsSyncActivity;
import com.keyboard_theme_manager.MainBaseActivity;
import com.keyboard_theme_manager.screens.activity.SalveActivity;
import com.keyboard_theme_manager.screens.activity.aboutKeyboard.AboutKeyboard;
import com.keyboard_theme_manager.screens.activity.previewKeyboard.CustomEditText;
import com.keyboard_theme_manager.screens.activity.previewKeyboard.PreviewKeyboard;
import com.keyboard_theme_manager.screens.fragments.themes.ThemesFragment;
import com.kplayout2019.R;

public class Keyboard_Settings extends SalveActivity implements View.OnClickListener {

    private SettingsValues mSettingsValues;
    private Settings mSettings;
    private CheckBox mCheckSlave;
    private OnBoardingUtilsMain mOnBoardingUtils;
    private RelativeLayout nContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tj_activity_keyboard_settings);

        findViewById(R.id.sync_sms).setOnClickListener(this);
        findViewById(R.id.sync_gmail).setOnClickListener(this);
        findViewById(R.id.mAbout).setOnClickListener(this);
        findViewById(R.id.mMail).setOnClickListener(this);
        findViewById(R.id.mFAQ).setOnClickListener(this);
        //findViewById(R.id.mThemeManager).setOnClickListener(this);
        findViewById(R.id.mShare).setOnClickListener(this);
       nContainer =  findViewById(R.id.container);

        findViewById(R.id.mSpaceDotPeriod).setOnClickListener(this);

        findViewById(R.id.mSound).setOnClickListener(this);

        initViews();
        commonInit();

        mBarText.setText(getResources().getString(R.string.menu_nav_3));

        setUpFloatingButton();
        showSoftKeyboard(nContainer);
    }

    private void initViews() {

        mSettings = Settings.getInstance();
        mSettingsValues = mSettings.getCurrent();

        if (mSettingsValues != null) {

            ((CheckBox) findViewById(R.id.mSpaceDotPeriod).findViewById(R.id.mCheck)).setChecked(mSettingsValues.isAddSpaceAutomatically());
            ((CheckBox) findViewById(R.id.mSpaceDotPeriod).findViewById(R.id.mCheck)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mSettings.savePrefByKeyFlag(Settings.PREF_ADD_SPACE_AUTOMATICALLY, isChecked);
                    mSettings.loadTJSettings();
                }
            });

            ((CheckBox) findViewById(R.id.mAutomaticallyCapitalizeLetters).findViewById(R.id.mCheck)).setChecked(mSettingsValues.isAutomaticallyCapitalizeLetters());
            ((CheckBox) findViewById(R.id.mAutomaticallyCapitalizeLetters).findViewById(R.id.mCheck)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mSettings.savePrefByKeyFlag(Settings.PREF_AUTO_CAP, isChecked);
                    mSettings.loadTJSettings();
                }
            });

            ((CheckBox) findViewById(R.id.mShowKeyPopUp).findViewById(R.id.mCheck)).setChecked(mSettingsValues.isKeyPreviewPopupOn());
            ((CheckBox) findViewById(R.id.mShowKeyPopUp).findViewById(R.id.mCheck)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mSettings.savePrefByKeyFlag(Settings.PREF_POPUP_ON, isChecked);
                    mSettings.loadTJSettings();
                }
            });

            ((CheckBox) findViewById(R.id.mSound).findViewById(R.id.mCheck)).setChecked(mSettingsValues.isSoundOn());
            ((CheckBox) findViewById(R.id.mSound).findViewById(R.id.mCheck)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mSettings.savePrefByKeyFlag(Settings.PREF_SOUND_ON, isChecked);
                    mSettings.loadTJSettings();
                }
            });

            ((CheckBox) findViewById(R.id.mVibration).findViewById(R.id.mCheck)).setChecked(mSettingsValues.isVibrateOn());
            ((CheckBox) findViewById(R.id.mVibration).findViewById(R.id.mCheck)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mSettings.savePrefByKeyFlag(Settings.PREF_VIBRATE_ON, isChecked);
                    mSettings.loadTJSettings();
                }
            });


            ((CheckBox) findViewById(R.id.mSuggestion).findViewById(R.id.mCheck)).setChecked(mSettingsValues.isSuggestionsEnabledPerUserSettings());
            ((CheckBox) findViewById(R.id.mSuggestion).findViewById(R.id.mCheck)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mSettings.savePrefByKeyFlag(Settings.PREF_SHOW_SUGGESTIONS, isChecked);
                    if (!isChecked) {
                        ((CheckBox) findViewById(R.id.mPrediction).findViewById(R.id.mCheck)).setChecked(false);
                    }
                    mSettings.loadTJSettings();
                }
            });


            ((CheckBox) findViewById(R.id.mPrediction).findViewById(R.id.mCheck)).setChecked(mSettingsValues.isPredition());
            ((CheckBox) findViewById(R.id.mPrediction).findViewById(R.id.mCheck)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mSettings.savePrefByKeyFlag(Settings.PREF_ENABLE_PREDICTION, isChecked);
                    if (isChecked) {
                        ((CheckBox) findViewById(R.id.mSuggestion).findViewById(R.id.mCheck)).setChecked(true);
                    }
                    mSettings.loadTJSettings();
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
super.onClick(v);
        Intent mRedirect = null;

        int i = v.getId();
        if (i == R.id.sync_sms) {
            mRedirect = new Intent(this, SmsSyncActivity.class);
            Settings.getInstance().loadTJSettings();

        } else if (i == R.id.sync_gmail) {
            mRedirect = new Intent(this, GmailSyncActivity.class);

        } else if (i == R.id.mAbout) {
            mRedirect = new Intent(this, AboutKeyboard.class);

        } else if (i == R.id.mMail) {
            mRedirect = new CommonFunc().sendMail(this);

        } else if (i == R.id.mFAQ) {
            mRedirect = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.app_faq)));

           /* case R.id.mThemeManager:
                super.onBackPressed();
                break;*/
        } else if (i == R.id.mShare) {
            Intent mIntent = new Intent(Intent.ACTION_SEND);
            mIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.keyboard_share_text) + " https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
            mIntent.setType("text/plain");
            startActivity(mIntent);
        }
        if (mRedirect != null) {
            startActivity(mRedirect);
        }
    }

    private void mChecksAdmin(final int nCustomView, final String nPrefKey, Boolean nCheckValue) {

        ((CheckBox) findViewById(nCustomView).findViewById(R.id.mCheck)).setChecked(nCheckValue);

        (findViewById(nCustomView).findViewById(R.id.mContainer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox mCheckSlave = (findViewById(nCustomView).findViewById(R.id.mCheck));
                mCheckSlave.setChecked(!mCheckSlave.isChecked());
                mSettings.savePrefByKeyFlag(nPrefKey, !mCheckSlave.isChecked());
//                if (!mCheckSlave.isChecked()) {
//                    ((CheckBox) findViewById(nCustomView).findViewById(R.id.mCheck)).setChecked(false);
//                }
                mSettings.loadTJSettings();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mOnBoardingUtils == null) mOnBoardingUtils = new OnBoardingUtilsMain(this);
        if (mOnBoardingUtils.determineSetupStepNumber()== OnBoardingUtils.ONBOARDING_STEP_1 || mOnBoardingUtils.determineSetupStepNumber()==OnBoardingUtils.ONBOARDING_STEP_2) {
            mOnBoardingUtils.invokeSetupWizardOfThisIme();
        }
    }

    private void setUpFloatingButton() {

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Keyboard_Settings.this, PreviewKeyboard.class));
                //Snackbar.make(view, "Open Keyboard", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    public static void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }
}

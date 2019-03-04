package com.keyboard_settings.screens.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.SharedPreferencesCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import com.android.inputmethod.latin.settings.Settings;
import com.android.inputmethod.latin.settings.SettingsValues;
import com.intro.IntroTutorial;
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
import kotlin.random.Random;

public class Keyboard_Settings extends SalveActivity implements View.OnClickListener {

    private SettingsValues mSettingsValues;
    private Settings mSettings;
    private CheckBox mCheckSlave;
    private OnBoardingUtilsMain mOnBoardingUtils;
    private RelativeLayout nContainer, nBoost;
    protected final int CONTACTS_PERMISSION = 10;
    protected final int CONTACTS_PERMISSION2 = 11;
    public static final int GRANTED = 0;
    public static final int DENIED = 1;
    public static final int BLOCKED_OR_NEVER_ASKED = 2;
    private boolean permissionGarasnted;
    private View nSyncSms;


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
        nContainer = findViewById(R.id.container);
        nSyncSms = findViewById(R.id.sync_sms);

        findViewById(R.id.mSpaceDotPeriod).setOnClickListener(this);
        findViewById(R.id.boostId).setOnClickListener(this);

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
        nBoost = findViewById(R.id.boostId);
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

            if(Integer.valueOf(android.os.Build.VERSION.SDK)<23){
                nBoost.setVisibility(View.GONE);
            }
        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent mRedirect = null;

        int i = v.getId();
        if (i == R.id.sync_sms) {
            askPermissionSms(this);
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED){
                mRedirect = new Intent(this, SmsSyncActivity.class);
                Settings.getInstance().loadTJSettings();
            }


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
        } else if (i == R.id.boostId) {
            askPermission(this);
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
        if (mOnBoardingUtils.determineSetupStepNumber() == OnBoardingUtils.ONBOARDING_STEP_1 || mOnBoardingUtils.determineSetupStepNumber() == OnBoardingUtils.ONBOARDING_STEP_2) {
            mOnBoardingUtils.invokeSetupWizardOfThisIme();
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("adasdasd", String.valueOf(sharedPreferences.getBoolean("isDontAsk",false)));
        if(sharedPreferences.getBoolean("isDontAsk",false)){
            nBoost.setVisibility(View.GONE);
            nSyncSms.setVisibility(View.GONE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
            nBoost.setVisibility(View.GONE);
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


    public void askPermission(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_SMS,}, CONTACTS_PERMISSION);
        }

    }
    public void askPermissionSms(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_SMS}, CONTACTS_PERMISSION2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("Asfdasf", "onRequestPermissionsResult: " + requestCode);
        Log.d("dad3edfad", "result: " +   getPermissionStatus(this, Manifest.permission.READ_SMS));
        if (requestCode == CONTACTS_PERMISSION) {
            String permission = permissions[0];
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (!shouldShowRequestPermissionRationale(permission)) {
                    nBoost.setVisibility(View.GONE);
                    nSyncSms.setVisibility(View.GONE);
                    isDontAsk();
                }
            }else if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                nBoost.setVisibility(View.GONE);
                Log.d("Asfdasf","PERMISSION_GRANTED - badge");
            }
        }

        if (requestCode == CONTACTS_PERMISSION2) {
            String permission = permissions[0];
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                if (!shouldShowRequestPermissionRationale(permission)) {
                    nBoost.setVisibility(View.GONE);
                    nSyncSms.setVisibility(View.GONE);
                    isDontAsk();
                }
            }else if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                nBoost.setVisibility(View.GONE);
                Log.d("Asfdasf","PERMISSION_GRANTED - sms");
               Intent intent =  new Intent(this, SmsSyncActivity.class);
                Settings.getInstance().loadTJSettings();
                startActivity(intent);
            }
        }

    }

    public static int getPermissionStatus(Activity activity, String androidPermissionName) {
        if(ContextCompat.checkSelfPermission(activity, androidPermissionName) != PackageManager.PERMISSION_GRANTED) {
            if(!ActivityCompat.shouldShowRequestPermissionRationale(activity, androidPermissionName)){
                return BLOCKED_OR_NEVER_ASKED;
            }
            return DENIED;
        }
        return GRANTED;
    }

    private void isDontAsk(){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putBoolean("isDontAsk",true);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }



}

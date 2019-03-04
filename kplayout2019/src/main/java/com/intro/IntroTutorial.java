package com.intro;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.keyboard_onboarding.OnBoardingUtils;
import com.keyboard_settings.screens.activity.Keyboard_Settings;

import java.util.Timer;
import java.util.TimerTask;

import com.kplayout2019.MainApplication;
import com.kplayout2019.R;
import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents;

public class IntroTutorial extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "dasdasdfa";
    private OnBoardingUtils mOnBoardingUtils;
    private RelativeLayout activateButton,switchButton,boostButton;
    private TimerTask timertask;
    private Timer timer;
    protected final int CONTACTS_PERMISSION = 10;
    private boolean isFirstTime;
    private boolean isLittleVersion;
    private Module_GoogleAnalyticsEvents mGae;
    private boolean isFirstEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFirstEntry();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isFirstTime=  sharedPreferences.getBoolean("isFirstTime",false);
        mOnBoardingUtils = OnBoardingUtils.getInstance(this);


        if(isFirstTime&& mOnBoardingUtils.determineSetupStepNumber()==3){
            Intent intent  = (new Intent(IntroTutorial.this,Keyboard_Settings.class));
            if (getIntent().getStringExtra("theme_name") != null && getIntent().getStringExtra("theme_name").length() > 0) {
                intent.putExtra("theme_name",getIntent().getStringExtra("theme_name"));
                startActivity(intent);
                finish();
            }else {
                startActivity(intent);
                finish();
            }

        }
        setContentView(R.layout.activity_intro_tutorial);
        initView();
        if (android.os.Build.VERSION.SDK_INT < 24) {
            boostButton.setVisibility(View.GONE);
            isLittleVersion= true;
        }

        setView();

        if (mOnBoardingUtils.determineSetupStepNumber() == OnBoardingUtils.ONBOARDING_STEP_1 || mOnBoardingUtils.determineSetupStepNumber() == OnBoardingUtils.ONBOARDING_STEP_2) {
            mOnBoardingUtils.invokeSetupWizardOfThisIme();
        }
    }


    private void setView() {
        mOnBoardingUtils = OnBoardingUtils.getInstance(this);
        android.util.Log.d(TAG, "setView: "+mOnBoardingUtils.determineSetupStepNumber());
        if (mOnBoardingUtils != null) {
            switch (mOnBoardingUtils.determineSetupStepNumber()) {
                case 1:
                    activateButton.setClickable(true);
                    switchButton.setClickable(false);
                    boostButton.setClickable(false);
                    activateButton.setBackground(getResources().getDrawable(R.drawable.button_selected));
                    switchButton.setBackground(getResources().getDrawable(R.drawable.button_unselected));
                    break;
                case 2:
                    activateButton.setClickable(false);
                    switchButton.setClickable(true);
                    boostButton.setClickable(false);
                    switchButton.setBackground(getResources().getDrawable(R.drawable.button_selected));
                    activateButton.setBackground(getResources().getDrawable(R.drawable.button_unselected));
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
                    editor.putBoolean("isFirstTime",true);
                    SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);


                    break;
                case 3:
                    activateButton.setClickable(false);
                    switchButton.setClickable(false);
                    boostButton.setClickable(true);
                    activateButton.setBackground(getResources().getDrawable(R.drawable.button_unselected));
                    switchButton.setBackground(getResources().getDrawable(R.drawable.button_unselected));
                    boostButton.setBackground(getResources().getDrawable(R.drawable.button_selected));
                    break;
            }
        }
    }
    private void initView() {
        activateButton = (RelativeLayout) findViewById(R.id.btnActivate);
        switchButton = (RelativeLayout) findViewById(R.id.btnSwitch);
        boostButton = (RelativeLayout) findViewById(R.id.btnBoost);



        activateButton.setOnClickListener(this);
        switchButton.setOnClickListener(this);
        boostButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnActivate) {
           // ((ThemeJunkyApplication) getApplication()).setEvents(mGae, "FE-TutorialIntro", "Tutorial", "Click on Activate button");
            mOnBoardingUtils.invokeLanguageAndInputSettings();

        } else if (i == R.id.btnSwitch) {
           // ((ThemeJunkyApplication) getApplication()).setEvents(mGae, "FE-TutorialIntro", "Tutorial", "Click on Switch button");
            mOnBoardingUtils.invokeInputMethodPicker();
            checkMyWindowHasFocus();

        } else if (i == R.id.btnBoost) {
            //((ThemeJunkyApplication) getApplication()).setEvents(mGae, "FE-TutorialIntro", "Tutorial", "Click on Boost button");
            askPermission(this);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        setView();
    }

    public void checkMyWindowHasFocus() {
        timertask = new TimerTask() {
            @Override
            public void run() {
                if (IntroTutorial.this.hasWindowFocus()) {
                    android.util.Log.d(TAG, "run: 1 - " +mOnBoardingUtils.determineSetupStepNumber());
                    if (mOnBoardingUtils.determineSetupStepNumber() == OnBoardingUtils.ONBOARDING_STEP_3) {
                        android.util.Log.d(TAG, "run: 2");
                        IntroTutorial.this.runOnUiThread(new Runnable() {
                            public void run() {

                                if(isLittleVersion){
                                    startActivity(new Intent(IntroTutorial.this,Keyboard_Settings.class));
                                    finish();
                                }else  if(isFirstTime){
                                    startActivity(new Intent(IntroTutorial.this,Keyboard_Settings.class));
                                    finish();
                                }else {

                                    Intent intent = new Intent(IntroTutorial.this,Keyboard_Settings.class);
                                    startActivity(intent);
                                    finish();
                                  /*  activateButton.setClickable(false);
                                    switchButton.setClickable(false);
                                    boostButton.setClickable(true);
                                    activateButton.setBackground(getResources().getDrawable(R.drawable.button_unselected));
                                    switchButton.setBackground(getResources().getDrawable(R.drawable.button_unselected));
                                    boostButton.setBackground(getResources().getDrawable(R.drawable.button_selected));*/
                                }

                            }
                        });
                    }
                    android.util.Log.d(TAG, "run: 3");
                    timer.cancel();
                    timer.purge();
                }
            }


        };
        timer = new Timer();
        timer.schedule(timertask, 800, 1000);

    }

    public void askPermission(Activity activity){
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_EXTERNAL_STORAGE}, CONTACTS_PERMISSION);
        }
        else if(ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_EXTERNAL_STORAGE}, CONTACTS_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        android.util.Log.d(TAG, "onRequestPermissionsResult: "+requestCode);
        if (requestCode == CONTACTS_PERMISSION) {

            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Wait for Themes Manager", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(IntroTutorial.this,Keyboard_Settings.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, "Wait for Themes Manager", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(IntroTutorial.this,Keyboard_Settings.class);
                startActivity(intent);
                finish();

            }



        }
    }

    public void setFirstEntry(){
        mGae = ((MainApplication) getApplication()).MGAE;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isFirstEntry =sharedPreferences.getBoolean("firstEntry",false);
        if(!isFirstEntry){
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
            editor.putBoolean("firstEntry",true);
            SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
            mGae.getEvents("FirstEntryApp", "FirstEntry", "First Entry");
        }

    }

    public void getAndSendIntent(Class toClass){
        if (getIntent().getStringExtra("theme_name") != null && getIntent().getStringExtra("theme_name").length() > 0) {
            Log.d("aefawefas",getIntent().getStringExtra("theme_name"));
        }else {
            Log.d("aefawefas","este null");
        }
    }




}

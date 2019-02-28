package com.keyboard_theme_manager.screens.activity.aboutKeyboard;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.keyboard_theme_manager.screens.activity.SalveActivity;
import com.kplayout2019.R;

public class AboutKeyboard extends SalveActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            ((TextView) findViewById(R.id.mTextUp)).setText(getResources().getString(R.string.about_version)+" "+pInfo.versionName+" "+getResources().getString(R.string.about_version_code)+" "+pInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        commonInit();
        mBarText.setText(getResources().getString(R.string.menu_nav_4));
    }
}

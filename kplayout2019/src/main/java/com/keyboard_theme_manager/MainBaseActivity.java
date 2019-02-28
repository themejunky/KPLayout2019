package com.keyboard_theme_manager;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.intro.utils.OnBoardingUtilsMain;
import com.keyboard_core.utils.CommonFunc;
import com.keyboard_onboarding.OnBoardingUtils;
import com.keyboard_settings.screens.activity.Keyboard_Settings;
import com.keyboard_theme_manager.screens.activity.aboutKeyboard.AboutKeyboard;
import com.keyboard_theme_manager.screens.activity.previewKeyboard.PreviewKeyboard;
import com.keyboard_theme_manager.screens.fragments.home.HomeFragment;
import com.keyboard_theme_manager.screens.fragments.themes.ThemesFragment;
import com.kplayout2019.R;

public class MainBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    protected ViewPager viewPager;
    protected TabLayout tabLayout;
    protected ImageView mIconSettings;

    protected Fragment mFragmentInstanceThemes;
    protected Fragment mCurrentActiveFragment;
    protected OnBoardingUtilsMain mOnBoardingUtils;


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.menu_vip) {
//        }
//        else
            if (id == R.id.nav_languages) {
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, Keyboard_Settings.class));

        } else if (id == R.id.nav_about) {
                startActivity(new Intent(this, AboutKeyboard.class));

        } else if (id == R.id.nav_faq) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.app_faq))));
        } else if (id == R.id.nav_help) {
                startActivity(new CommonFunc().sendMail(this));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mCurrentActiveFragment != null) {
            if (mCurrentActiveFragment instanceof ThemesFragment) {
                ThemesFragment_onResumeOrOnPause(true); }
        }
       /* if (mOnBoardingUtils == null) mOnBoardingUtils = new OnBoardingUtilsMain(this);

        if (mOnBoardingUtils.determineSetupStepNumber()==OnBoardingUtils.ONBOARDING_STEP_1 || mOnBoardingUtils.determineSetupStepNumber()==OnBoardingUtils.ONBOARDING_STEP_2) {
            Snackbar.make(viewPager, "Please switch Light Keyboard first", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            mOnBoardingUtils.invokeSetupWizardOfThisIme();
        }*/
    }




    @Override
    public void onPause() {
        super.onPause();

        if (mCurrentActiveFragment != null) {
            if (mCurrentActiveFragment instanceof ThemesFragment) {
                ThemesFragment_onResumeOrOnPause(false);
            }
        }
    }

    public void ThemesFragment_onResumeOrOnPause(Boolean type) {
        if (mCurrentActiveFragment instanceof ThemesFragment && mFragmentInstanceThemes != null) {
           if (type) {
                ((ThemesFragment) mFragmentInstanceThemes).onCustomResume();
            } else {
                ((ThemesFragment) mFragmentInstanceThemes).onCustomPause();
            }
        }
    }

    public void onChangeFragment(Fragment newFrag) {
        if (mCurrentActiveFragment != null) {
            if (mCurrentActiveFragment instanceof HomeFragment) {
                ThemesFragment_onResumeOrOnPause(false);
            }
        } else
            mCurrentActiveFragment = newFrag;
    }


    protected void setUpViews() {
        setUpDrawer();
        initViews();
        setUpFloatingButton();
    }

    private void setUpDrawer() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected void initViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
       // noInternetLayout = (LinearLayout) findViewById(R.id.noInternetLayout);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);

        mIconSettings = (ImageView) findViewById(R.id.mIconSettings);
        mIconSettings.setOnClickListener(this);
    }

    private void setUpFloatingButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainBaseActivity.this, PreviewKeyboard.class));
                //Snackbar.make(view, "Open Keyboard", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer!=null) {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View nView) {
        if (nView == mIconSettings) {
            startActivity(new Intent(this, Keyboard_Settings.class));
        }
    }
}

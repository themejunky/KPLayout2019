package com.keyboard_theme_manager.screens.fragments.themes;


import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource;
import com.keyboard_theme_manager.custom_views.YellowBadge;
import com.keyboard_theme_manager.default_themes.DefaultThemes;
import com.keyboard_theme_manager.screens.fragments.BaseAdapter;
import com.keyboard_theme_manager.screens.fragments.SlaveFragment;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.interf.ViewType;
import com.kplayout2019.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public abstract class ThemesBaseFragment extends SlaveFragment implements ThemesContract.View, ThemesContract.Adapter {

    private List<ViewType> mAllThemes;
    private RecyclerView mList;
    private SwipeRefreshLayout mSwipe;
    private LinearLayoutManager mLinearLayoutManager;
    private BaseAdapter mAdapter;
    protected View mRootView;
//    private ModuleAdsManager adsManager;
    public void fetchViewsAndInits() {
      //  mYellowBadge = (YellowBadge) mRootView.findViewById(R.id.mYellowBadge);
        mList = (RecyclerView) mRootView.findViewById(R.id.mList);
        mSwipe = (SwipeRefreshLayout) mRootView.findViewById(R.id.mSwipe);
        preInitialization(this);
    }


    public void initializations()
    {
//        adsManager = ModuleAdsManager.getInstance(getActivity(),true);
//        adsManager.setListenerAds(this);
//        adsManager.initializeNativeAds(true,false);
//        adsManager.setLogs("cevaceva");
//
//
//        adsManager.initAdmobNativeAds(mRootView.findViewById(R.id.nAds1), "ca-app-pub-8562466601970101/5081303159");
//        adsManager.initAppnextNativeAds(mRootView.findViewById(R.id.nAds2), "a2c3e720-fc6f-4d61-8f50-a8c2436edaaf");



        if (mAdapter == null) {
            mAdapter = new BaseAdapter().setListenerThemes(this);
      //      mAdapter.setAdsManager(adsManager);
        }

        if (mLinearLayoutManager == null) {
            mLinearLayoutManager = setList_GridLayoutManager_Vertical(new WeakReference<Activity>(activity),mList,mAdapter,2,true);
        }

        mList.setAdapter(mAdapter);
        activity.onChangeFragment(this);

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
            }
        });

       // mYellowBadge.setFunctionality(activity,this);

        load();




    }

    public void load() {
        mAllThemes = new ArrayList<>();
        mAllThemes.addAll(DefaultThemes.getmInstance().getAllInstalledThemes());

        mAdapter.clearAndAddThemes(insertAdds(mAllThemes));

        mSwipe.setRefreshing(false);
    }

    protected void onCustomResumeCode() {
        preInitialization(this);
    }

    protected void onCustomPauseCode() {

    }











    @Override
    public void onThemeClicked(ThemeResource nTheme) {

    }

    /*@Override
    public void afterInterstitialIsClosed(String s) {

    }

    @Override
    public void loadedInterAds() {

    }

    @Override
    public void loadInterFailed() {

    }

    @Override
    public void loadNativeAds(String s) {

    }*/
}

package com.keyboard_theme_manager.screens.fragments;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource;
import com.keyboard_theme_manager.screens.fragments.themes.ThemesContract;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.holders.AdsHolder;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.interf.ViewType;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.interf.ViewTypeDelegateAdapter;



public class AdsAdapter implements ViewTypeDelegateAdapter {

    private ThemesContract.Adapter mListener = null;
   /* private ModuleAdsManager mAdsManager;

    public AdsAdapter( ModuleAdsManager nAdsAdapter) {
        this.mAdsManager = nAdsAdapter;
    }*/

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(View parent) {
        return new AdsHolder(parent);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, ViewType item) {
       // ((AdsHolder) holder).setAdsManager(mAdsManager);
        ((AdsHolder) holder).bind((ThemeResource) item);
    }
}
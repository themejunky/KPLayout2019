package com.keyboard_theme_manager.screens.fragments.themes;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.holders.ThemesHolderInstalled;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.interf.ViewType;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.interf.ViewTypeDelegateAdapter;

public class ThemesAdapter implements ViewTypeDelegateAdapter {

    private ThemesContract.Adapter mListener = null;

    public ThemesAdapter(ThemesContract.Adapter nListener) {
        this.mListener = nListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(View parent) {
        return new ThemesHolderInstalled(parent);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, ViewType item) {

        ((ThemesHolderInstalled) holder).setListener(mListener);
        ((ThemesHolderInstalled) holder).bind((ThemeResource) item);
    }
}
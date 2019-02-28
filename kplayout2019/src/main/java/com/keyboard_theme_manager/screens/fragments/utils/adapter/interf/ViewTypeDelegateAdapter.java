package com.keyboard_theme_manager.screens.fragments.utils.adapter.interf;


import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface ViewTypeDelegateAdapter {
    RecyclerView.ViewHolder onCreateViewHolder(View parent);
    void onBindViewHolder(RecyclerView.ViewHolder holder,  ViewType item);
}

package com.keyboard_theme_manager.screens.fragments.utils.adapter.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource;
import com.keyboard_theme_manager.screens.fragments.themes.ThemesContract;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.AdapterConstants;
import com.kplayout2019.R;


public class ThemesHolderInstalled extends RecyclerView.ViewHolder {
    private ThemesContract.Adapter mListener;
    private RelativeLayout mContainer;
    private ImageView mImage,mActiveTheme;

    public ThemesHolderInstalled(View viewGroup){

        super(viewGroup);
        mImage = (ImageView) viewGroup.findViewById(R.id.mThemePicture);
        mContainer = (RelativeLayout) viewGroup.findViewById(R.id.mContainer);
        mActiveTheme = (ImageView) viewGroup.findViewById(R.id.mActiveTheme);
    }

    public void bind(final ThemeResource item) {
        mImage.setBackground(item.theme_preview);

        if (item.listing.isActive) {
            mActiveTheme.setVisibility(View.VISIBLE);
        } else {
            mActiveTheme.setVisibility(View.GONE);
        }

        mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onThemeClicked(item);
            }
        });
    }

    public void setListener(ThemesContract.Adapter nListener) {
        mListener = nListener;
    }


}
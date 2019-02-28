package com.keyboard_theme_manager.screens.fragments.utils.adapter.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource;
import com.keyboard_theme_manager.screens.fragments.AdsAdapter;
import com.keyboard_theme_manager.screens.fragments.themes.ThemesContract;
import com.kplayout2019.R;

import java.util.ArrayList;



public class AdsHolder extends RecyclerView.ViewHolder {
    private RelativeLayout mContainer,mAds1,mAds2;
  //  private ModuleAdsManager mAdsAdapter;
    public AdsHolder(View viewGroup){
        super(viewGroup);
      //  mImage = (ImageView) viewGroup.findViewById(R.id.mThemePicture);
        mContainer = (RelativeLayout) viewGroup.findViewById(R.id.nContainer);
        mAds1 = (RelativeLayout) viewGroup.findViewById(R.id.nAds1);   mAds2 = (RelativeLayout) viewGroup.findViewById(R.id.nAds2);
      //  mActiveTheme = (ImageView) viewGroup.findViewById(R.id.mActiveTheme);
    }

    public void bind(final ThemeResource item) {

       // Log.d("adadadadas","3 : "+mAdsAdapter);
       // mAdsAdapter.setNativeFlowAndShowAds(new ArrayList<String>() {{add("admob"); add("appnext");}},null, mAds1,mAds2);


//        mContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListener.onThemeClicked(item);
//            }
//        });
    }

   /* public void setAdsManager(ModuleAdsManager nAdsManager) {
        this.mAdsAdapter = nAdsManager;
    }*/
}
package com.keyboard_theme_manager.screens.fragments;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keyboard_theme_manager.screens.fragments.themes.ThemesAdapter;
import com.keyboard_theme_manager.screens.fragments.themes.ThemesContract;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.AdapterConstants;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.interf.ViewType;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.interf.ViewTypeDelegateAdapter;
import com.kplayout2019.R;

import java.util.ArrayList;
import java.util.List;


public class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private ThemesContract.Adapter mListenerThemes = null;
    private SparseArrayCompat<ViewTypeDelegateAdapter> delegateAdapters = new SparseArrayCompat<>();
    private List<ViewType> items  = new ArrayList();
   // private ModuleAdsManager mAdsManager;
    public BaseAdapter setListenerThemes(ThemesContract.Adapter nListenerThemes) {
        this.mListenerThemes = nListenerThemes;
        setUp();
        return this;
    }

    /*public BaseAdapter setAdsManager(ModuleAdsManager nAdsManager) {
        this.mAdsManager = nAdsManager;
      //  setUp();

        return this;
    }*/

    public void clearAndAddThemes(List<ViewType> nThemes) {
        items.clear();
        items.addAll(nThemes);
        // notifyItemRangeRemoved(0, getLastPosition());
        notifyDataSetChanged();
    }

    private void setUp() {
        delegateAdapters = new SparseArrayCompat<>();
        delegateAdapters.put(AdapterConstants.Themes,new ThemesAdapter(mListenerThemes));
       // delegateAdapters.put(AdapterConstants.Adds,new AdsAdapter(mAdsManager));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == AdapterConstants.Themes) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_themes_adapter_installed, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ads, parent, false);
        }
        return delegateAdapters.get(viewType).onCreateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder.getItemViewType() == AdapterConstants.Themes) {
                delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder,items.get(position)); }
                else if (holder.getItemViewType() == AdapterConstants.Adds) {
            delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder,items.get(position)); }
    }


    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private int getLastPosition() {
        return items.size();
    }
}

package com.keyboard_theme_manager.screens.fragments;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource;
import com.keyboard_theme_manager.MainActivity;
import com.keyboard_theme_manager.custom_views.YellowBadge;
import com.keyboard_theme_manager.default_themes.DefaultThemes;
import com.keyboard_theme_manager.screens.fragments.home.HomeBaseFragment;
import com.keyboard_theme_manager.screens.fragments.home.HomeContract;
import com.keyboard_theme_manager.screens.fragments.themes.ThemesBaseFragment;
import com.keyboard_theme_manager.screens.fragments.themes.ThemesContract;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.AdapterConstants;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.interf.ViewType;

import java.lang.ref.WeakReference;
import java.util.List;

public class SlaveFragment extends Fragment implements HomeContract.View{

    /* General */
    protected MainActivity activity;
    protected Boolean isPreviewShowing = false;
    public YellowBadge mYellowBadge;

    /**
     * @Initializations -
     * Step 1 (preInitialization) :  Check if local variable activity is not not null before proceed
     * Step 2  Presenter, instance of activity, bottom menu
     */

    protected void preInitialization(final SlaveFragment instanceMainFragment) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (activity == null) {
                    activity = ((MainActivity) getActivity());
                    preInitialization(instanceMainFragment);
                } else {
                    isPreviewShowing = false;
                    if (instanceMainFragment instanceof ThemesBaseFragment) {
                        ((ThemesBaseFragment) instanceMainFragment).initializations();
                    }
//                    else if (instanceMainFragment instanceof ExclusiveBase) {
//                        ((ExclusiveBase) instanceMainFragment).initializations();
//                    } else if (instanceMainFragment instanceof FreeBase) {
//                        ((FreeBase) instanceMainFragment).initializations();
//                    } else if (instanceMainFragment instanceof InstalledBase) {
//                        ((InstalledBase) instanceMainFragment).initializations();
//                    }
                }
            }
        }, 5);
    }

    public void noInternetLogic(final SlaveFragment instanceMainFragment) {
      /*  activity.noInternetLayout.setVisibility(View.VISIBLE);
        activity.noInternetLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (instanceMainFragment instanceof AllPacks) {
                    ((AllPacks) instanceMainFragment).loadList();
                } else if (instanceMainFragment instanceof Exclusive) {
                    ((Exclusive) instanceMainFragment).loadList();
                } else if (instanceMainFragment instanceof Free) {
                    ((Free) instanceMainFragment).loadList();
                }
                swipe.setRefreshing(false);
                activity.noInternet = false;
                activity.noInternetLayout.setVisibility(View.GONE);
            }
        });
        activity.noInternet = true;*/
    }

    protected GridLayoutManager setList_GridLayoutManager_Vertical(WeakReference<Activity> activity, final RecyclerView list, final RecyclerView.Adapter adapter, final int cols, boolean showingAds) {
        GridLayoutManager GL = new GridLayoutManager(activity.get(), cols, LinearLayoutManager.VERTICAL, false);

        if (showingAds) {
            GL.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = 0;
                    if (adapter instanceof BaseAdapter) {
                        type = adapter.getItemViewType(position);
                    }

                    if (type==AdapterConstants.Adds) {
                        type = cols;
                    }
                    return type;
                }
            });
        }

        list.setLayoutManager(GL);
        list.setAdapter(adapter);
        return GL;
    }

    public List<ViewType> insertAdds(List<ViewType> nList) {

        int resultsSize = nList.size();
        int nrAds = resultsSize / 2;
        int currentIndex = 2;

        for (int i = 0; i < nrAds; i++) {
            ThemeResource adItem = new ThemeResource();
            adItem.setViewType(AdapterConstants.Adds);
            if (currentIndex < resultsSize) {
                nList.add(currentIndex, adItem);
                currentIndex += 2;
            }
        }
        return nList;
    }

    @Override
    public void noInternetConnection() {

    }

    @Override
    public void showAllPack() {

    }

    @Override
    public void onPackClicked() {

    }
}

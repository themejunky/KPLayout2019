package com.kplayout2019.screen.moreapps.utils

import android.app.Activity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

fun RecyclerView.setWaMoreAppsAdapter(mActivity: Activity){
    adapter = MoreAppsAdapter(mActivity)
    layoutManager = GridLayoutManager(context, 1)
}
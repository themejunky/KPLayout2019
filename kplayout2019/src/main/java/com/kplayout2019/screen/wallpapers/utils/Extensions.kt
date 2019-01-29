package com.kplayout2019.screen.wallpapers.utils

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.kplayout2019.screen.wallpapers.ListenerImage


fun RecyclerView.setWallpapersAdapter(listenerImage: ListenerImage){
    adapter = WallpapersAdapter(listenerImage)
    layoutManager = GridLayoutManager(context, 2)
}
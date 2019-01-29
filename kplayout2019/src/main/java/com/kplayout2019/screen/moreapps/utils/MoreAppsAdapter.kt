package com.kplayout2019.screen.moreapps.utils

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kplayout2019.BR
import com.kplayout2019.R
import com.kplayout2019.model.AdsItem

class MoreAppsAdapter(val mActivity: Activity): RecyclerView.Adapter<MoreAppsAdapter.WallpaperHolder>() {

    private var moreAppList: MutableList<AdsItem> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.row_moreapps, parent, false)
        return WallpaperHolder(binding,mActivity)
    }


    override fun onBindViewHolder(holder: WallpaperHolder, position: Int) {
        holder.bind(moreAppList[position])
        moreAppList[position].position = position
    }



    override fun getItemCount(): Int {
        return moreAppList.size
    }

    class WallpaperHolder(private val binding: ViewDataBinding, private val mActivity: Activity) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AdsItem) {
            binding.setVariable(BR.moreItem, data)
            binding.setVariable(BR.activity, mActivity)
            binding.executePendingBindings()
        }

    }


    fun setNewData(listWallpaper: MutableList<AdsItem>) {
        Log.d("aa32a", listWallpaper.size.toString())
        moreAppList = ArrayList()
        moreAppList.addAll(listWallpaper)
        notifyDataSetChanged()
    }
}
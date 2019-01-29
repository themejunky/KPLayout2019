package com.kplayout2019.screen.wallpapers.utils

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kplayout2019.BR
import com.kplayout2019.R
import com.kplayout2019.model.AdsItem
import com.kplayout2019.screen.wallpapers.ListenerImage

class WallpapersAdapter(private var listenerImage: ListenerImage) : RecyclerView.Adapter<WallpapersAdapter.WallpaperHolder>() {

    private var wallpaperList: MutableList<AdsItem> = ArrayList()
    private var mPrevCheck = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.row_wallpaper, parent, false)
        return WallpaperHolder(binding,this)
    }


    override fun onBindViewHolder(holder: WallpaperHolder, position: Int) {
        holder.bind(wallpaperList[position])
        wallpaperList[position].position = position


    }

    fun onTapRow(position: Int,urlString: String) {

        if (mPrevCheck != -1) {
            wallpaperList.get(mPrevCheck).isChecked = false
            notifyItemChanged(mPrevCheck)

            mPrevCheck = position
            wallpaperList.get(mPrevCheck).isChecked = true
            notifyItemChanged(mPrevCheck)
        } else {
            mPrevCheck = position
            wallpaperList.get(mPrevCheck).isChecked = true
            notifyItemChanged(mPrevCheck)
        }
        if(  wallpaperList.get(mPrevCheck).isChecked){
            listenerImage.whenClickOnImage(urlString,true,position)
        }



    }



    override fun getItemCount(): Int {
        return wallpaperList.size
    }

    class WallpaperHolder(private val binding: ViewDataBinding, private val mAdapter: WallpapersAdapter) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AdsItem) {
            binding.setVariable(BR.wallpapersItem, data)
            binding.setVariable(BR.adapter, mAdapter)
            binding.executePendingBindings()
        }





    }


    fun setNewData(listWallpaper: MutableList<AdsItem>) {
        Log.d("aa32a", listWallpaper.size.toString())
        wallpaperList = ArrayList()
        wallpaperList.addAll(listWallpaper)
        notifyDataSetChanged()
    }
}
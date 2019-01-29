package com.kplayout2019.screen.wallpapers

import android.app.WallpaperManager
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.kplayout2019.MainApplication
import com.kplayout2019.R
import com.kplayout2019.databinding.WallpaperActivityBinding
import com.kplayout2019.screen.wallpapers.utils.WallpapersAdapter
import com.kplayout2019.screen.wallpapers.utils.setWallpapersAdapter
import com.kplayout2019.utils.ViewModelFactory
import kotlinx.android.synthetic.main.no_internet.*
import kotlinx.android.synthetic.main.wallpaper_activity.*

class Wallpapers : AppCompatActivity(), ListenerImage {


    private lateinit var mViewModel: WallpaperViewModel
    private lateinit var mBinding: WallpaperActivityBinding
    private var mUrlString =""
    private var mIsClicked =false
    var urlString = ""
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.wallpaper_activity)
        mViewModel = ViewModelProviders.of(this, ViewModelFactory()).get(WallpaperViewModel::class.java)
        mBinding.viewModel = mViewModel
        mBinding.activity = this

        supportActionBar?.hide()

        mViewModel.getWallpapers()
        nWallpaperRW.setWallpapersAdapter(this)
        (nWallpaperRW.adapter as WallpapersAdapter).setNewData(mViewModel.wallpapersList)

        mViewModel.triggerWallapper.observe(this, Observer {
            if(it==true){
                (nWallpaperRW.adapter as WallpapersAdapter).setNewData(mViewModel.wallpapersList)
            }
        })
        nRloadInternet.setOnClickListener {
            mViewModel.getWallpapers() }

        mViewModel.isInternet.observe(this, Observer {
            if(it==false){
                noInternetWall.visibility = View.VISIBLE
                nButtonSelectWall.visibility = View.GONE
            }else{
                noInternetWall.visibility = View.GONE
                nButtonSelectWall.visibility = View.VISIBLE
            }
        })

    }


    fun setWallpaper(){
        if(mIsClicked){
            val myWallpaperManager = WallpaperManager.getInstance(this)
            Glide.with(this)
                .asBitmap()
                .load(mUrlString)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?) {
                        myWallpaperManager.setBitmap(resource)
                        Toast.makeText(this@Wallpapers,"Wallpaper was set!", Toast.LENGTH_LONG).show()
                        (application as MainApplication).MGAE.getEvents("asfdfasd","Wallpapers: Click on position: ${position} ${urlString}","Click on Button")
                    }

                })
        }

    }
    override fun whenClickOnImage(urlString: String,isClicked:Boolean,position:Int) {
        this.urlString = urlString
        this.position = position
        // (application as MainApplication).MGAE.getEvents(getString(R.string.layout_event),"MoreApps: Click on position: ${position} ${urlString}","Click on Button")

        nButtonSelectWall.background = ContextCompat.getDrawable(this, R.drawable.select_button)
        mUrlString = urlString
        mIsClicked = isClicked



    }



}
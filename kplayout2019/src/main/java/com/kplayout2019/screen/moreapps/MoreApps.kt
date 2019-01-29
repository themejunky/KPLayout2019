package com.kplayout2019.screen.moreapps

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.kplayout2019.MainApplication
import com.kplayout2019.R
import com.kplayout2019.screen.moreapps.utils.MoreAppsAdapter
import com.kplayout2019.screen.moreapps.utils.setWaMoreAppsAdapter
import com.kplayout2019.utils.ViewModelFactory
import kotlinx.android.synthetic.main.moreapps_activity.*
import kotlinx.android.synthetic.main.no_internet.*


class MoreApps  : AppCompatActivity() {
    private lateinit var mViewModel: MoreAppsViewModel
    private lateinit var mBinding: com.kplayout2019.databinding.MoreappsActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.moreapps_activity)
        mViewModel = ViewModelProviders.of(this, ViewModelFactory()).get(MoreAppsViewModel::class.java)
        mBinding.viewModel = mViewModel

        supportActionBar?.hide()

        mViewModel.getMoreApps()

        nMoreAppsRW.setWaMoreAppsAdapter(this)
        mViewModel.triggerMoreApps.observe(this, Observer {
            (nMoreAppsRW.adapter as MoreAppsAdapter).setNewData(mViewModel.moreAppsList)
        })

        nRloadInternet.setOnClickListener {
            mViewModel.getMoreApps() }

        mViewModel.isInternet.observe(this, Observer {
            if(it==false){
                noInternetMoreApp.visibility = View.VISIBLE
            }else{
                noInternetMoreApp.visibility = View.GONE
            }
        })


    }

    fun redirectToGP(urlString: String,position:Int){
        (application as MainApplication).MGAE.getEvents("taseeesa","MoreApps: Click on position: ${position} ${urlString}","Click on Button")
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlString)))}
}
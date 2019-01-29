package com.kplayout2019.screen.mainscreen

import android.arch.lifecycle.ViewModel
import com.kplayout2019.screen.mainscreen.repository.Repository

class MainScreenViewModel: ViewModel() {

    private val mRepository = Repository(this)
    var ourBestTheme=""
    var interstitialAdmob=""
    var interstitialAppnext=""

    fun getAdsInfo(){
        mRepository.getAds()
    }

    fun getThemeInfo(){
        mRepository.getTheme()
    }


}
package com.kplayout2019.screen.mainscreen.repository

import android.util.Log
import com.kplayout2019.model.AdsReceived
import com.kplayout2019.model.ThemeItem
import com.kplayout2019.screen.mainscreen.MainScreenViewModel
import io.reactivex.disposables.Disposable
import java.io.IOException

class Repository (val mViewModel: MainScreenViewModel) {
    private var mRepositoryWs = RepositoryWS()

    fun getAds(): Disposable{
        return mRepositoryWs.getAdsConfig().subscribe(::successGetAds, ::errorGetAds)
    }
    fun getTheme(): Disposable{
        return mRepositoryWs.getThemeConfig().subscribe(::successetTheme, ::erroretTheme)
    }

    private fun successGetAds(mResult: AdsReceived){
        with(mViewModel){
            interstitialAdmob = mResult.keys.admob_interstitial
            interstitialAppnext = mResult.keys.appnext
            android.util.Log.d("Afef","interstitialAdmob: ${interstitialAdmob} ---/--- interstitialAppnext ${interstitialAppnext}")
        }

    }
    private fun successetTheme(mResult: List<ThemeItem>){
        with (mViewModel){
            for (mData in mResult[0].account_internal_ads){
                if(mData.text.contains("type_best",true)){
                    android.util.Log.d("asdfewef34",mData.destination_url)
                    ourBestTheme = mData.destination_url
                    break

                }
            }
        }

    }
    private fun errorGetAds(mError: Throwable){
        Log.d("afweafs","errorGetAds: ${mError.message}")
        if (mError is IOException) {

        }

    }
    private fun erroretTheme(mError: Throwable){
        Log.d("afweafs","errorGetAds: ${mError.message}")
        if (mError is IOException) {

        }

    }
}
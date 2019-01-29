package com.kplayout2019.ads

import android.app.Activity
import android.util.Log
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd


class ManagerAdmob private constructor() {

    interface ManagerAdmobInterface {
        fun onAdmobLoaded(nAdmobInterstitial: InterstitialAd, nContext : Activity)
        fun onAdmobFailed(nContext: Activity)
        fun onAdmobClosed(nContext: Activity)
    }

    companion object {
        private val mInstance: ManagerAdmob = ManagerAdmob()
        @Synchronized
        fun getInstance(): ManagerAdmob {
            return mInstance
        }
    }

    fun init(nContext: Activity, nListener: ManagerAdmobInterface, idAdmob:String, nameLog:String) {
        /*"ca-app-pub-3940256099942544/10331737120"*/
        val mInterstitialAd = InterstitialAd(nContext)

        mInterstitialAd.adUnitId =idAdmob
        mInterstitialAd.loadAd(AdRequest.Builder().build())
        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Log.d(nameLog,"Admob - onAdLoaded")
                nListener.onAdmobLoaded(mInterstitialAd,nContext)
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                Log.d(nameLog,"Admob - onAdFailedToLoad $errorCode")
                nListener.onAdmobFailed(nContext)
            }

            override fun onAdOpened() {
                Log.d(nameLog,"Admob - onAdOpened")
            }

            override fun onAdLeftApplication() {
                Log.d(nameLog,"Admob - onAdLeftApplication")
            }

            override fun onAdClosed() {
                Log.d(nameLog,"Admob - onAdClosed")
                nListener.onAdmobClosed(nContext)
            }
        }
    }
}
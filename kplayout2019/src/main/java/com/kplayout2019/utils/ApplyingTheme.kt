package com.kplayout2019.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import com.appnext.ads.interstitial.Interstitial
import com.google.android.gms.ads.InterstitialAd
import com.intro.IntroTutorial
import com.kplayout2019.ManagerLayout
import com.kplayout2019.ads.Loading
import com.kplayout2019.ads.ManagerAdmob
import com.kplayout2019.ads.ManagerAppNext
import com.kplayout2019.dialogs.MotherIsNotActive
import com.kplayout2019.dialogs.PopupApply
import com.kplayout2019.isKplusOnStore
import com.theme.junky.pushnotificationlib.R
import java.io.IOException
import java.net.HttpURLConnection
import java.net.ProtocolException
import java.net.URL

open class ApplyingTheme : Loading.LoadingInterface, ManagerAdmob.ManagerAdmobInterface,  ManagerAppNext.ManagerAppNextInterface {
    lateinit var prefs: SharedPreferences
    private var myIntent: Intent? = null
    private var idAppnext = ""
    private var idAdmob = ""
    private var nameLog: String = ""
    open fun loadingFirstAdManager(nContext: Activity,mIdAdmob:String,mIdAppnext:String,mNameLog:String) {
        idAdmob = mIdAdmob
        idAppnext = mIdAppnext
        nameLog = mNameLog
        Loading.getInstance().init(nContext,this)
    }

    /***************FIRST MANAGER********************/

    override fun onLoadingShowing(nContext: Activity) {
        ManagerAdmob.getInstance().init(nContext,this,idAdmob,nameLog)
    }

    override fun onAdmobLoaded(nAdmobInterstitial: InterstitialAd,nContext : Activity) {
        if (nAdmobInterstitial.isLoaded) {
            Loading.getInstance().close()
            nAdmobInterstitial.show()
        } else {
            loadingSecondManager(nContext)
        }
    }

    override fun onAdmobFailed(nContext : Activity) {
        Log.d("afwef","admob - onAdmobFailed")
        loadingSecondManager(nContext)
    }

    override fun onAdmobClosed(nContext: Activity) {
        applyTheme(nContext)
    }

    /***************SECOND MANAGER********************/

    private fun loadingSecondManager(nContext : Activity) {
        Log.d("afwef","admob - loadingSecondManager")
        ManagerAppNext.getInstance().init(nContext,this,idAppnext,nameLog)
    }

    override fun onAppNextClosed(nContext: Activity) {
        applyTheme(nContext)
    }

    override fun onAppNextLoaded(nInterstitialAd: Interstitial, nContext: Activity) {
        if(nInterstitialAd.isAdLoaded) {
            Loading.getInstance().close()
            nInterstitialAd.showAd()
        } else {
            applyTheme(nContext)
        }
    }

    override fun onAppNextFailed(nContext: Activity) {
        Log.d("afwef","1 - onAppNextFailed")
        Loading.getInstance().close()
        Log.d("afwef","2 - onAppNextFailed")
        applyTheme(nContext)
        Log.d("afwef","3 - onAppNextFailed")
    }

    open fun applyTheme(nContext: Activity) {
        PopupApply.getInstance().init(nContext)

        /*if(isKplusOnStore){
            Log.d("isKplusOnStore","2 " + isKplusOnStore)
            Tools().directApply(nContext)
        }else{
            Log.d("isKplusOnStore","3 " + isKplusOnStore)
            Log.d("afwef","2 - applyTheme")
            nContext.startActivity(Intent(nContext,IntroTutorial::class.java))
            nContext.finish()
        }*/

    }

    @Throws(IOException::class)
    private fun availableOnGooglePlay(): Int {
        var resultConnection=0
        val url = URL("https://play.google.com/store/apps/details?id=com.themejunky.keyboardplus")
        val httpURLConnection = url.openConnection() as HttpURLConnection
        try {
            httpURLConnection.requestMethod = "GET"
        } catch (e: ProtocolException) {
            e.printStackTrace()
        }

        try {
            httpURLConnection.connect()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        resultConnection = httpURLConnection.responseCode
        Log.d("isKplusOnStore","onCreateApp " + resultConnection)
        return resultConnection
    }

}
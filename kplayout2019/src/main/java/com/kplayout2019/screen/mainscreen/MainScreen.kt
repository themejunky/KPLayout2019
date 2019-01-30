package com.kplayout2019.screen.mainscreen

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kplayout2019.MainApplication
import com.kplayout2019.R
import com.kplayout2019.databinding.MainScreenBinding
import com.kplayout2019.screen.moreapps.MoreApps
import com.kplayout2019.screen.wallpapers.Wallpapers
import com.kplayout2019.utils.ApplyingTheme
import com.kplayout2019.utils.Tools
import com.kplayout2019.utils.ViewModelFactory
import com.theme.junky.themeskotlin.ads.ManagerNativeAdmob
import effects.ripper.water.themejunky.com.rippereffects.ManagerWaterEffects

class MainScreen : AppCompatActivity() {
    private val mTools = Tools()
    private lateinit var mViewModel: MainScreenViewModel
    private lateinit var mBinding: MainScreenBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.main_screen)
        mViewModel = ViewModelProviders.of(this, ViewModelFactory()).get(MainScreenViewModel::class.java)
        mBinding.activity = this
        init()

        mViewModel.getAdsInfo()
        mViewModel.getThemeInfo()



    }

    private fun init() {
        supportActionBar?.hide()
        mTools.skakeImage(this,findViewById(R.id.nImageShake),2000,2000)
        ManagerNativeAdmob().init(this,getString(R.string.id_native_admob),findViewById(R.id.nativeAdsContainer))

    }

    fun applyTheme(){
        (application as MainApplication).MGAE.getEvents(getString(R.string.layout_event),"Click on Apply","Click on Button")
        ApplyingTheme().loadingFirstAdManager(this,mViewModel.interstitialAdmob,mViewModel.interstitialAppnext,"logInter")}

    fun redirectToWhatsAppGP(){
        (application as MainApplication).MGAE.getEvents(getString(R.string.layout_event),"Click on WhatsAppStickers Button","Click on Button")
        startActivity(Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.smsplus.sticker.pack.app")))}

    fun redirectToWallpapers(){
        (application as MainApplication).MGAE.getEvents(getString(R.string.layout_event),"Click on Wallpaper","Click on Button")
        startActivity(Intent(this, Wallpapers::class.java))}

    fun redirectToOurBestThemeGP(){
        (application as MainApplication).MGAE.getEvents(getString(R.string.layout_event),"Click on OurBest","Click on Button")
        if(mViewModel.ourBestTheme.isEmpty()){ startActivity(Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.themejunky.launchers")))}
        else{startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(mViewModel.ourBestTheme)))}}

    fun redirectToMoreApps(){
        (application as MainApplication).MGAE.getEvents(getString(R.string.layout_event),"Click on MoreApps","Click on Button")
        startActivity(Intent(this, MoreApps::class.java))}

    fun openPrivacyPolicy(){
        (application as MainApplication).MGAE.getEvents(getString(R.string.layout_event),"PrivacyPolicy","Click on Button")
        Tools().showPrivacyPolicy(this)}

    fun redirectToLiveWallpaper(){
        ManagerWaterEffects().setWaterEffects(this)}

    fun shareTheme(){
        (application as MainApplication).MGAE.getEvents(getString(R.string.layout_event),"Share","Click on Button")
        mTools.shareTheme(this)}

    override fun onBackPressed() {
        mTools.existApp(this,findViewById(R.id.scrollViewId))}


}

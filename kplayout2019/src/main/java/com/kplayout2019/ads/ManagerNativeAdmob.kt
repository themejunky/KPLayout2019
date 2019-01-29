package com.theme.junky.themeskotlin.ads

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.formats.MediaView
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import com.kplayout2019.R


class ManagerNativeAdmob {

    fun init(activity: Activity,admobAdUnit:String,containerAd:RelativeLayout) {
        // MobileAds.initialize(activity, admoAppId)
        val builder = AdLoader.Builder(activity, admobAdUnit)
        Log.d("nativeTest","init native")
        builder.forUnifiedNativeAd { unifiedNativeAd ->
            // OnUnifiedNativeAdLoadedListener implementation.
            val adView = activity.layoutInflater
                .inflate(R.layout.ad_unified, null) as UnifiedNativeAdView
            populateUnifiedNativeAdView(unifiedNativeAd, adView)
            containerAd.removeAllViews()
            containerAd.addView(adView)
        }

        val adLoader = builder.withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(errorCode: Int) {
                Log.d("nativeTest","Failed to load native ad: $errorCode")
            }
        }).build()

        adLoader.loadAd(AdRequest.Builder().build())


    }

    private fun populateUnifiedNativeAdView(nativeAd: UnifiedNativeAd, adView: UnifiedNativeAdView) {
        // Set the media view. Media content will be automatically populated in the media view once
        // adView.setNativeAd() is called.
        val mediaView = adView.findViewById<MediaView>(R.id.ad_media)
        adView.mediaView = mediaView

        // Set other ad assets.
        adView.headlineView = adView.findViewById(R.id.ad_headline)
        adView.bodyView = adView.findViewById(R.id.ad_body)
        adView.iconView = adView.findViewById(R.id.ad_app_icon)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)

        if (nativeAd.callToAction == null) {

            adView.callToActionView.visibility = View.INVISIBLE

        } else {

            adView.callToActionView.visibility = View.VISIBLE

            (adView.callToActionView as Button).text = nativeAd.callToAction

        }


        // The headline is guaranteed to be in every UnifiedNativeAd.
        (adView.headlineView as TextView).text = nativeAd.headline

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.body == null) {
            adView.bodyView.visibility = View.INVISIBLE
        } else {
            adView.bodyView.visibility = View.VISIBLE
            (adView.bodyView as TextView).text = nativeAd.body
        }



        if (nativeAd.icon == null) {
            adView.iconView.visibility = View.GONE
        } else {
            (adView.iconView as ImageView).setImageDrawable(
                nativeAd.icon.drawable
            )
            adView.iconView.visibility = View.VISIBLE
        }

        adView.setNativeAd(nativeAd)

    }
}
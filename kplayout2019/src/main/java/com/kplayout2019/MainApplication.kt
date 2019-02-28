package com.kplayout2019

import android.app.Application
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents

var packageNameApp=""
var redirectGP = ""

class MainApplication: Application() {
    open lateinit var mGoogleCredential: GoogleAccountCredential
    open lateinit var MGAE :Module_GoogleAnalyticsEvents
    override fun onCreate() {
        super.onCreate()
        packageNameApp= packageName
        MGAE= Module_GoogleAnalyticsEvents.getInstance(this,getString(R.string.id_google_analytics))
    }



}
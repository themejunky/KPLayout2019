package com.kplayout2019

import android.app.Application
import android.util.Log
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents
import java.io.IOException
import java.net.HttpURLConnection
import java.net.ProtocolException
import java.net.URL

var packageNameApp=""
var redirectGP = ""
var isKplusOnStore=0
class MainApplication: Application() {
    open lateinit var mGoogleCredential: GoogleAccountCredential
    open lateinit var MGAE :Module_GoogleAnalyticsEvents
    override fun onCreate() {
        super.onCreate()
        packageNameApp= packageName
        MGAE= Module_GoogleAnalyticsEvents.getInstance(this,getString(R.string.id_google_analytics))
        val thread = Thread(Runnable {
            try {
                try {
                    isKplusOnStore =  availableOnGooglePlay("com.themejunky.keyboardplus")
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

        thread.start()
    }

    @Throws(IOException::class)
    private fun availableOnGooglePlay(packageName: String): Int {
        var resultConnection=0
        val url = URL("https://play.google.com/store/apps/details?id=$packageName")
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
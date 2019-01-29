package com.kplayout2019

import android.app.Application
import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents

var packageNameApp=""

class MainApplication: Application() {

    lateinit var MGAE :Module_GoogleAnalyticsEvents
    var isFirstEntry:Boolean=false
    override fun onCreate() {
        super.onCreate()
        packageNameApp= packageName
        MGAE= Module_GoogleAnalyticsEvents.getInstance(this,"UA-58480755-1")
    }

    /*   fun setEvents(mGAEA: Module_GoogleAnalyticsEvents?, events: String) {
           val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
           isFirstEntry = sharedPreferences.getBoolean("first", false)
           if (isFirstEntry && mGAEA != null) {
               mGAEA.getEvents(R.string.layout_event, "Buttons", events)
           } else {
               Log.d("taferfare", isFirstEntry.toString() + " mgae este null")
           }
       }*/
}
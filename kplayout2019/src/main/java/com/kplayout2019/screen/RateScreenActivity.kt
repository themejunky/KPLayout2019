package com.kplayout2019.screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kplayout2019.MainApplication

class RateScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName"))
        )
        (application as MainApplication).MGAE.getEvents("asfdfasd","Click on rate","Click on Button")
        finish ()

    }
}
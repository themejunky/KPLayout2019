package com.themejunkylayout2019

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kplayout2019.screen.IntroPrivacyPolicy

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)

        startActivity(Intent(this,IntroPrivacyPolicy::class.java))
    }
}

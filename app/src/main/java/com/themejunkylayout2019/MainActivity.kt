package com.themejunkylayout2019

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.kplayout2019.ManagerLayout
import com.kplayout2019.screen.IntroPrivacyPolicy

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)
        ManagerLayout.getInstance().redirect(this,Main2Activity::class.java)

    }
}

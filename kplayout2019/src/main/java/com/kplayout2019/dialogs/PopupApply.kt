package com.kplayout2019.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.intro.IntroTutorial
import com.kplayout2019.MainApplication
import com.kplayout2019.R
import com.kplayout2019.isKplusOnStore
import com.kplayout2019.utils.Tools

class PopupApply {

    companion object {
        private val mInstance: PopupApply = PopupApply()
        @Synchronized
        fun getInstance(): PopupApply {
            return mInstance
        }
    }

    fun init(nActivity : Activity) : PopupApply {
        val dialogBuilder = AlertDialog.Builder(nActivity)
        val inflater = nActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.popup_apply, null)
        dialogBuilder.setView(dialogView)
        val mDialog = dialogBuilder.create()
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        val button11 = dialogView.findViewById(R.id.button1) as Button
        val button22 = dialogView.findViewById(R.id.button2) as Button
        val text = dialogView.findViewById(R.id.orId) as TextView


        if(isKplusOnStore==404){
            Log.d("Asdfasdf","if")
            button11.visibility = View.GONE
            text.visibility = View.GONE
        }else{
            Log.d("Asdfasdf","else")
        }
        val intent = nActivity.packageManager.getLaunchIntentForPackage("com.themejunky.keyboardplus")
        if (intent!=null) {
            if (Settings.Secure.getString(nActivity.contentResolver, "default_input_method").matches("com.themejunky.keyboardplus.*".toRegex())) {
                button11.text = nActivity.resources.getString(R.string.apply_apply_keyboard)
            } else {
                (nActivity .application as MainApplication).MGAE.getEvents(nActivity .resources.getString(R.string.layout_event),"Click on Activate Red Keyboard","Click on Button")
                button11.text = nActivity.resources.getString(R.string.apply_activate_keyboard)
            }
        } else {
            (nActivity .application as MainApplication).MGAE.getEvents(nActivity .resources.getString(R.string.layout_event),"Click on Download Red Keyboard","Click on Button")
            button11.text = nActivity.resources.getString(R.string.apply_download_keyboard)
        }

        button11.setOnClickListener {

            if (button11.text.equals(nActivity.resources.getString(R.string.apply_activate_keyboard))){
                (nActivity .application as MainApplication).MGAE.getEvents(nActivity .resources.getString(R.string.layout_event),"Click on Apply Basic Keyboard","Click on Button")
                Tools().directApply(nActivity)
            }else if(button11.text.equals(nActivity.resources.getString(R.string.apply_apply_keyboard))){
                Tools().directApply(nActivity)
            }else{
                val intent = Intent(Intent.ACTION_VIEW)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.data = Uri.parse(nActivity.getString(R.string.mother_url))
                nActivity.startActivity(intent)
            }

            mDialog.dismiss()  }
        button22.setOnClickListener {

            nActivity.startActivity(Intent(nActivity, IntroTutorial::class.java))
           // nActivity.finish()

            mDialog.dismiss()}
        mDialog.show()
        return this

    }
}
package com.kplayout2019.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import com.google.android.gms.ads.AdListener
import com.kplayout2019.R
import com.kplayout2019.dialogs.MotherIsNotActive
import com.kplayout2019.dialogs.MotherIsNotInstalled
import com.kplayout2019.screen.mainscreen.MainScreenLibrary
import com.theme.junky.pushnotificationlib.ManagerPush
import java.util.*

open class Tools : AdListener() {
    private var isFirstBack = 0
    private var firstBack = 0L
    private var secondBack = 0L

    open fun showPrivacyPolicy(nContext : Context)  {

        val mAlert = AlertDialog.Builder(nContext)
        val wv = WebView(nContext)

        wv.settings.defaultFontSize = 12
        wv.loadDataWithBaseURL("", readTxt(nContext), "text/html", "UTF-8", "")
        mAlert.setPositiveButton("Ok") { _, _ -> }
        mAlert.setView(wv)
        mAlert.show()
    }

    private fun readTxt(nContext : Context): String {
        val iStream = nContext.assets.open("privacy_policy.html")
        val size = iStream.available()

        val buffer = ByteArray(size)
        iStream.read(buffer)
        iStream.close()

        var str = String(buffer)
        str = str.replace("Best Keyboard Themes", "The developer ").replace("Keyboard Hell", nContext.getString(R.string.app_name))
        return str
    }

    open fun redirectLayout(nActivity : Activity) {
        val intent = Intent(nActivity, MainScreenLibrary::class.java)
        nActivity.startActivity(intent)
        nActivity.finish()
    }

    open fun applyTheme(nContext : Activity) {
        val intent = nContext.packageManager.getLaunchIntentForPackage("com.themejunky.keyboardplus")
        if (intent!=null) {
            if (Settings.Secure.getString(nContext.contentResolver, "default_input_method").matches("com.themejunky.keyboardplus.*".toRegex())) {
                directApply(nContext)
            } else {
                Log.d("afwef","0")
                MotherIsNotActive.getInstance().init(nContext)
            }
        } else {
            Log.d("afwef","0.0")
            MotherIsNotInstalled.getInstance().init(nContext)
        }
    }

    open fun directApply(activity: Activity) {

        val intent = activity.packageManager.getLaunchIntentForPackage("com.themejunky.keyboardplus")

        if (intent!=null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.putExtra("themeId", activity.resources.getString(R.string.theme_id))
            intent.putExtra("installed", true)
            intent.putExtra("theme_name", activity.resources.getString(R.string.keyboard_theme_name))
            activity.startActivity(intent)
           // ManagerPush().setPushNotification(true,activity.resources.getString(R.string.text_push_notification_title),activity.resources.getString(R.string.text_push_notification_subtitle),R.drawable.ic_launcher,1,3,2,"testPush")
            Log.d("testPush", activity.resources.getInteger(R.integer.time_rate_notification1).toString())
            ManagerPush().setPushNotification(true,activity.resources.getString(R.string.text_push_notification_title),activity.resources.getString(R.string.text_push_notification_subtitle),R.mipmap.ic_launcher,
               activity.resources.getInteger(R.integer.time_rate_notification1).toLong(), activity.resources.getInteger(R.integer.time_rate_notification2).toLong(),activity.resources.getInteger(R.integer.time_rate_notification3).toLong(),"testPush")

        }
    }


    open fun shareTheme(nContext : Context) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT,"Check out this awesome theme! https://play.google.com/store/apps/details?id=" + nContext.packageName)
        sendIntent.type = "text/plain"
        nContext.startActivity(Intent.createChooser(sendIntent, nContext.resources.getText(R.string.app_name)))
    }

    open fun skakeImage(nContext: Context,nImage: ImageView, nStartDelay: Long, nSartAfter: Long)
    {
        val timer = Timer()
        val timerTask = object : TimerTask() {
            var handler = Handler()
            override fun run() {
                handler.post {
                    val animShake = AnimationUtils.loadAnimation(nContext, R.anim.shake)
                    nImage.startAnimation(animShake)
                }
            }
        }
        timer.schedule(timerTask, nStartDelay, nSartAfter)
    }

    open fun existApp(activity:Activity,scrollView: ScrollView) {

        if (isFirstBack == 0) {
            isFirstBack++
            firstBack = getTimeBack()
            snackBar(activity,scrollView)
        } else if (isFirstBack == 1) {
            secondBack = getTimeBack()

            if (secondBack - firstBack < 3) {
                activity.finish()
            } else {
                isFirstBack = 0
                firstBack = 0L
                secondBack = 0L
                existApp(activity,scrollView)

            }
        }
    }

    private fun snackBar(activity:Activity,scrollView: ScrollView) {
        val snackbar = Snackbar
            .make(scrollView, "Message is deleted", Snackbar.LENGTH_SHORT)
            .setDuration(3000)
            .setAction(R.string.rate_me) { activity.finish() }
        val sbView = snackbar.view
        val params = sbView.layoutParams as FrameLayout.LayoutParams
        val textView = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        val textViewAction = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_action)

        params.gravity = Gravity.TOP

        sbView.layoutParams = params
        sbView.setBackgroundColor(Color.RED)

        textViewAction.visibility = View.GONE

        textView.setTextColor(Color.WHITE)
        textViewAction.setTextColor(Color.WHITE)
        textView.setText(R.string.rate_message)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        } else {
            textView.gravity = Gravity.CENTER_HORIZONTAL
        }
        snackbar.show()
    }

    private fun getTimeBack(): Long {  return System.currentTimeMillis() / 1000 }
}
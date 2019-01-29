package com.theme.junky.ohvaz.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kplayout2019.R
import com.kplayout2019.redirectGP
import com.theme.junky.themeskotlin.service.RedirectGP


class MyFirebaseMessagingService : FirebaseMessagingService() {

    private var TAG = "MyFirebaseMessagingService"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data != null && remoteMessage.data.isNotEmpty()) {
            showNotification(this,remoteMessage.data["title"],remoteMessage.data["message"],remoteMessage.data["icon_small"],remoteMessage.data["url"])

        }
        Log.d("asdfasdf",remoteMessage.data["url"])
    }

    private fun showNotification(context: Context, title: String?, message: String?, icon: String?,url: String?) {
        redirectGP = url.toString()
        val channelId = "channel-01"
        val channelName = "Channel Name"


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = channelName
            }
            // Register the channel with the system
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        val intent = Intent(this, RedirectGP::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)


        val mBuilder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(Glide.with(applicationContext).asBitmap().load(icon).into(100, 100).get())
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)

        with(NotificationManagerCompat.from(this)) {
            notify(123, mBuilder.build())
        }

        Log.d(TAG, "final")

    }
 }
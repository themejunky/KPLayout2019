package com.keyboard_core;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.keyboard_core.database.DataBase;
import com.keyboard_settings.screens.activity.Sync.SyncService.ReSyncServiceInBackground;
import com.keyboard_settings.screens.activity.Sync.SyncService.SyncServiceInBackground;

public class BootCompletedReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       if (DataBase.getInstance(context).getAll().getCount()>0) {
            // if there is any more mail indexes to parse trigger the allarm for the service
            ((AlarmManager) context.getSystemService(Context.ALARM_SERVICE)).set(0, System.currentTimeMillis() + 5000, PendingIntent.getService(context, 0, new Intent(context.getApplicationContext(), SyncServiceInBackground.class), 0));
        }

        //This was a service that tiggers on every phone restart to re-sync the keyboard dic
       /*  ((AlarmManager) context.getSystemService(Context.ALARM_SERVICE)).set(0, System.currentTimeMillis() + 5000, PendingIntent.getService(context, 0, new Intent(context.getApplicationContext(), ReSyncServiceInBackground.class), 0));*/
    }
}

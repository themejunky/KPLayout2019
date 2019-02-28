package com.keyboard_settings.screens.activity.Sync.SyncSMS;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.keyboard_settings.screens.activity.Sync.SyncService.SyncServiceInBackground;

public class SmsSyncService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("personal_sync", "Service Sms start");
        new SmsSyncAsyncTask(this).execute("");
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("personal_sync", "Service Sms Sync final");
        startService(new Intent(this, SyncServiceInBackground.class));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
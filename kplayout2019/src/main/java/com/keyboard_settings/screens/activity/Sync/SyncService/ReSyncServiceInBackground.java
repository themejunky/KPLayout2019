package com.keyboard_settings.screens.activity.Sync.SyncService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;


public class ReSyncServiceInBackground extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        new ReSyncServiceInBackgroundAsyncTask(this).execute();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

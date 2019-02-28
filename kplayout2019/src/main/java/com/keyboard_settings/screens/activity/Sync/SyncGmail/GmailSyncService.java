package com.keyboard_settings.screens.activity.Sync.SyncGmail;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.keyboard_settings.screens.activity.Sync.SyncService.SyncServiceInBackground;
import com.kplayout2019.MainApplication;


/**
 * This class is connecting to selected Gmail Account and gather all index of all mails from SENT label
 * After that ( triggered by onDrestroy() method ) parse all indexs and open every and each mail for words.
 * In the same time save to local DB the  base64 of the content mail for future re-parsing ( in case of restart of self-keyboard-fallback )
 */
public class GmailSyncService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("service_one","p1");
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        Gmail mGmailService = new Gmail.Builder(transport, jsonFactory, ((MainApplication) getApplication()).mGoogleCredential).setApplicationName("GmailSyncActivity API Android Quickstart2").build();
        Log.d("service_one","p2");
        new GmailSyncAsyncTask(this,mGmailService).execute("");
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        startService(new Intent(this, SyncServiceInBackground.class));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

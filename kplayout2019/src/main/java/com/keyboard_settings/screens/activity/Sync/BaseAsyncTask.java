package com.keyboard_settings.screens.activity.Sync;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.keyboard_core.database.DataBase;
import com.keyboard_settings.utils.SettingsConstants;
import com.keyboard_theme_manager.MainActivity;
import com.kplayout2019.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.Context.NOTIFICATION_SERVICE;


public class BaseAsyncTask extends AsyncTask<String, String, String> {

    protected SharedPreferences preferences;
    protected Context mContext;
    protected int idNotification;
    protected NotificationManager mNotifyManager;
    protected NotificationCompat.Builder mBuilder;
    protected String notificationTile;
    protected int notificationIcon;
    protected ContentValues mContentValues = new ContentValues();

    @Override
    protected void onPreExecute() {
        Toast.makeText(mContext, mContext.getResources().getString(R.string.keyboard_settings_progress),Toast.LENGTH_LONG).show();

        mNotifyManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(mContext);
        mBuilder.setContentTitle(notificationTile).setSmallIcon(notificationIcon);
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    protected void setCurrentSync(int type,String accountName) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String formattedDate = df.format(c.getTime());

        if (type== SettingsConstants.GMAIL) {
            DataBase.getInstance(mContext).setEmailSyncForAccount(accountName,formattedDate);
        } else  if (type== SettingsConstants.SMS) {
            DataBase.getInstance(mContext).setSMSSyncForAccount(accountName,String.valueOf(System.currentTimeMillis()));
        }
    }

    protected void publishProgress(int max, int current)
    {
        mBuilder.setProgress(max, current, false);
        mNotifyManager.notify(idNotification, mBuilder.build());
    }
}

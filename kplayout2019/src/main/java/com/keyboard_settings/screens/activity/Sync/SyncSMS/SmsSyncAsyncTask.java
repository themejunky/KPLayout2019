package com.keyboard_settings.screens.activity.Sync.SyncSMS;

import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.util.Log;

import com.keyboard_core.database.DataBase;
import com.keyboard_settings.screens.activity.Sync.BaseAsyncTask;
import com.keyboard_settings.utils.SettingsConstants;
import com.kplayout2019.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SmsSyncAsyncTask extends BaseAsyncTask {
    private SmsSyncService mService;
    private ArrayList<String> smsIds;
    private int maxSms=0, smsCount=0;
    SmsSyncAsyncTask(SmsSyncService service) {
        mContext = mService = service;
        idNotification = 1;

        notificationTile = mContext.getResources().getString(R.string.keyboard_settings_sync_sms);
        notificationIcon = R.drawable.tj_kbd_settings_0_1;

        Log.d("personal_sync","Start Task Sms Sync");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        smsIds = new ArrayList<>();
    }

    @Override
    protected String doInBackground(String... params) {

        Boolean returnFoundedNewSms = false;
        String mSMSLastSync = null;

        try {
            smsCount=0;
            mSMSLastSync = DataBase.getInstance(mContext).getSmsSyncDateForAccount();

            Uri allCalls = Uri.parse("content://sms");
            String[] mColls = new String[]{Telephony.Sms._ID, Telephony.Sms.TYPE};

            Cursor mC = null;
/*
last sync logic.... leave it disable until next update
            if (mSMSLastSync!=null) {
                String[] mWhere = new String[]{mSMSLastSync};
                mC = mContext.getContentResolver().query(allCalls, mColls, Telephony.Sms.DATE + ">?", mWhere, Telephony.Sms.DATE + " ASC");
            }
            else {*/
                mC = mContext.getContentResolver().query(allCalls, mColls, null, null, Telephony.Sms.DATE + " ASC");
          //  }


            if (mC!=null && mC.getCount()!=0) {

                returnFoundedNewSms = true;
                maxSms = mC.getCount();
                publishProgress(maxSms,smsCount);

                for (mC.moveToFirst(); !mC.isAfterLast(); mC.moveToNext()) {

                    smsCount++;
                    publishProgress(maxSms,smsCount);

                    smsIds.add(mC.getString(mC.getColumnIndex(Telephony.Sms._ID)));
                }
            } else {
                returnFoundedNewSms = false;

            }
            Log.d("personal_sync","Total New SMS Sync : "+smsIds.size()+" | Account : SMS");

            Log.d("sistem_parsare","TOTAL SMS : "+smsIds.size());
            DataBase.getInstance(mContext).save_sms_index(smsIds);
            //this was to save base64 of the mail for future re-sync if keyboard resets
            // DataBase.getInstance(mContext).save_base64(smsIds,"sms");
            setCurrentSync(SettingsConstants.SMS,"sms");

        } catch (Exception e) {
            Log.d("service_one", "NASOALE(1) : " + e.getMessage());
        }

        return returnFoundedNewSms+"/"+mSMSLastSync;
    }

    @Override
    protected void onPostExecute(String result) {

        String[] separated = result.split("/");

    /*    if (!Boolean.parseBoolean(separated[0])) {
        Toast.makeText(mContext,mContext.getResources().getString(R.string.no_new_sms)+""+getDate(Long.parseLong(separated[1]), "dd/MM/yyyy hh:mm:ss.SSS"),Toast.LENGTH_LONG).show(); }
*/
        Log.d("personal_sync", "Task Sync Sms final");
        mNotifyManager.cancel(idNotification);
        mService.stopSelf();
    }

    /**
     * Return date in specified format.
     * @param milliSeconds Date in milliseconds
     * @param dateFormat Date format
     * @return String representing date in specified format
     */
    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}

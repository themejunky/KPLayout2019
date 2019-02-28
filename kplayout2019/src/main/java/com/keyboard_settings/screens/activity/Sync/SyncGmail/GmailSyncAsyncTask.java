package com.keyboard_settings.screens.activity.Sync.SyncGmail;

import android.preference.PreferenceManager;
import android.util.Log;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.keyboard_core.database.DataBase;
import com.keyboard_settings.screens.activity.Sync.BaseAsyncTask;
import com.keyboard_settings.utils.SettingsConstants;
import com.kplayout2019.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class GmailSyncAsyncTask extends BaseAsyncTask {

    //instance of the service to be able to shutdown the service
    private GmailSyncService mService;
    //mail container : here i colect all the mail indexs to store in the DB
    private List<Message> mTotalListMails;
    // for gmail apoi
    private String mGmailAccountName;
    private long MaxMessages = 500;
    private String mGmailLabel = "SENT";
    private com.google.api.services.gmail.Gmail mGmailService = null;
    private List<String> mGmailLabel2 = Collections.singletonList(mGmailLabel);
    //counters for notification bar
    private int maxEmails = 0;
    private int currentEmail = 1;

    GmailSyncAsyncTask(GmailSyncService service, Gmail nGmailService) {
        Log.d("service_one","p3");
        mContext = mService = service;
        idNotification = 4;
        mGmailService = nGmailService;

        notificationTile = mContext.getResources().getString(R.string.keyboard_settings_sync_gmail);
        notificationIcon = R.drawable.tj_kbd_settings_0_2;

        preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mGmailAccountName = preferences.getString(SettingsConstants.GMAIL_ACCOUNT_NAME, null);
        Log.d("service_one","p4");
        mTotalListMails = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        Boolean returnFoundedNewMail = false;
        String mGmailLastSync = null;

        try {
            //get las sync date
            mGmailLastSync = DataBase.getInstance(mContext).getEmailSyncDateForAccount(mGmailAccountName);

            Log.d("service_one",""+mGmailAccountName+"/"+mGmailLabel);
            Log.d("service_one",""+mGmailService);
            Label infoLabel = mGmailService.users().labels().get(mGmailAccountName, mGmailLabel).execute();
            maxEmails = Integer.parseInt(infoLabel.get("messagesTotal").toString());
            publishProgress(maxEmails,currentEmail);
            Gmail.Users.Messages.List myQuery =  mGmailService.users().messages().list(mGmailAccountName).setLabelIds(mGmailLabel2).setMaxResults(MaxMessages);
            Log.d("service_one",""+maxEmails);
            // last sync logic.... leave it disable until next update
            // if (mGmailLastSync !=null) { myQuery.setQ("in:sent after:"+ mGmailLastSync); /*"in:sent after:2017/09/20" */ }

            //recursive method
            Log.d("service_one","parsare 1");
            parsingMails(myQuery.execute());

            returnFoundedNewMail = mTotalListMails.size() > 0;
            Log.d("sistem_parsare","TOTAL MAILS : "+mTotalListMails.size());

            //save in local DB all mail indexex
            DataBase.getInstance(mContext).save_mails_index(mTotalListMails,mGmailAccountName);
            //save in local DB current date of sync
            setCurrentSync(SettingsConstants.GMAIL,mGmailAccountName);
        } catch (Exception e) {
            Log.d("service_one", "NASOALE(11) : " + e.getMessage());
            Log.d("service_one", "NASOALE(11) : " + e.getCause());
        }

        return returnFoundedNewMail+"//"+mGmailLastSync;
    }

    @Override
    protected void onPostExecute(String result) {

        String[] separated = result.split("//");

      /*  if (!Boolean.parseBoolean(separated[0])) {
            Toast.makeText(mContext,mContext.getResources().getString(R.string.no_new_gmail)+""+separated[1],Toast.LENGTH_LONG).show(); }
*/
        mNotifyManager.cancel(idNotification);

        //close the service that hold current AsyncTask and trigger SyncServiceInBackground.class service
        mService.stopSelf();
    }

    /**
     * This is a recursive method that request for mails indexex in the selected label
     * @param listMailsId : information about mail from where we get mail indexes
     */
    private void parsingMails(ListMessagesResponse listMailsId) {
        try {
            Log.d("service_one",""+listMailsId.getMessages().size());
            if (listMailsId.getMessages() != null) {
                mTotalListMails.addAll(listMailsId.getMessages());
                for (Message ignored : listMailsId.getMessages()) {
                    Log.d("service_one",""+maxEmails+"/"+currentEmail++);
                    publishProgress(maxEmails,currentEmail++);
                }
            }

            //if the current request/query has nextToken we request for
            if (listMailsId.getNextPageToken() != null) {
                String pageToken = listMailsId.getNextPageToken();
                parsingMails(mGmailService.users().messages().list(mGmailAccountName).setLabelIds(mGmailLabel2).setPageToken(pageToken).setMaxResults(MaxMessages).execute());
            }
        } catch (Exception e) {
            Log.d("service_one", "NASOALE(2) : " + e.getMessage());
        }
    }
}
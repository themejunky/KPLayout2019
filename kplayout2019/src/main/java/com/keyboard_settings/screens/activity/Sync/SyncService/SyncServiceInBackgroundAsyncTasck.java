package com.keyboard_settings.screens.activity.Sync.SyncService;


import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Telephony;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.android.inputmethod.latin.NgramContext;
import com.android.inputmethod.latin.personalization.UserHistoryDictionary;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import com.keyboard_core.database.DataBase;
import com.kplayout2019.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.android.inputmethod.latin.LatinIME.mDictionaryFacilitator;

class SyncServiceInBackgroundAsyncTasck extends AsyncTask<String, String, String> {

    private Context mContext;
    private SyncServiceInBackground mService;
    private String rand_id,rand_type,rand_index,rand_email;
    private NgramContext.WordInfo primul, doilea, treilea;
    private NgramContext ngramContext_Single;
    private NgramContext.WordInfo[] grupare;
    private NgramContext container;
    private Cursor mSmsCursor;
    private String[] mWords;
    private int countWords;
    private int simpleCounter, maxAll,idNotification=3,loopCount=0;
    private String mWordLoop;
    protected NotificationManager mNotifyManager;
    protected NotificationCompat.Builder mBuilder;
    private static final String[] SCOPES = {GmailScopes.GMAIL_LABELS, GmailScopes.GMAIL_READONLY};
    private GoogleAccountCredential mCredential;
    HttpTransport transport;
    JsonFactory jsonFactory;

    private List<String> base64;
private  UserHistoryDictionary ceva;
    SyncServiceInBackgroundAsyncTasck(Context nContext,SyncServiceInBackground service) {
        mContext = nContext;
        mService = service;
        setUp();

        ceva = new UserHistoryDictionary(nContext, Locale.getDefault(),null);
      //  ceva.clear();
    }

    @Override
    protected String doInBackground(String... params) {

        Cursor rand = DataBase.getInstance(mContext).getAll();
        maxAll = rand.getCount();
        if (rand!=null && rand.getCount()!=0) {
            if (mDictionaryFacilitator.isActive()) {

                mBuilder.setProgress(maxAll, 0, false);
                mNotifyManager.notify(idNotification, mBuilder.build());

                loop(rand);

            } else {
                ((AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE)).set(0, System.currentTimeMillis() + 30000, PendingIntent.getService(mContext, 0, new Intent(mContext.getApplicationContext(), SyncServiceInBackground.class), 0));
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        DataBase.getInstance(mContext).save_base64(base64,"mail");
        ceva.asyncFlushBinaryDictionary();
        mService.stopSelf();
        mNotifyManager.cancel(idNotification);
    }

    private void loop(Cursor rand) {
        for (rand.moveToFirst(); !rand.isAfterLast(); rand.moveToNext()) {
            loopCount++;
            mBuilder.setContentTitle(mContext.getResources().getString(R.string.please_wait)+" "+(int)((100*loopCount)/maxAll)+"%");
            mBuilder.setProgress(maxAll, loopCount, false);
            mNotifyManager.notify(idNotification, mBuilder.build());

             rand_id = rand.getString(0);
             rand_type = rand.getString(1);
             rand_index = rand.getString(2);
             rand_email = rand.getString(3);
             switch (rand_type) {
                 case "sms" : parse_sms(); break;
                 case "mail" : parse_mail(); break;
             }
        }
    }
    private void parse_sms() {
        Uri allCalls = Uri.parse("content://sms");
        String[] mColls = new String[]{Telephony.Sms._ID, Telephony.Sms.TYPE, Telephony.Sms.BODY};
        mSmsCursor = mContext.getContentResolver().query(allCalls, mColls, Telephony.Sms._ID + "=?", new String[]{rand_index}, null);

        if (mSmsCursor!=null && mSmsCursor.getCount()!=0)  {
            mSmsCursor.moveToFirst();
            parse_fraze(mSmsCursor.getString(2));
        }
    }

    private void parse_mail() {

        try {

            mCredential.setSelectedAccountName(rand_email);
            Gmail mGmailService = new Gmail.Builder(transport, jsonFactory, mCredential).setApplicationName("GmailSyncActivity API Android Quickstart2").build();
            Message mail =  mGmailService.users().messages().get(rand_email,rand_index).execute();


            if (mail.getPayload().getParts() != null && mail.getPayload().getParts().size()>0 && mail.getPayload().getParts().get(0).getBody().getData()!=null) {

                base64.add(mail.getPayload().getParts().get(0).getBody().getData().trim());
                byte[] bodyBytes = Base64.decodeBase64(mail.getPayload().getParts().get(0).getBody().getData().trim());
                String body = new String(bodyBytes, "UTF-8");
                parse_fraze(body);
             } else {
                DataBase.getInstance(mContext).deleteFrom(rand_id);
            }

        } catch (Exception e) {
            Log.d("personal_sync","nasoale : "+e.getMessage());
        }

    }

    private void parse_fraze(String fraze) {

        fraze = fraze.replaceAll("[0-9]", " ");
        fraze = fraze.replaceAll("[-\\\\[\\\\]^/,'*:.!><~@#$%+=?|\\\"\\\\\\\\()]+", " ");
        fraze = fraze.replaceAll("\\s+", " ");

        mWords = fraze.split(" ");
        countWords = mWords.length;

        for (int i=0;i<countWords;i++)
        {
           mWordLoop = mWords[i].toLowerCase().trim();

            if (mWordLoop.length() > 2) {


                Log.d("single_word",""+mWordLoop);


//            ceva.reloadDictionaryIfRequired

                mDictionaryFacilitator.addToUserHistory(mWordLoop,false, ngramContext_Single, 1507623211 , true);

               // ceva.dumpAllWordsForDebug();
                ceva.addUnigramEntry(mWordLoop,-1,false,false,1507623211);
                delayInsert();
                if (i + 1 < countWords) {
                    addComposeWords(mWordLoop, mWords[i + 1].toLowerCase().trim());

                }

            }
        }
        DataBase.getInstance(mContext).deleteFrom(rand_id);
    }

    private void addComposeWords(String firstWord,String secondWord) {

        for (int i=0;i<2;i++) {

            primul = new NgramContext.WordInfo("Ss", false);
            doilea = new NgramContext.WordInfo("", true);
            treilea = new NgramContext.WordInfo(null);

            grupare = new NgramContext.WordInfo[]{primul, doilea, treilea};
            container = new NgramContext(3, grupare);

            mDictionaryFacilitator.addToUserHistory(firstWord, false, container,1507623211, true);

            ceva.addNgramEntry(container,firstWord,1,1507623211);


            primul = new NgramContext.WordInfo(firstWord, false);
            doilea = new NgramContext.WordInfo("Ss", false);
            treilea =new NgramContext.WordInfo("", true);

            grupare = new NgramContext.WordInfo[]{primul, doilea, treilea};
            container = new NgramContext(3, grupare);

            Log.d("double_word",""+firstWord+"/"+secondWord);

            mDictionaryFacilitator.addToUserHistory(secondWord, false, container, System.currentTimeMillis()/1000, true);

            ceva.addNgramEntry(container,secondWord,1,1507623211);

            delayInsert();
        }
    }

    private void setUp() {

        base64 = new ArrayList<>();

        simpleCounter = 0;

        mCredential = GoogleAccountCredential.usingOAuth2(mContext.getApplicationContext(), Arrays.asList(SCOPES)).setBackOff(new ExponentialBackOff());
        transport = AndroidHttp.newCompatibleTransport();
        jsonFactory = JacksonFactory.getDefaultInstance();

        mNotifyManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(mContext);
        mBuilder.setContentTitle(mContext.getResources().getString(R.string.please_wait)).setSmallIcon(R.drawable.tj_kbd_settings_0_3);

        NgramContext.WordInfo singleWord = new NgramContext.WordInfo("",true);
        NgramContext.WordInfo[] mP = { singleWord, new NgramContext.WordInfo(null),new NgramContext.WordInfo(null)};
        ngramContext_Single = new NgramContext(3,mP);
    }

    private void delayInsert() {
        try {
            simpleCounter++;

            if (simpleCounter>10000) {
                ceva.asyncFlushBinaryDictionary();
                Thread.sleep(1000);
                simpleCounter=0;
            }
        } catch (Exception e) {

        }
    }
}

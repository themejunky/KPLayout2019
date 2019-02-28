package com.keyboard_settings.screens.activity.Sync.SyncService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Telephony;
import android.util.Log;

import com.android.inputmethod.latin.NgramContext;
import com.google.api.client.util.Base64;
import com.keyboard_core.database.DataBase;

import static com.android.inputmethod.latin.LatinIME.mDictionaryFacilitator;


public class ReSyncServiceInBackgroundAsyncTask extends AsyncTask<String, String, String> {
    private NgramContext.WordInfo primul, doilea, treilea;
    private ReSyncServiceInBackground mService;
    private Context mContext;
    private String rand_id,rand_type,rand_index;
    private NgramContext ngramContext_Single;
    private NgramContext.WordInfo[] grupare;
    private NgramContext container;
    private int simpleCounter;

    ReSyncServiceInBackgroundAsyncTask(ReSyncServiceInBackground service) {
        this.mService = service;
        this.mContext = service;

        setUp();
    }

    @Override
    protected String doInBackground(String... params) {

        Cursor rand = DataBase.getInstance(mContext).getAll2();

        Log.d("trezire_repopulate","trezit : gasit "+rand.getCount()+"/ mDictionaryFacilitator :"+mDictionaryFacilitator.isActive());

        if (rand!=null && rand.getCount()!=0) {
            if (mDictionaryFacilitator.isActive()) {
                mDictionaryFacilitator.clearUserHistoryDictionary(mContext);
                Log.d("trezire_repopulate","loooop");
                loop(rand);

            } else {
                Log.d("trezire_repopulate","culcarea");

                ((AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE)).set(0, System.currentTimeMillis() + 10000, PendingIntent.getService(mContext, 0, new Intent(mContext.getApplicationContext(), ReSyncServiceInBackground.class), 0));
                mService.stopSelf();
            }
        }
        return null;
    }

    private void loop(Cursor rand) {
        for (rand.moveToFirst(); !rand.isAfterLast(); rand.moveToNext()) {

            rand_id = rand.getString(0);
            rand_index = rand.getString(1);
            rand_type = rand.getString(2);

            switch (rand_type) {
                case "sms" : parse_sms(); break;
                case "mail" : parse_mail(); break;
            }
        }
    }

    private void parse_mail() {

        try {

                if (rand_index!=null && rand_index.length()>0) {
                    byte[] bodyBytes = Base64.decodeBase64(rand_index.trim());
                    String body = new String(bodyBytes, "UTF-8");
                    Log.d("trezire_repopulate","mail : "+body);

                    parse_fraze(body);
                }
        } catch (Exception e) {
            Log.d("la_rand_parsare","nasoale : "+e.getMessage());
        }

    }

    private void parse_sms() {
        Uri allCalls = Uri.parse("content://sms");
        String[] mColls = new String[]{Telephony.Sms._ID, Telephony.Sms.TYPE, Telephony.Sms.BODY};
        Cursor mSmsCursor = mContext.getContentResolver().query(allCalls, mColls, Telephony.Sms._ID + "=?", new String[]{rand_index}, null);

        if (mSmsCursor!=null && mSmsCursor.getCount()!=0)  {
            mSmsCursor.moveToFirst();
            parse_fraze(mSmsCursor.getString(2));
        }
    }

    private void parse_fraze(String fraze) {

        fraze = fraze.replaceAll("[0-9]", " ");
        fraze = fraze.replaceAll("[-\\\\[\\\\]^/,'*:.!><~@#$%+=?|\\\"\\\\\\\\()]+", " ");
        fraze = fraze.replaceAll("\\s+", " ");

        String[] mWords = fraze.split(" ");
        int countWords = mWords.length;

        for (int i=0;i<countWords;i++)
        {
            String mWordLoop = mWords[i].toLowerCase().trim();
            Log.d("la_rand_parsare",""+mWordLoop);

            if (mWordLoop.length() > 2) {
                mDictionaryFacilitator.addToUserHistory(mWordLoop,false, ngramContext_Single, System.currentTimeMillis()/1000, true);
                if (i + 1 < countWords) {
                    addComposeWords(mWordLoop, mWords[i + 1].toLowerCase().trim());
                }
                delayInsert();
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

            mDictionaryFacilitator.addToUserHistory(firstWord, false, container, System.currentTimeMillis()/1000, true);

            primul = new NgramContext.WordInfo(firstWord, false);
            doilea = new NgramContext.WordInfo("Ss", false);
            treilea =new NgramContext.WordInfo("", true);

            grupare = new NgramContext.WordInfo[]{primul, doilea, treilea};
            container = new NgramContext(3, grupare);

            mDictionaryFacilitator.addToUserHistory(secondWord, false, container, System.currentTimeMillis()/1000, true);
        }
    }


    private void setUp() {
        NgramContext.WordInfo singleWord = new NgramContext.WordInfo("",true);
        NgramContext.WordInfo[] mP = { singleWord, new NgramContext.WordInfo(null),new NgramContext.WordInfo(null)};
        ngramContext_Single = new NgramContext(3,mP);
    }


    private void delayInsert() {
        try {
            simpleCounter++;

            if (simpleCounter>10000) {
                Thread.sleep(2000);
                simpleCounter=0;
            }
        } catch (Exception e) {

        }
    }

}

package com.keyboard_core.thread_pool.callables.service_sms;


import android.database.Cursor;
import android.provider.Telephony;
import android.util.Log;

import com.android.inputmethod.latin.personalization.UserHistoryDictionary;
import com.keyboard_core.thread_pool.callables.ThreadInfo;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;


public class CallAddSms implements Callable<ThreadInfo> {

    private String mSMS;
    private Cursor mCursor;
    private String[] mWords;
    private int mNrOfWords=0;
    private UserHistoryDictionary mDictionary;
    private int cate;
    public CallAddSms(WeakReference<Cursor> mCursor, WeakReference<UserHistoryDictionary> mDictionary, int cate) {
        this.mCursor = mCursor.get();
        this.mDictionary = mDictionary.get();

        this.cate = cate;
    }
    @Override
    public ThreadInfo call() throws Exception {

    //    try {
            mSMS = mCursor.getString(mCursor.getColumnIndex(Telephony.Sms.BODY));
            Log.d("proba","sms : "+mSMS);
            mSMS = mSMS.replaceAll("[0-9]", " ");
            mSMS = mSMS.replaceAll("[-\\\\[\\\\]^/,'*:.!><~@#$%+=?|\\\"\\\\\\\\()]+", " ");
            mSMS = mSMS.replaceAll("\\s+", " ");
            String mWordLoop;

            mWords = mSMS.split(" ");
            mNrOfWords = mWords.length;

            for (int i=0;i<mNrOfWords; i++)
            {
                mWordLoop = mWords[i].trim();
                if (mWordLoop.length()>0)
                {
                    Log.d("testares","CUVINTE --> : "+mWordLoop);
                    mDictionary.addUnigramEntry(mWordLoop,1,false,false,1504777919);
                }
            }
    //    } catch (Exception e) {
     //       Log.d("testare","nasoale 11 : "+e.getMessage());
     //   }

        return null;
    }
}

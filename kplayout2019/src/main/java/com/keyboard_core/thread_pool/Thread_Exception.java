package com.keyboard_core.thread_pool;

import android.util.Log;

/**
 * Created by Didina on 14/09/2017.
 */

public class Thread_Exception implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.w("Thread_Exception", "ThreadName : " + t.getName() + " ERROR : " + e.getMessage());
    }
}
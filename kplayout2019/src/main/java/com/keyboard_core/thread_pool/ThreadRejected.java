package com.keyboard_core.thread_pool;


import android.util.Log;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadRejected implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable nR, ThreadPoolExecutor nE) {
        Log.d("TPE_monitor_key", " Message : " + nR.toString() + " Queue size : " + nE.getQueue().size()+"/");
    }
}
package com.keyboard_core.thread_pool;

import android.util.Log;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolMonitor implements Runnable {
    private ThreadPoolExecutor mTPE;
    private Boolean mR = true;

    public ThreadPoolMonitor(ThreadPoolExecutor nTPE) {
        mTPE = nTPE;
    }

    public void TPM_ShutDown() {
        mR = false;
    }

    @Override
    public void run() {
        while (mR) {
            Log.d("TPE_monitor_key", String.format("[monitor] [%d/%d] Act: %d, Com: %d, Task: %d | in Queue : %s",
                    mTPE.getPoolSize(), mTPE.getCorePoolSize(), mTPE.getActiveCount(), mTPE.getCompletedTaskCount(),
                    mTPE.getTaskCount(), mTPE.getQueue()));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException mE) {
                Log.w("TPE_monitor_key", "Message : " + mE.getMessage());
            }
        }
    }
}
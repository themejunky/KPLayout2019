package com.keyboard_core.thread_pool;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.ThreadFactory;

/**
 * Created by Didina on 14/09/2017.
 */

public class ThreadFactoryBase implements ThreadFactory
{
    private static int mC = 0;

    @Override
    public Thread newThread(@NonNull Runnable nR)
    {
        Thread nT = new Thread(nR);
        try
        {
            nT.setName("Thread_No : " + mC++);  // pt monitor - fun :)
            nT.setPriority(4);                // mai mica decat UI
            nT.setUncaughtExceptionHandler(new Thread_Exception());
        }
        catch(Exception e)
        {
            Log.w("Thread_Factory_Base","Message : "+e.getMessage());
        }
        return nT;
    }
}
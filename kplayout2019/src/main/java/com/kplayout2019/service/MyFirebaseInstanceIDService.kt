package com.theme.junky.ohvaz.service

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {
    private var TAG = "MyFirebaseInstanceIDService"

    override fun onTokenRefresh() {
        //Get updated token
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG,"New Token : "+refreshedToken)

    }
}
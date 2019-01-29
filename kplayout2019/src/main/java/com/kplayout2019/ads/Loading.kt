package com.kplayout2019.ads

import android.app.Activity
import android.app.Dialog
import android.view.Window
import com.kplayout2019.R

class Loading private constructor() {

    interface LoadingInterface {
        fun onLoadingShowing(nContext: Activity)
    }

    private lateinit var mDialog: Dialog

    companion object {
        private val mInstance: Loading = Loading()
        @Synchronized
        fun getInstance(): Loading {
            return mInstance
        }
    }

    fun init(nContext : Activity, nListener : LoadingInterface) {
        try {
            mDialog = Dialog(nContext)
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.setContentView(R.layout.dialog_loading)

            mDialog.show()

        } finally {
            nListener.onLoadingShowing(nContext)
        }
    }

    fun close() {
        if (mDialog!=null && mDialog.isShowing) {
            mDialog.dismiss()
        }
    }
}
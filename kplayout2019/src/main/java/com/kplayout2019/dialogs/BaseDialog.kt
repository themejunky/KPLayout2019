package com.kplayout2019.dialogs

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window

open class BaseDialog(nContext: Context) : View.OnClickListener  {

    private var mIsPopUpReady = false
    lateinit var mDialog: Dialog

    var mContext = nContext

    protected fun create() {
        mDialog = Dialog(mContext)
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        mDialog.setCanceledOnTouchOutside(true)
        mIsPopUpReady = true
    }

    fun showDialog() {
        if (mIsPopUpReady && !mDialog.isShowing) mDialog.show()
    }

    fun dismissDialog() {
        if (mIsPopUpReady && mDialog.isShowing)
            mIsPopUpReady = false
        mDialog.dismiss()
    }

    override fun onClick(v: View) {
    }

}
package com.kplayout2019.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.util.Log
import android.widget.Button
import com.kplayout2019.R
import com.kplayout2019.utils.Tools


class MotherIsNotActive private constructor() {

    companion object {
        private val mInstance: MotherIsNotActive = MotherIsNotActive()
        @Synchronized
        fun getInstance(): MotherIsNotActive {
            return mInstance
        }
    }

    fun init(nContext : Activity) : MotherIsNotActive {
        Log.d("afwef","1")
        val dialogBuilder = AlertDialog.Builder(nContext)
        Log.d("afwef","2")
        val inflater = nContext.layoutInflater
        Log.d("afwef","3")
        val dialogView = inflater.inflate(R.layout.dialog_mother_is_not_active, null)
        Log.d("afwef","4")
        dialogBuilder.setView(dialogView)
        Log.d("afwef","5")
        val mDialog = dialogBuilder.create()
        Log.d("afwef","6")
        val button1 = dialogView.findViewById(R.id.nView1) as Button
        val button2 = dialogView.findViewById(R.id.nView2) as Button
        button1.setOnClickListener {mDialog.dismiss()  }
        button2.setOnClickListener {
            Tools().directApply(nContext)
            mDialog.dismiss() }
        Log.d("afwef","7")
        mDialog.show()
        return this
    }
}
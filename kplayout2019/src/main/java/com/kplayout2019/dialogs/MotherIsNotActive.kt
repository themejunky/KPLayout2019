package com.kplayout2019.dialogs

import android.app.Activity
import android.app.AlertDialog
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
        val dialogBuilder = AlertDialog.Builder(nContext)
        val inflater = nContext.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_mother_is_not_active, null)
        dialogBuilder.setView(dialogView)
        val mDialog = dialogBuilder.create()
        val button1 = dialogView.findViewById(R.id.nView1) as Button
        val button2 = dialogView.findViewById(R.id.nView2) as Button
        button1.setOnClickListener {mDialog.dismiss()  }
        button2.setOnClickListener {
            Tools().directApply(nContext)
            mDialog.dismiss() }
        mDialog.show()
        return this
    }
}
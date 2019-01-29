package com.kplayout2019.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.widget.Button
import com.kplayout2019.R

class MotherIsNotInstalled private constructor() {

    companion object {
        private val mInstance: MotherIsNotInstalled = MotherIsNotInstalled()
        @Synchronized
        fun getInstance(): MotherIsNotInstalled {
            return mInstance
        }
    }

    fun init(nActivity : Activity) : MotherIsNotInstalled {

        val dialogBuilder = AlertDialog.Builder(nActivity)
        val inflater = nActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_mother_is_not_installed, null)
        dialogBuilder.setView(dialogView)
        val mDialog = dialogBuilder.create()
        val button1 = dialogView.findViewById(R.id.nView1) as Button
        val button2 = dialogView.findViewById(R.id.nView2) as Button
        button1.setOnClickListener {mDialog.dismiss()  }
        button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.data = Uri.parse(nActivity.getString(R.string.mother_url))
            nActivity.startActivity(intent)

            mDialog.dismiss()}
        mDialog.show()
        return this

    }
}
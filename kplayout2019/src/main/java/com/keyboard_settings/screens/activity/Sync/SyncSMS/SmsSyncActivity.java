package com.keyboard_settings.screens.activity.Sync.SyncSMS;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import com.kplayout2019.R;


public class SmsSyncActivity extends Activity implements View.OnClickListener {
    private Button mStartButton;
    private final int CODE_SMS_PERMISSION = 1;
    /**
     * Create the main activity.
     *
     * @param savedInstanceState previously saved instance data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tj_sms);

        mStartButton = (Button) findViewById(R.id.start);
        mStartButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.start) {
            mCheckSmsReadPermission();
        }
    }

    private void mCheckSmsReadPermission() {
        final String[] mPermissions = new String[] {Manifest.permission.READ_SMS};
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,mPermissions, CODE_SMS_PERMISSION);
        } else {
            startServiceSms();
        }
    }

    private void startServiceSms() {
        startService(new Intent(this,SmsSyncService.class));
        onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String persmissions[], int[] grantResults) {
        switch (requestCode) {
            case CODE_SMS_PERMISSION : {
                if (checkPermission(grantResults)) {
                    startServiceSms();
                    //sms permission granted
                } else {
                    //sms permisssion not granted
                }
            }
        }
    }
    public boolean checkPermission(int[] grantResults) {
        if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
}

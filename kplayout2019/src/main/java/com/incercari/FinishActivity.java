package com.incercari;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Didina on 05/10/2017.
 */

public class FinishActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       // Toast.makeText(this,"INCHIDE",Toast.LENGTH_SHORT).show();
        finish();
    }
}
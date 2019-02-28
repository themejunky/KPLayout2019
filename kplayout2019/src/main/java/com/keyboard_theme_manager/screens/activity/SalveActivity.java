package com.keyboard_theme_manager.screens.activity;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.kplayout2019.R;


public class SalveActivity extends Activity implements View.OnClickListener {

    protected ImageView mBarImage;
    protected TextView mBarText;

    protected void commonInit() {
        mBarImage = (ImageView) findViewById(
                R.id.barImage);
        mBarText = (TextView) findViewById(R.id.barTitle);

        mBarImage.setOnClickListener(this);
        mBarText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.barImage) {
            super.onBackPressed();
        }
    }
}

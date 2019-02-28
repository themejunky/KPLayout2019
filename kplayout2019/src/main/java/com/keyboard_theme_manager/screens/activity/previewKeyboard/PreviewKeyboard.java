package com.keyboard_theme_manager.screens.activity.previewKeyboard;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.kplayout2019.R;


public class PreviewKeyboard extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preview);
        findViewById(R.id.containerPreview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();

        CustomEditText yourEditText= (CustomEditText) findViewById(R.id.mTest);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(yourEditText, InputMethodManager.SHOW_IMPLICIT);
        yourEditText.requestFocus();
    }
}

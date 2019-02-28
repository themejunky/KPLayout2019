package com.keyboard_theme_manager;

import android.os.Bundle;
import android.view.View;

import com.MainFlavorActivity;
import com.android.inputmethod.latin.NgramContext;
import com.android.inputmethod.latin.common.Constants;
import com.crashlytics.android.Crashlytics;

import com.kplayout2019.R;
import io.fabric.sdk.android.Fabric;

import me.drakeet.materialdialog.MaterialDialog;

import static com.android.inputmethod.latin.LatinIME.mDictionaryFacilitator;

public class MainActivity extends MainFlavorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());


        final NgramContext.WordInfo ceva_first = new NgramContext.WordInfo("",true);
        NgramContext.WordInfo[] mP = { ceva_first, new NgramContext.WordInfo(null),new NgramContext.WordInfo(null)};
        final NgramContext ngramContext = new NgramContext(3,mP);


        final String word = getIntent().getStringExtra("word");

        if (word!=null && word.length()>0) {
       // setContentView(R.layout.tj_onboarding_popup);

            getIntent().putExtra("word", "");

            final MaterialDialog asdad = new MaterialDialog(this);
            asdad.setTitle("Remove word");
            asdad.setMessage("Are you sure you want to remove " + word + " from Dictionary?");


            asdad.setPositiveButton("Yes", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    asdad.dismiss();

                    if (mDictionaryFacilitator.isActive()) {
                        mDictionaryFacilitator.unlearnFromUserHistory(word, ngramContext, 1506001596, Constants.EVENT_REJECTION);
                        mDictionaryFacilitator.unlearnFromUserHistory(word.toLowerCase(), ngramContext, 1506001596, Constants.EVENT_REJECTION);
                    }


                    MainActivity.this.onBackPressed();
                    MainActivity.this.finish();
                }
            });
            asdad.setNegativeButton("No", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    asdad.dismiss();
                    MainActivity.this.onBackPressed();
                    MainActivity.this.finish();

                }
            });
            asdad.setCanceledOnTouchOutside(false);
            asdad.show();
        } else {
            setContentView(R.layout.tj_main_activity);
            setUpViews();
            setUpViewPager();
        }

    }


}

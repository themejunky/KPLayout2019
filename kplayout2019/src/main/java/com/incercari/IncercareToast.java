package com.incercari;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.android.inputmethod.latin.NgramContext;
import com.android.inputmethod.latin.common.Constants;

import me.drakeet.materialdialog.MaterialDialog;

import static com.android.inputmethod.latin.LatinIME.mDictionaryFacilitator;


public class IncercareToast extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
      //  getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

      //  set

        final NgramContext.WordInfo ceva_first = new NgramContext.WordInfo("",true);
        NgramContext.WordInfo[] mP = { ceva_first, new NgramContext.WordInfo(null),new NgramContext.WordInfo(null)};
        final NgramContext ngramContext = new NgramContext(3,mP);

      //  Toast.makeText(this,""+mDictionaryFacilitator.isActive(),Toast.LENGTH_LONG).show();
     //   mDictionaryFacilitator.dumpDictionaryForDebug(Dictionary.TYPE_USER_HISTORY);

        final String word = getIntent().getStringExtra("word");
        if (word.length()>0) {

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
                    //IncercareToast.this.onBackPressed();
                    IncercareToast.this.finish();

                    Intent intentFinish = new Intent(IncercareToast.this, FinishActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentFinish);
                    finish();
                }
            });
            asdad.setNegativeButton("No", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    asdad.dismiss();

                    IncercareToast.this.finish();
                    Intent intentFinish = new Intent(IncercareToast.this, FinishActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentFinish);
                    finish();
                }
            });
            asdad.setCanceledOnTouchOutside(false);
            asdad.show();
        }

    }
}

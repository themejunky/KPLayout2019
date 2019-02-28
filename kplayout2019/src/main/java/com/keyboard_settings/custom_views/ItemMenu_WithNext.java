package com.keyboard_settings.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kplayout2019.R;


public class ItemMenu_WithNext extends LinearLayout {

    public ItemMenu_WithNext(Context context) {
        super(context);
    }

    public ItemMenu_WithNext(Context nContext,AttributeSet nAttrs)
    {
        super(nContext, nAttrs);

        TypedArray mTypedarray = nContext.obtainStyledAttributes(nAttrs, R.styleable.KeyBoardSettings);
        inflate(nContext, R.layout.tj_customview_menuitem_withnext,this);

        ((TextView) findViewById(R.id.mTitle)).setText(mTypedarray.getString(R.styleable.KeyBoardSettings_title));
        ((TextView) findViewById(R.id.mSubtitle)).setText(mTypedarray.getString(R.styleable.KeyBoardSettings_subtitle));
        ((ImageView) findViewById(R.id.mImage)).setBackground(mTypedarray.getDrawable(R.styleable.KeyBoardSettings_image));

        mTypedarray.recycle();
    }
}

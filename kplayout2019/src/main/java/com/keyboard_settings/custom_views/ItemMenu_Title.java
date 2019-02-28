package com.keyboard_settings.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kplayout2019.R;


public class ItemMenu_Title extends LinearLayout {
    public ItemMenu_Title(Context context) {
        super(context);
    }

    public ItemMenu_Title(Context nContext,AttributeSet nAttrs)
    {
        super(nContext, nAttrs);

        TypedArray mTypedarray = nContext.obtainStyledAttributes(nAttrs, R.styleable.KeyBoardSettings);
        inflate(nContext, R.layout.tj_customview_menuitem_title,this);

        ((TextView) findViewById(R.id.mTitle)).setText(mTypedarray.getString(R.styleable.KeyBoardSettings_title));

        mTypedarray.recycle();
    }
}

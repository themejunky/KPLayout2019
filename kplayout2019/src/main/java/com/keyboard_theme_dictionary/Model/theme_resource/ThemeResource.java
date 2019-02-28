package com.keyboard_theme_dictionary.Model.theme_resource;


import android.graphics.drawable.Drawable;

import com.keyboard_theme_manager.screens.fragments.utils.adapter.AdapterConstants;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.interf.ViewType;


public class ThemeResource implements ViewType {

    public int type = AdapterConstants.Themes;

    public ThemeResource_Listing listing;

    public Drawable keyboardBackground;
    public Drawable theme_preview;

    public ThemeResource_Icons icons;
    public ThemeResource_KeyBackground keyBackground;
    public ThemeResource_ActionSymbol actionSymbol;
    public ThemeResource_ShiftSymbol shiftSymbol;
    public ThemeResource_KeyAlternatives keyAlternatives;
    public ThemeResource_Topbar topbar;
    public ThemeResource_MoreKeys moreKeys;
    public ThemeResource_KeyHintLabel hintLabel;
    public ThemeResource_KeyLabel keyLabel;
    public ThemeResource_KeyPreview keyPreview;


    public Drawable deleteSymbol;
    public Drawable spaceSymbol;
    public Drawable emojiSymbol;
    public int swipeTrailColor;
    public int menuViewTextColor;
    public int emojiViewTextColor;

    @Override
    public void setViewType(int nType) {
        this.type = nType;
    }

    @Override
    public int getViewType() {
        return  this.type;
    }
}

package com.keyboard_theme_dictionary.Model.theme_resource;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;

public class ThemeResource_KeyLabel {
    public ColorStateList normal;
    public ColorStateList noPreview;
    public Typeface font;

    public int select(boolean hasPreview, int[] state) {
        ColorStateList stateList = hasPreview ? normal : noPreview;
        return stateList.getColorForState(state, Color.BLACK);
    }
}

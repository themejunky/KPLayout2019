package com.keyboard_theme_dictionary.Model.theme_resource;

import android.graphics.drawable.Drawable;

public class ThemeResource_ShiftSymbol {
    public Drawable def;
    public Drawable pressed;
    public Drawable locked;

    public Drawable select(boolean pressed, boolean locked) {
        if (locked) return this.locked;
        if (pressed) return this.pressed;
        return def;
    }
}

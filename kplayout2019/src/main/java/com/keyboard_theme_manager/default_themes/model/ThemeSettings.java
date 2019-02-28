package com.keyboard_theme_manager.default_themes.model;

import com.keyboard_theme_manager.screens.fragments.utils.adapter.AdapterConstants;
import com.keyboard_theme_manager.screens.fragments.utils.adapter.interf.ViewType;

public class ThemeSettings implements ViewType
{
    public String theme_name;
    public String theme_type;
    public String theme_preview;
    public String theme_font_family;

    public String keyboard_background;
    public ThemeSettings_KeyBackground key_background;
    public ThemeSettings_KeyPreview key_preview;
    public ThemeSettings_KeyLabelColor key_label_color;
    public String swipe_trail_color;
    public String symbol_space;
    public String symbol_delete;

    public ThemeSettings_Icons icons;
    public ThemeSettings_SymbolAction symbol_action;
    public ThemeSettings_SymbolShift symbol_shift;
    public ThemeSettings_KeyAlternatives key_alternatives;
    public ThemeSettings_MoreKeys more_keys;

    @Override
    public int getViewType() {
        return AdapterConstants.Themes;
    }

    @Override
    public void setViewType(int nType) {
        //this.type = nType;
    }
}

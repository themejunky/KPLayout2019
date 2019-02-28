package com.keyboard_theme_dictionary.Model.theme_settings;


public class ThemeSettings {

    public String theme_name;
    public String theme_type;  // default/installed
    public String theme_preview;

    public String keyboard_background;
    public ThemeSettings_KeyBackground key_background;
    public ThemeSettings_KeyPreview key_preview;
    public ThemeSettings_KeyLabelColor key_label_color;
    public String swipe_trail_color;
    public String symbol_space;
    public String symbol_delete;
    public ThemeSettings_SymbolAction symbol_action;
    public ThemeSettings_SymbolShift symbol_shift;
    public ThemeSettings_KeyAlternatives key_alternatives;
    public ThemeSettings_MoreKeys more_keys;
}


package com.android.inputmethod.keyboard.emoji_themejunky;

import android.content.Context;

/**
 * Created by Didina on 25/10/2017.
 */

public interface EmojiconRecents {
    public void addRecentEmoji(Context context, Emojicon emojicon);


    void refreshRecentEmojis();
}

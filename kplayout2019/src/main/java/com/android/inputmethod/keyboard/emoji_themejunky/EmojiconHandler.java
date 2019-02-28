package com.android.inputmethod.keyboard.emoji_themejunky;

import android.content.Context;
import android.os.Build;
import android.text.Spannable;
import android.util.Log;
import android.util.SparseIntArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Didina on 25/10/2017.
 */

public class EmojiconHandler {


    private static final SparseIntArray sEmojisMap = new SparseIntArray(1029);
    private static final SparseIntArray sEmojisSupportMap = new SparseIntArray(1029);
    private static final SparseIntArray sSoftbanksMap = new SparseIntArray(471);
    public static Map<String, Integer> sEmojisModifiedMap = new HashMap<>();


    public static int getResourceId(Context context, String pVariableName, String pResourcename, String pPackageName) {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void reloadResources() {
        if (EmojIconActions.emojiPackageContext != null) {
            sEmojisMap.put(0x1f600, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f600", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f62c, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f62c", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f601, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f601", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f602, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f602", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f603, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f603", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f604, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f604", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f605, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f605", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f606, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f606", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f607, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f607", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f608, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f608", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f47f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f47f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f609, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f609", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f642, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f642", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f643, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f643", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x263a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_263a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f60a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f60a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f60b, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f60b", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f60c, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f60c", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f60d, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f60d", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f618, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f618", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f617, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f617", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f619, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f619", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f61a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f61a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f61c, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f61c", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f61d, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f61d", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f61b, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f61b", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f911, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f911", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f913, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f913", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f60e, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f60e", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f917, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f917", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f60f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f60f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f636, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f636", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f610, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f610", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f611, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f611", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f612, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f612", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f644, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f644", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f914, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f914", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f633, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f633", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f61e, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f61e", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f61f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f61f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f620, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f620", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f621, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f621", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f614, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f614", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f615, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f615", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f641, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f641", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x2639, getResourceId(EmojIconActions.emojiPackageContext, "emoji_2639", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f623, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f623", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f616, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f616", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f62b, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f62b", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f629, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f629", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f624, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f624", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f62e, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f62e", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f631, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f631", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f628, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f628", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f630, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f630", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f62f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f62f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f626, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f626", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f627, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f627", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f622, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f622", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f625, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f625", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f62a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f62a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f613, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f613", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f62d, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f62d", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f635, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f635", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f632, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f632", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f910, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f910", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f637, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f637", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f912, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f912", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f915, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f915", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f634, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f634", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
        }

        if (EmojIconActions.emojiPackageContext != null) {
            sEmojisSupportMap.put(0x1f600, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f600", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f62c, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f62c", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f601, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f601", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f602, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f602", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f603, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f603", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f604, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f604", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f605, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f605", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f606, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f606", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f607, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f607", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f608, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f608", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f47f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f47f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f609, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f609", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f642, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f642", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f643, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f643", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x263a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_263a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f60a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f60a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f60b, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f60b", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f60c, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f60c", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f60d, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f60d", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f618, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f618", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f617, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f617", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f619, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f619", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f61a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f61a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f61c, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f61c", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f61d, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f61d", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f61b, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f61b", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f911, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f911", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f913, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f913", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f60e, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f60e", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f917, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f917", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f60f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f60f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f636, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f636", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f610, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f610", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f611, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f611", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f612, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f612", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f644, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f644", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f914, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f914", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f633, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f633", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f61e, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f61e", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f61f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f61f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f620, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f620", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f621, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f621", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f614, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f614", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f615, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f615", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f641, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f641", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x2639, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_2639", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f623, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f623", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f616, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f616", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f62b, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f62b", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f629, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f629", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f624, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f624", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f62e, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f62e", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f631, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f631", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f628, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f628", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f630, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f630", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f62f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f62f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f626, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f626", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f627, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f627", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f622, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f622", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f625, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f625", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f62a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f62a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f613, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f613", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f62d, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f62d", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f635, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f635", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f632, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f632", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f910, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f910", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f637, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f637", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f912, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f912", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f915, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f915", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f634, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f634", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
        }
    }

    static {
        // People
        if (EmojIconActions.emojiPackageContext != null) {
            sEmojisMap.put(0x1f600, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f600", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f62c, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f62c", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f601, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f601", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f602, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f602", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f603, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f603", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f604, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f604", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f605, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f605", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f606, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f606", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f607, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f607", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f608, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f608", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f47f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f47f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f609, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f609", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f642, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f642", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f643, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f643", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x263a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_263a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f60a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f60a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f60b, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f60b", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f60c, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f60c", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f60d, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f60d", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f618, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f618", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f617, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f617", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f619, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f619", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f61a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f61a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f61c, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f61c", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f61d, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f61d", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f61b, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f61b", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f911, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f911", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f913, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f913", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f60e, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f60e", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f917, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f917", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f60f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f60f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f636, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f636", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f610, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f610", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f611, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f611", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f612, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f612", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f644, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f644", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f914, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f914", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f633, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f633", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f61e, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f61e", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f61f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f61f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f620, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f620", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f621, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f621", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f614, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f614", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f615, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f615", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f641, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f641", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x2639, getResourceId(EmojIconActions.emojiPackageContext, "emoji_2639", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f623, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f623", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f616, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f616", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f62b, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f62b", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f629, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f629", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f624, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f624", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f62e, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f62e", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f631, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f631", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f628, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f628", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f630, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f630", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f62f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f62f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f626, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f626", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f627, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f627", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f622, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f622", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f625, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f625", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f62a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f62a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f613, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f613", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f62d, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f62d", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f635, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f635", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f632, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f632", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f910, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f910", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f637, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f637", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f912, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f912", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f915, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f915", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisMap.put(0x1f634, getResourceId(EmojIconActions.emojiPackageContext, "emoji_1f634", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
        }


    }


    static {

        if (EmojIconActions.emojiPackageContext != null) {
            sEmojisSupportMap.put(0x1f600, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f600", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f62c, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f62c", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f601, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f601", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f602, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f602", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f603, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f603", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f604, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f604", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f605, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f605", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f606, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f606", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f607, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f607", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f608, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f608", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f47f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f47f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f609, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f609", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f642, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f642", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f643, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f643", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x263a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_263a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f60a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f60a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f60b, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f60b", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f60c, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f60c", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f60d, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f60d", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f618, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f618", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f617, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f617", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f619, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f619", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f61a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f61a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f61c, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f61c", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f61d, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f61d", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f61b, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f61b", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f911, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f911", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f913, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f913", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f60e, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f60e", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f917, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f917", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f60f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f60f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f636, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f636", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f610, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f610", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f611, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f611", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f612, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f612", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f644, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f644", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f914, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f914", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f633, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f633", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f61e, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f61e", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f61f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f61f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f620, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f620", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f621, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f621", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f614, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f614", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f615, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f615", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f641, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f641", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x2639, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_2639", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f623, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f623", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f616, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f616", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f62b, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f62b", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f629, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f629", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f624, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f624", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f62e, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f62e", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f631, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f631", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f628, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f628", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f630, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f630", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f62f, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f62f", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f626, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f626", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f627, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f627", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f622, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f622", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f625, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f625", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f62a, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f62a", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f613, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f613", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f62d, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f62d", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f635, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f635", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f632, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f632", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f910, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f910", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f637, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f637", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f912, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f912", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f915, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f915", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
            sEmojisSupportMap.put(0x1f634, getResourceId(EmojIconActions.emojiPackageContext, "emoji_support_1f634", "drawable", EmojIconActions.emojiPackageContext.getPackageName()));
        }

    }

    private boolean isSoftBankEmoji(char c) {
        return ((c >> 12) == 0xe);
    }

    private int getEmojiResource( int codePoint, boolean popup) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (popup) {
                int resId = sEmojisSupportMap.get(codePoint);
                if (resId > 0) {
                    return resId;
                } else {
                    Log.d("get_emojii_res","1");
                    return sEmojisMap.get(codePoint);
                }
            } else {
                Log.d("get_emojii_res","3");
                return sEmojisMap.get(codePoint);
            }

        } else {
            int resId = sEmojisSupportMap.get(codePoint);
            if (resId > 0) {
                return resId;
            } else {
                Log.d("get_emojii_res","2");
                return sEmojisMap.get(codePoint);
            }
        }

    }

    private int getSoftbankEmojiResource(char c) {
        return sSoftbanksMap.get(c);
    }

    /**
     * Convert emoji characters of the given Spannable to the according emojicon.
     *
     * @param context
     * @param text
     * @param emojiSize
     * @param emojiAlignment
     * @param textSize
     */
    public void addEmojis(Context context, Spannable text, int emojiSize, int emojiAlignment, int textSize) {
        addEmojis(context, text, emojiSize, emojiAlignment, textSize, 0, -1, false);
    }

    /**
     * Convert emoji characters of the given Spannable to the according emojicon.
     *
     * @param context
     * @param text
     * @param emojiSize
     * @param emojiAlignment
     * @param textSize
     * @param index
     * @param length
     */
    public void addEmojis(Context context, Spannable text, int emojiSize, int emojiAlignment, int textSize, int index, int length, boolean popup) {
        addEmojis(context, text, emojiSize, emojiAlignment, textSize, index, length, false,popup);
    }

    /**
     * Convert emoji characters of the given Spannable to the according emojicon.
     *
     * @param context
     * @param text
     * @param emojiSize
     * @param emojiAlignment
     * @param textSize
     * @param useSystemDefault
     */
    public void addEmojis(Context context, Spannable text, int emojiSize, int emojiAlignment, int textSize, boolean useSystemDefault, boolean popup) {
        addEmojis(context, text, emojiSize, emojiAlignment, textSize, 0, -1, useSystemDefault,popup);
    }

    /**
     * Convert emoji characters of the given Spannable to the according emojicon.
     *
     * @param context
     * @param text
     * @param emojiSize
     * @param emojiAlignment
     * @param textSize
     * @param index
     * @param length
     * @param useSystemDefault
     */
    public void addEmojis(Context context, Spannable text, int emojiSize, int emojiAlignment, int textSize, int index, int length, boolean useSystemDefault, boolean popup) {
        if (useSystemDefault) {
            return;
        }

        int textLength = text.length();
        int textLengthToProcessMax = textLength - index;
        int textLengthToProcess = length < 0 || length >= textLengthToProcessMax ? textLength : (length + index);

        // remove spans throughout all text
        EmojiconSpan[] oldSpans = text.getSpans(0, textLength, EmojiconSpan.class);
        for (int i = 0; i < oldSpans.length; i++) {
            text.removeSpan(oldSpans[i]);
        }

        int skip;
        for (int i = index; i < textLengthToProcess; i += skip) {
            skip = 0;
            int icon = 0;
            char c = text.charAt(i);
            if (isSoftBankEmoji(c)) {
                icon = getSoftbankEmojiResource(c);
                skip = icon == 0 ? 0 : 1;
            }

            if (icon == 0) {
                int unicode = Character.codePointAt(text, i);
                skip = Character.charCount(unicode);

                if (unicode > 0xff) {
                    icon = getEmojiResource( unicode,popup);
                }

                if (i + skip < textLengthToProcess) {
                    int followUnicode = Character.codePointAt(text, i + skip);
                    //Non-spacing mark (Combining mark)
                    if (followUnicode == 0xfe0f) {
                        int followSkip = Character.charCount(followUnicode);
                        if (i + skip + followSkip < textLengthToProcess) {

                            int nextFollowUnicode = Character.codePointAt(text, i + skip + followSkip);
                            if (nextFollowUnicode == 0x20e3) {
                                int nextFollowSkip = Character.charCount(nextFollowUnicode);
                                int tempIcon = getKeyCapEmoji(unicode);

                                if (tempIcon == 0) {
                                    followSkip = 0;
                                    nextFollowSkip = 0;
                                } else {
                                    icon = tempIcon;
                                }
                                skip += (followSkip + nextFollowSkip);
                            }
                        }
                    } else if (followUnicode == 0x20e3) {
                        //some older versions of iOS don't use a combining character, instead it just goes straight to the second part
                        int followSkip = Character.charCount(followUnicode);

                        int tempIcon = getKeyCapEmoji(unicode);
                        if (tempIcon == 0) {
                            followSkip = 0;
                        } else {
                            icon = tempIcon;
                        }
                        skip += followSkip;

                    } else {
                        //handle other emoji modifiers
                        int followSkip = Character.charCount(followUnicode);

                        //TODO seems like we could do this for every emoji type rather than having that giant static map, maybe this is too slow?
                        String hexUnicode = Integer.toHexString(unicode);
                        String hexFollowUnicode = Integer.toHexString(followUnicode);

                        String resourceName = "emoji_" + hexUnicode + "_" + hexFollowUnicode;

                        int resourceId = 0;
                        if (sEmojisModifiedMap.containsKey(resourceName)) {
                            resourceId = sEmojisModifiedMap.get(resourceName);
                        } else {
                            resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
                            if (resourceId != 0) {
                                sEmojisModifiedMap.put(resourceName, resourceId);
                            }
                        }

                        if (resourceId == 0) {
                            followSkip = 0;
                        } else {
                            icon = resourceId;
                        }
                        skip += followSkip;
                    }
                }
            }

            if (icon > 0) {
                text.setSpan(new EmojiconSpan(context, icon, emojiSize), i, i + skip, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }



    private static int getKeyCapEmoji(int unicode) {
        int icon = 0;
        switch (unicode) {
          /*  case 0x0023:
                icon = R.drawable.emoji_0023;
                break;
            case 0x002a:
                icon = R.drawable.emoji_002a_20e3;
                break;
            case 0x0030:
                icon = R.drawable.emoji_0030;
                break;
            case 0x0031:
                icon = R.drawable.emoji_0031;
                break;
            case 0x0032:
                icon = R.drawable.emoji_0032;
                break;
            case 0x0033:
                icon = R.drawable.emoji_0033;
                break;
            case 0x0034:
                icon = R.drawable.emoji_0034;
                break;
            case 0x0035:
                icon = R.drawable.emoji_0035;
                break;
            case 0x0036:
                icon = R.drawable.emoji_0036;
                break;
            case 0x0037:
                icon = R.drawable.emoji_0037;
                break;
            case 0x0038:
                icon = R.drawable.emoji_0038;
                break;
            case 0x0039:
                icon = R.drawable.emoji_0039;
                break;*/
            default:
                break;
        }
        return icon;
    }


}

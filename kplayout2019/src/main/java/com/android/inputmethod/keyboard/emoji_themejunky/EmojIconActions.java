package com.android.inputmethod.keyboard.emoji_themejunky;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

/**
 * Created by Didina on 25/10/2017.
 */

public class EmojIconActions {
    private EmojiCustomListener mEmojiCustomListener;


    public static EmojiActionListener emojiActionListener;
    public  static TjEmojiPalettesView popup;
    public static Context context;
    public static Context emojiPackageContext;
    InputMethodService emojiconEditText;
    public static EmojiconHandler emojiconHandler;


    public EmojIconActions(Context ctx, Context emojiPackageContext, EmojiActionListener emojiActionListener) {

        this.emojiPackageContext = emojiPackageContext;
        this.emojiActionListener = emojiActionListener;
        this.context = ctx;
        // this.popup = new EmojiconsPopup(rootView, ctx, useSystemEmoji);
        long start = System.currentTimeMillis();
        emojiconHandler = new EmojiconHandler();

        Log.e("loading_time_emoji"," "+(start-System.currentTimeMillis()));




    }



    public void ShowEmojIcon(View rootView, Context context, final EmojiCustomListener emojiCustomListener, RelativeLayout container) {
        mEmojiCustomListener = emojiCustomListener;


        this.popup = new TjEmojiPalettesView(container, context, true,emojiCustomListener);



        //Will automatically set size according to the soft keyboard size
        //  popup.setSizeForSoftKeyboard();
        popup.setHeight(rootView.getHeight());


        //If the emoji popup is dismissed, change emojiButton to smiley icon
        popup.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                //  changeEmojiKeyboardIcon(emojiButton, SmileyIcons);
            }
        });



        //On emoji clicked, add it to edittext
        popup.setOnEmojiconClickedListener(new EmojiconGridView.OnEmojiconClickedListener() {

            @Override
            public void onEmojiconClicked(Emojicon nEmojiIcon) {
                if (mEmojiCustomListener == null || nEmojiIcon == null) {
                    return;
                }
                mEmojiCustomListener.customEmoji_Commit(nEmojiIcon.getEmoji());
            }
        });

        popup.showAtBottom();
    }
}
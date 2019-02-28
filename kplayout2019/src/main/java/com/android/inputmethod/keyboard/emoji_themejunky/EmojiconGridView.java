package com.android.inputmethod.keyboard.emoji_themejunky;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;

import com.android.inputmethod.keyboard.emoji_themejunky.EmojiDex.People;
import com.kplayout2019.R;

import java.util.Arrays;

/**
 * Created by Didina on 25/10/2017.
 */

public class EmojiconGridView {
    public View rootView;
    TjEmojiPalettesView mEmojiconPopup;
    EmojiconRecents mRecents;
    Emojicon[] mData;
    private boolean mUseSystemDefault = false;
    private int myLastVisiblePos;
    private boolean animationRunning;


    public EmojiconGridView(final Context context, Emojicon[] emojicons, EmojiconRecents recents, TjEmojiPalettesView emojiconPopup, boolean useSystemDefault) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        mEmojiconPopup = emojiconPopup;
        rootView = inflater.inflate(R.layout.emojicon_grid, null);
        setRecents(recents);
        GridView gridView = (GridView) rootView.findViewById(R.id.Emoji_GridView);
        final ImageButton fbMoreEmojis = (ImageButton) rootView.findViewById(R.id.fbGetMoreEmoji);

        if (emojicons== null) {
            mData = People.DATA;
        } else {
            Object[] o = (Object[]) emojicons;
            mData = Arrays.asList(o).toArray(new Emojicon[o.length]);
        }
        EmojiAdapter mAdapter = new EmojiAdapter(rootView.getContext(), mData ,useSystemDefault);
        mAdapter.setEmojiClickListener(new OnEmojiconClickedListener() {

            @Override
            public void onEmojiconClicked(Emojicon emojicon) {
                Log.d("custom_emojii","click_1 : "+emojicon);
                Log.d("custom_emojii","click_1 : "+emojicon.getEmoji());
                Log.d("custom_emojii","click_1 : "+emojicon.getCodePoint());
                Log.d("custom_emojii","click_1 : 040");
                if (mEmojiconPopup.onEmojiconClickedListener != null) {
                    Log.d("custom_emojii","click_1 : 042");
                    mEmojiconPopup.onEmojiconClickedListener.onEmojiconClicked(emojicon);
                }
                if (mRecents != null) {
                    mRecents.addRecentEmoji(rootView.getContext(), emojicon);
                }
            }
        });
        gridView.setAdapter(mAdapter);
    }

    private void setRecents(EmojiconRecents recents) {
        mRecents = recents;
    }

    public interface OnEmojiconClickedListener {
        void onEmojiconClicked(Emojicon emojicon);
    }

}

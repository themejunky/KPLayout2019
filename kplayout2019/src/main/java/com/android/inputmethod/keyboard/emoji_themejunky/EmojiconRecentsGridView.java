package com.android.inputmethod.keyboard.emoji_themejunky;

import android.content.Context;
import android.util.Log;
import android.widget.GridView;
import com.kplayout2019.R;


/**
 * Created by Didina on 25/10/2017.
 */

public class EmojiconRecentsGridView extends EmojiconGridView implements EmojiconRecents {
    private final GridView gridView;
    EmojiAdapter mAdapter;
    private boolean mUseSystemDefault = false;



    public EmojiconRecentsGridView(Context context, Emojicon[] emojicons,
                                   EmojiconRecents recents, TjEmojiPalettesView emojiconsPopup, boolean useSystemDefault) {
        super(context, emojicons, recents, emojiconsPopup,useSystemDefault);
        this.mUseSystemDefault=useSystemDefault;
        EmojiconRecentsManager recents1 = EmojiconRecentsManager
                .getInstance(rootView.getContext());
        mAdapter = new EmojiAdapter(rootView.getContext(),  recents1,mUseSystemDefault);
        mAdapter.setEmojiClickListener(new OnEmojiconClickedListener() {

            @Override
            public void onEmojiconClicked(Emojicon emojicon) {
                Log.d("custom_emojii","click_1 : 05");
                if (mEmojiconPopup.onEmojiconClickedListener != null) {
                    mEmojiconPopup.onEmojiconClickedListener.onEmojiconClicked(emojicon);
                }
            }
        });
        gridView = (GridView) rootView.findViewById(R.id.Emoji_GridView);
        gridView.setAdapter(mAdapter);
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addRecentEmoji(Context context, Emojicon emojicon) {
        EmojiconRecentsManager recents = EmojiconRecentsManager
                .getInstance(context);
        recents.push(emojicon);

        // notify dataset changed
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshRecentEmojis() {
        gridView.setAdapter(mAdapter);
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

}
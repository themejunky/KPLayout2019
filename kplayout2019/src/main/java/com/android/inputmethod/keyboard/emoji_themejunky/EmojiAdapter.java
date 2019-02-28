package com.android.inputmethod.keyboard.emoji_themejunky;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.kplayout2019.R;


import java.util.List;

class EmojiAdapter extends ArrayAdapter<Emojicon> {
    private boolean mUseSystemDefault = false;
    private EmojiconGridView.OnEmojiconClickedListener emojiClickListener;


    EmojiAdapter(Context context, List<Emojicon> data, boolean useSystemDefault) {
        super(context, R.layout.emojicon_item, data);
        mUseSystemDefault = useSystemDefault;
    }

    EmojiAdapter(Context context, Emojicon[] data, boolean useSystemDefault) {
        super(context, R.layout.emojicon_item, data);
        mUseSystemDefault = useSystemDefault;
    }


    void setEmojiClickListener(EmojiconGridView.OnEmojiconClickedListener listener) {
        this.emojiClickListener = listener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = View.inflate(getContext(), R.layout.emojicon_item, null);
            ViewHolder holder = new ViewHolder();

            holder.icon = (PopupEmojiconTextView) v.findViewById(R.id.emojicon_icon);
            holder.icon.setUseSystemDefault(mUseSystemDefault);
            v.setTag(holder);
        }

        final Emojicon emoji = getItem(position);
        emoji.getCodePoint();

        ViewHolder holder = (ViewHolder) v.getTag();

        holder.icon.setVisibility(View.VISIBLE);
        holder.icon.setText(emoji.getEmoji());
        holder.icon.handleAnimationDrawable(true, holder.icon.getText());
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emojiClickListener.onEmojiconClicked(getItem(position));
            }
        });

        return v;
    }

    private class ViewHolder {
        PopupEmojiconTextView icon;
    }
}
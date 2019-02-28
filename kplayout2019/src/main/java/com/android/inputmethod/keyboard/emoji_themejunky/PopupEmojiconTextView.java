package com.android.inputmethod.keyboard.emoji_themejunky;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import com.kplayout2019.R;


/**
 * Created by Didina on 25/10/2017.
 */

public class PopupEmojiconTextView  extends TextView {
    private int mEmojiconSize;
    private int mEmojiconAlignment;
    private int mEmojiconTextSize;
    private int mTextStart = 0;
    private int mTextLength = -1;
    private boolean mUseSystemDefault = false;
    private final Handler mHandler = new Handler();
    private final AnimationTextWatcher mTextWatcher = new AnimationTextWatcher();
    private Runnable runnable;
    private boolean animate;


    public PopupEmojiconTextView(Context context) {
        super(context);
        init(null);
    }

    public PopupEmojiconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PopupEmojiconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mEmojiconTextSize = (int) getTextSize();
        if (attrs == null) {
            mEmojiconSize = (int) getTextSize();
        } else {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Emojicon);
            // mEmojiconSize = (int) a.getDimension(R.styleable.Emojicon_emojiconSize, getTextSize());
            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
            mEmojiconSize = width;
            mEmojiconAlignment = a.getInt(R.styleable.Emojicon_emojiconAlignment, DynamicDrawableSpan.ALIGN_BASELINE);
            mTextStart = a.getInteger(R.styleable.Emojicon_emojiconTextStart, 0);
            mTextLength = a.getInteger(R.styleable.Emojicon_emojiconTextLength, -1);
            mUseSystemDefault = a.getBoolean(R.styleable.Emojicon_emojiconUseSystemDefault, mUseSystemDefault);
            a.recycle();
        }
        setText(getText());
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (!TextUtils.isEmpty(text)) {
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            if (EmojIconActions.emojiconHandler != null)
                EmojIconActions.emojiconHandler.addEmojis(EmojIconActions.emojiPackageContext, builder, mEmojiconSize, mEmojiconAlignment, mEmojiconTextSize, mTextStart, mTextLength, true);
            text = builder;
        }
        super.setText(text, type);
    }

    /**
     * Set the size of emojicon in pixels.
     */
    public void setEmojiconSize(int pixels) {
        mEmojiconSize = pixels;
        super.setText(getText());
    }

    /**
     * Set whether to use system default emojicon
     */
    public void setUseSystemDefault(boolean useSystemDefault) {
        mUseSystemDefault = useSystemDefault;
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        handleAnimationDrawable(false);
        removeTextChangedListener(mTextWatcher);
        mHandler.removeCallbacks(runnable);
    }

    private void handleAnimationDrawable(boolean isPlay) {
        CharSequence text = getText();
        handleAnimationDrawable(isPlay, text);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        CharSequence text = getText();
        //  handleAnimationDrawable(true, text);
    }

    public void handleAnimationDrawable(boolean isPlay, CharSequence text) {
        animate = isPlay;
        if (text instanceof Spanned) {
            Spanned span = (Spanned) text;
            ImageSpan[] spans = span
                    .getSpans(0, span.length(), ImageSpan.class);
            EmojiconSpan[] emojiSpans = span
                    .getSpans(0, span.length(), EmojiconSpan.class);
            for (ImageSpan i : spans) {
                Drawable d = i.getDrawable();
                if (d instanceof AnimationDrawable) {
                    AnimationDrawable ad = (AnimationDrawable) d;
                    if (isPlay) {
                        ad.setCallback(this);
                        ad.start();
                    } else {
                        ad.stop();
                    }
                }

            }

            for (EmojiconSpan i : emojiSpans) {
                Drawable d = i.getDrawable();
                if (d instanceof AnimationDrawable) {
                    AnimationDrawable ad = (AnimationDrawable) d;
                    if (isPlay) {
                        ad.setCallback(this);
                        ad.start();
                    } else {
                        ad.stop();

                    }
                }

            }
        }
    }


    @SuppressLint("WrongCall")
    @Override
    public void invalidateDrawable(Drawable dr) {
        if (animate)
            invalidate();
    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        if (who != null && what != null && animate) {
            this.runnable = what;
            mHandler.postAtTime(what, when);
        }
    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {
        if (who != null && what != null && animate) {
            mHandler.removeCallbacks(what);
        }
    }

    private class AnimationTextWatcher implements TextWatcher {
        @Override
        public void afterTextChanged(Editable s) {
            //  handleAnimationDrawable(true, s);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            //  handleAnimationDrawable(false, s);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }
    }
}
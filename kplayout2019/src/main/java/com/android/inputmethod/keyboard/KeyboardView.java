/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.inputmethod.keyboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.android.inputmethod.keyboard.internal.KeyDrawParams;
import com.android.inputmethod.keyboard.internal.KeyVisualAttributes;
import com.android.inputmethod.keyboard.internal.KeyboardIconsSet;
import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource;
import com.keyboard_theme_manager.default_themes.DefaultThemes;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.utils.TypefaceUtils;
import com.kplayout2019.R;

import java.util.HashSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A view that renders a virtual {@link Keyboard}.
 *
 * @attr ref R.styleable#KeyboardView_keyBackground
 * @attr ref R.styleable#KeyboardView_functionalKeyBackground
 * @attr ref R.styleable#KeyboardView_spacebarBackground
 * @attr ref R.styleable#KeyboardView_spacebarIconWidthRatio
 * @attr ref R.styleable#Keyboard_Key_keyLabelFlags
 * @attr ref R.styleable#KeyboardView_keyHintLetterPadding
 * @attr ref R.styleable#KeyboardView_keyPopupHintLetter
 * @attr ref R.styleable#KeyboardView_keyPopupHintLetterPadding
 * @attr ref R.styleable#KeyboardView_keyShiftedLetterHintPadding
 * @attr ref R.styleable#KeyboardView_keyTextShadowRadius
 * @attr ref R.styleable#KeyboardView_verticalCorrection
 * @attr ref R.styleable#Keyboard_Key_keyTypeface
 * @attr ref R.styleable#Keyboard_Key_keyLetterSize
 * @attr ref R.styleable#Keyboard_Key_keyLabelSize
 * @attr ref R.styleable#Keyboard_Key_keyLargeLetterRatio
 * @attr ref R.styleable#Keyboard_Key_keyLargeLabelRatio
 * @attr ref R.styleable#Keyboard_Key_keyHintLetterRatio
 * @attr ref R.styleable#Keyboard_Key_keyShiftedLetterHintRatio
 * @attr ref R.styleable#Keyboard_Key_keyHintLabelRatio
 * @attr ref R.styleable#Keyboard_Key_keyLabelOffCenterRatio
 * @attr ref R.styleable#Keyboard_Key_keyHintLabelOffCenterRatio
 * @attr ref R.styleable#Keyboard_Key_keyPreviewTextRatio
 * @attr ref R.styleable#Keyboard_Key_keyTextColor
 * @attr ref R.styleable#Keyboard_Key_keyTextColorDisabled
 * @attr ref R.styleable#Keyboard_Key_keyTextShadowColor
 * @attr ref R.styleable#Keyboard_Key_keyHintLetterColor
 * @attr ref R.styleable#Keyboard_Key_keyHintLabelColor
 * @attr ref R.styleable#Keyboard_Key_keyShiftedLetterHintInactivatedColor
 * @attr ref R.styleable#Keyboard_Key_keyShiftedLetterHintActivatedColor
 * @attr ref R.styleable#Keyboard_Key_keyPreviewTextColor
 */
public class KeyboardView extends View {
    // XML attributes
    private final KeyVisualAttributes mKeyVisualAttributes;
    // Default keyLabelFlags from {@link KeyboardTheme}.
    // Currently only "alignHintLabelToBottom" is supported.
    private final int mDefaultKeyLabelFlags;
    private final float mKeyHintLetterPadding;
    private final String mKeyPopupHintLetter;
    private final float mKeyPopupHintLetterPadding;
    private final float mKeyShiftedLetterHintPadding;
    private final float mKeyTextShadowRadius;
    private final float mVerticalCorrection;
    private final Drawable mKeyBackground;
    private final Drawable mFunctionalKeyBackground;
    private final Drawable mSpacebarBackground;
    private final float mSpacebarIconWidthRatio;
    private final Rect mKeyBackgroundPadding = new Rect();
    private static final float KET_TEXT_SHADOW_RADIUS_DISABLED = -1.0f;

    // The maximum key label width in the proportion to the key width.
    private static final float MAX_LABEL_RATIO = 0.90f;

    protected ThemeResource mKeyboardTheme;

    // Main keyboard
    // TODO: Consider having a dummy keyboard object to make this @Nonnull
    @Nullable
    private Keyboard mKeyboard;
    @Nonnull
    private final KeyDrawParams mKeyDrawParams = new KeyDrawParams();

    // Drawing
    /** True if all keys should be drawn */
    private boolean mInvalidateAllKeys;
    /** The keys that should be drawn */
    private final HashSet<Key> mInvalidatedKeys = new HashSet<>();
    /** The working rectangle for clipping */
    private final Rect mClipRect = new Rect();
    /** The keyboard bitmap buffer for faster updates */
    private Bitmap mOffscreenBuffer;
    /** The canvas for the above mutable keyboard bitmap */
    @Nonnull
    private final Canvas mOffscreenCanvas = new Canvas();
    @Nonnull
    private final Paint mPaint = new Paint();
    private final Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();

    private Drawable mKey_Qwerty;
    private Drawable mKey_Numeric;
    private Drawable mKey_Space;
    private Drawable mKey_Functional;
    private Drawable mKey_Symbols;
    private Drawable mKey_Alt;

    public KeyboardView(final Context context, final AttributeSet attrs) {
        this(context, attrs, R.attr.keyboardViewStyle);
    }

    public KeyboardView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        final TypedArray keyboardViewAttr = context.obtainStyledAttributes(attrs,R.styleable.KeyboardView, defStyle, R.style.KeyboardView);

        mKeyboardTheme = DefaultThemes.getmInstance().getCurrentTheme();

        mKey_Qwerty = mKeyboardTheme.keyBackground.mKey_Querty;
        mKey_Numeric = mKeyboardTheme.keyBackground.mKey_Numeric;
        mKey_Space = mKeyboardTheme.keyBackground.mKey_Space;
        mKey_Functional = mKeyboardTheme.keyBackground.mKey_Functional;
        mKey_Symbols = mKeyboardTheme.keyBackground.mKey_Symbols;
        mKey_Alt = mKeyboardTheme.keyBackground.mKey_Alt;




        mKeyBackground = keyboardViewAttr.getDrawable(R.styleable.KeyboardView_keyBackground);
        mKeyBackground.getPadding(mKeyBackgroundPadding);
        final Drawable functionalKeyBackground = keyboardViewAttr.getDrawable(R.styleable.KeyboardView_functionalKeyBackground);
        mFunctionalKeyBackground = (functionalKeyBackground != null) ? functionalKeyBackground : mKeyBackground;
        final Drawable spacebarBackground = keyboardViewAttr.getDrawable(R.styleable.KeyboardView_spacebarBackground);
        mSpacebarBackground = (spacebarBackground != null) ? spacebarBackground : mKeyBackground;
        mSpacebarIconWidthRatio = keyboardViewAttr.getFloat(R.styleable.KeyboardView_spacebarIconWidthRatio, 1.0f);
        mKeyHintLetterPadding = keyboardViewAttr.getDimension(R.styleable.KeyboardView_keyHintLetterPadding, 0.0f);
        mKeyPopupHintLetter = keyboardViewAttr.getString(R.styleable.KeyboardView_keyPopupHintLetter);
        mKeyPopupHintLetterPadding = keyboardViewAttr.getDimension(R.styleable.KeyboardView_keyPopupHintLetterPadding, 0.0f);
        mKeyShiftedLetterHintPadding = keyboardViewAttr.getDimension(R.styleable.KeyboardView_keyShiftedLetterHintPadding, 0.0f);
        mKeyTextShadowRadius = keyboardViewAttr.getFloat(R.styleable.KeyboardView_keyTextShadowRadius, KET_TEXT_SHADOW_RADIUS_DISABLED);
        mVerticalCorrection = keyboardViewAttr.getDimension(R.styleable.KeyboardView_verticalCorrection, 0.0f);
        keyboardViewAttr.recycle();

        final TypedArray keyAttr = context.obtainStyledAttributes(attrs,
                R.styleable.Keyboard_Key, defStyle, R.style.KeyboardView);
        mDefaultKeyLabelFlags = keyAttr.getInt(R.styleable.Keyboard_Key_keyLabelFlags, 0);
        mKeyVisualAttributes = KeyVisualAttributes.newInstance(keyAttr);
        keyAttr.recycle();

        mPaint.setAntiAlias(true);
    }

    @Nullable
    public KeyVisualAttributes getKeyVisualAttribute() {
        return mKeyVisualAttributes;
    }

    private static void blendAlpha(@Nonnull final Paint paint, final int alpha) {
        final int color = paint.getColor();
        paint.setARGB((paint.getAlpha() * alpha) / Constants.Color.ALPHA_OPAQUE,
                Color.red(color), Color.green(color), Color.blue(color));
    }

    public void setHardwareAcceleratedDrawingEnabled(final boolean enabled) {
        if (!enabled) return;
        // TODO: Should use LAYER_TYPE_SOFTWARE when hardware acceleration is off?
        setLayerType(LAYER_TYPE_HARDWARE, null);
    }

    /**
     * Attaches a keyboard to this view. The keyboard can be switched at any time and the
     * view will re-layout itself to accommodate the keyboard.
     * @see Keyboard
     * @see #getKeyboard()
     * @param keyboard the keyboard to display in this view
     */
    public void setKeyboard(@Nonnull final Keyboard keyboard) {
        mKeyboard = keyboard;
        final int keyHeight = keyboard.mMostCommonKeyHeight - keyboard.mVerticalGap;
        mKeyDrawParams.updateParams(keyHeight, mKeyVisualAttributes);
        mKeyDrawParams.updateParams(keyHeight, keyboard.mKeyVisualAttributes);
        invalidateAllKeys();
        requestLayout();
    }

    /**
     * Returns the current keyboard being displayed by this view.
     * @return the currently attached keyboard
     * @see #setKeyboard(Keyboard)
     */
    @Nullable
    public Keyboard getKeyboard() {
        return mKeyboard;
    }

    protected float getVerticalCorrection() {
        return mVerticalCorrection;
    }

    @Nonnull
    protected KeyDrawParams getKeyDrawParams() {
        return mKeyDrawParams;
    }

    protected void updateKeyDrawParams(final int keyHeight) {
        mKeyDrawParams.updateParams(keyHeight, mKeyVisualAttributes);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        final Keyboard keyboard = getKeyboard();
        if (keyboard == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        // The main keyboard expands to the entire this {@link KeyboardView}.
        final int width = keyboard.mOccupiedWidth + getPaddingLeft() + getPaddingRight();
        final int height = keyboard.mOccupiedHeight + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (canvas.isHardwareAccelerated()) {
            onDrawKeyboard(canvas);
            return;
        }

        final boolean bufferNeedsUpdates = mInvalidateAllKeys || !mInvalidatedKeys.isEmpty();
        if (bufferNeedsUpdates || mOffscreenBuffer == null) {
            if (maybeAllocateOffscreenBuffer()) {
                mInvalidateAllKeys = true;
                // TODO: Stop using the offscreen canvas even when in software rendering
                mOffscreenCanvas.setBitmap(mOffscreenBuffer);
            }
            onDrawKeyboard(mOffscreenCanvas);
        }
        canvas.drawBitmap(mOffscreenBuffer, 0.0f, 0.0f, null);
    }

    private boolean maybeAllocateOffscreenBuffer() {
        final int width = getWidth();
        final int height = getHeight();
        if (width == 0 || height == 0) {
            return false;
        }
        if (mOffscreenBuffer != null && mOffscreenBuffer.getWidth() == width
                && mOffscreenBuffer.getHeight() == height) {
            return false;
        }
        freeOffscreenBuffer();
        mOffscreenBuffer = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        return true;
    }

    private void freeOffscreenBuffer() {
        mOffscreenCanvas.setBitmap(null);
        mOffscreenCanvas.setMatrix(null);
        if (mOffscreenBuffer != null) {
            mOffscreenBuffer.recycle();
            mOffscreenBuffer = null;
        }
    }

    protected void drawBackground(Canvas canvas) {

    }

    private void onDrawKeyboard(@Nonnull final Canvas canvas) {
        final Keyboard keyboard = getKeyboard();
        if (keyboard == null) {
            return;
        }


        drawBackground(canvas);
        Log.d("tesatareee","1: "+mKeyboardTheme);

        final Paint paint = mPaint;
        final Drawable background = getBackground();
        // Calculate clip region and set.
        final boolean drawAllKeys = mInvalidateAllKeys || mInvalidatedKeys.isEmpty();
        final boolean isHardwareAccelerated = canvas.isHardwareAccelerated();
        // TODO: Confirm if it's really required to draw all keys when hardware acceleration is on.
        if (drawAllKeys || isHardwareAccelerated) {
            if (!isHardwareAccelerated && background != null) {
                // Need to draw keyboard background on {@link #mOffscreenBuffer}.
                canvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);

                background.draw(canvas);
            }
            // Draw all keys.
            for (final Key key : keyboard.getSortedKeys()) {

                onDrawKey(key, canvas, paint);
            }
        } else {
            for (final Key key : mInvalidatedKeys) {
                if (!keyboard.hasKey(key)) {
                    continue;
                }
                if (background != null) {
                    // Need to redraw key's background on {@link #mOffscreenBuffer}.
                    final int x = key.getX() + getPaddingLeft();
                    final int y = key.getY() + getPaddingTop();
                    mClipRect.set(x, y, x + key.getWidth(), y + key.getHeight());
                    canvas.save();
                    canvas.clipRect(mClipRect);
                    canvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
                    background.draw(canvas);
                    canvas.restore();
                }
                onDrawKey(key, canvas, paint);
            }
        }

        mInvalidatedKeys.clear();
        mInvalidateAllKeys = false;
    }

    private void onDrawKey(@Nonnull final Key key, @Nonnull final Canvas canvas,
            @Nonnull final Paint paint) {
        final int keyDrawX = key.getDrawX() + getPaddingLeft();
        final int keyDrawY = key.getY() + getPaddingTop();
        canvas.translate(keyDrawX, keyDrawY);

        final KeyVisualAttributes attr = key.getVisualAttributes();
        final KeyDrawParams params = mKeyDrawParams.mayCloneAndUpdateParams(key.getHeight(), attr);
        params.mAnimAlpha = Constants.Color.ALPHA_OPAQUE;

        if (!key.isSpacer()) {
            final Drawable background = key.selectBackgroundDrawable(mKey_Qwerty,mKey_Numeric,mKey_Space,mKey_Functional,mKey_Symbols,mKey_Alt);
            if (background != null) {
                onDrawKeyBackground(key, canvas, background);
            }
        }
        onDrawKeyTopVisuals(key, canvas, paint, params);

        canvas.translate(-keyDrawX, -keyDrawY);
    }

    // Draw key background.
    protected void onDrawKeyBackground(@Nonnull final Key key, @Nonnull final Canvas canvas,
            @Nonnull final Drawable background) {
        final int keyWidth = key.getDrawWidth();
        final int keyHeight = key.getHeight();
        final int bgWidth, bgHeight, bgX, bgY;

        Log.d("dimensiune_key","initial 1:"+keyWidth);

        if (key.needsToKeepBackgroundAspectRatio(mDefaultKeyLabelFlags)
                // HACK: To disable expanding normal/functional key background.
                && !key.hasCustomActionLabel()) {
            final int intrinsicWidth = background.getIntrinsicWidth();
            final int intrinsicHeight = background.getIntrinsicHeight();
            Log.d("dimensiune_key","initial 2:"+intrinsicWidth);
            final float minScale = Math.min(keyWidth / (float)intrinsicWidth, keyHeight / (float)intrinsicHeight);
            bgWidth = (int)(intrinsicWidth * minScale);
            bgHeight = (int)(intrinsicHeight * minScale);
            bgX = (keyWidth - bgWidth) / 2;
            bgY = (keyHeight - bgHeight) / 2;
        } else {
            final Rect padding = mKeyBackgroundPadding;
            bgWidth = keyWidth + padding.left + padding.right;
            bgHeight = keyHeight + padding.top + padding.bottom;
            bgX = -padding.left;
            bgY = -padding.top;
        }
        final Rect bounds = background.getBounds();
        if (bgWidth != bounds.right || bgHeight != bounds.bottom) {
            Log.d("dimensiune_key","final :"+keyWidth);
            background.setBounds(0, 0, bgWidth, bgHeight);
        }
        canvas.translate(bgX, bgY);
        background.draw(canvas);
        canvas.translate(-bgX, -bgY);
    }

    // Draw key top visuals.
    protected void onDrawKeyTopVisuals(@Nonnull final Key key, @Nonnull final Canvas canvas,
            @Nonnull final Paint paint, @Nonnull final KeyDrawParams params) {
        final int keyWidth = key.getDrawWidth();
        final int keyHeight = key.getHeight();
        final float centerX = keyWidth * 0.5f;
        final float centerY = keyHeight * 0.5f;

        // Draw key label.
        final Keyboard keyboard = getKeyboard();
        final Drawable icon; // = (keyboard == null) ? null: key.getIcon(keyboard.mIconsSet, params.mAnimAlpha);
        boolean alignIconToBottom = key.isAlignIconToBottom();

        Log.d("este_nul_sau"," da : "+mKeyboardTheme);

        if(key.getIconId() == KeyboardIconsSet.getIconId(KeyboardIconsSet.NAME_SHIFT_KEY)) {
            icon = mKeyboardTheme.shiftSymbol.def;
        } else if(key.getIconId() == KeyboardIconsSet.getIconId(KeyboardIconsSet.NAME_SHIFT_KEY_SHIFTED)) {
            icon = mKeyboardTheme.shiftSymbol.pressed;
        } else if(key.getIconId() == KeyboardIconsSet.getIconId(KeyboardIconsSet.NAME_SHIFT_KEY_LOCKED)) {
            icon = mKeyboardTheme.shiftSymbol.locked;
        } else if(key.getIconId() == KeyboardIconsSet.getIconId(KeyboardIconsSet.NAME_DELETE_KEY)) {
            icon = mKeyboardTheme.deleteSymbol;
        } else if(key.getIconId() == KeyboardIconsSet.getIconId(KeyboardIconsSet.NAME_SPACE_KEY) ||
                key.getIconId() == KeyboardIconsSet.getIconId(KeyboardIconsSet.NAME_SPACE_KEY_FOR_NUMBER_LAYOUT)) {
            icon = mKeyboardTheme.spaceSymbol;
        } else if(key.getIconId() == KeyboardIconsSet.getIconId(KeyboardIconsSet.NAME_DONE_KEY)) {
            icon = mKeyboardTheme.actionSymbol.done;
        } else if(key.getIconId() == KeyboardIconsSet.getIconId(KeyboardIconsSet.NAME_SEARCH_KEY)) {
            icon = mKeyboardTheme.actionSymbol.search;
        } else  if(key.getIconId() == KeyboardIconsSet.getIconId(KeyboardIconsSet.NAME_GO_KEY)) {
            icon = mKeyboardTheme.actionSymbol.go;
        } else if(key.getIconId() == KeyboardIconsSet.getIconId(KeyboardIconsSet.NAME_SEND_KEY)) {
            icon = mKeyboardTheme.actionSymbol.send;
        } else if(key.getIconId() == KeyboardIconsSet.getIconId(KeyboardIconsSet.NAME_NEXT_KEY)) {
            icon = mKeyboardTheme.actionSymbol.next;
        } else if(key.getIconId() == KeyboardIconsSet.getIconId(KeyboardIconsSet.NAME_ENTER_KEY)) {
            icon = mKeyboardTheme.actionSymbol.enter;
        } else if(key.getIconId() == KeyboardIconsSet.getIconId(KeyboardIconsSet.NAME_EMOJI_ACTION_KEY) && mKeyboardTheme.emojiSymbol != null) {
            icon = mKeyboardTheme.emojiSymbol;
        } else  {
/*
            if (key.getIconId() == KeyboardIconsSet.getIconId(KeyboardIconsSet.NAME_EMOJI_ACTION_KEY)) {
                alignIconToBottom = false;
                icon = TopBarUtils.applyFilterColorToIcon(key.getIcon(keyboard.mIconsSet, params.mAnimAlpha), mKeyboardTheme.keyLabel.normal.getDefaultColor());
            } else {*/
                icon = (keyboard == null) ? null : key.getIcon(keyboard.mIconsSet, params.mAnimAlpha);
           // }
        }





        float labelX = centerX;
        float labelBaseline = centerY;
        final String label = key.getLabel();
        if (label != null) {
            paint.setTypeface(key.selectTypeface(params));
            paint.setTextSize(key.selectTextSize(params));
            final float labelCharHeight = TypefaceUtils.getReferenceCharHeight(paint);
            final float labelCharWidth = TypefaceUtils.getReferenceCharWidth(paint);

            // Vertical label text alignment.
            labelBaseline = centerY + labelCharHeight / 2.0f;

            // Horizontal label text alignment
            if (key.isAlignLabelOffCenter()) {
                // The label is placed off center of the key. Used mainly on "phone number" layout.
                labelX = centerX + params.mLabelOffCenterRatio * labelCharWidth;
                paint.setTextAlign(Align.LEFT);
            } else {
                labelX = centerX;
                paint.setTextAlign(Align.CENTER);
            }
            if (key.needsAutoXScale()) {
                final float ratio = Math.min(1.0f, (keyWidth * MAX_LABEL_RATIO) /
                        TypefaceUtils.getStringWidth(label, paint));
                if (key.needsAutoScale()) {
                    final float autoSize = paint.getTextSize() * ratio;
                    paint.setTextSize(autoSize);
                } else {
                    paint.setTextScaleX(ratio);
                }
            }

            if (key.isEnabled()) {
                paint.setColor(key.selectTextColor(params));
                // Set a drop shadow for the text if the shadow radius is positive value.
                if (mKeyTextShadowRadius > 0.0f) {
                    paint.setShadowLayer(mKeyTextShadowRadius, 0.0f, 0.0f, params.mTextShadowColor);
                } else {
                    paint.clearShadowLayer();
                }
            } else {
                // Make label invisible
                paint.setColor(Color.TRANSPARENT);
                paint.clearShadowLayer();
            }
            blendAlpha(paint, params.mAnimAlpha);
            canvas.drawText(label, 0, label.length(), labelX, labelBaseline, paint);
            // Turn off drop shadow and reset x-scale.
            paint.clearShadowLayer();
            paint.setTextScaleX(1.0f);
        }

        // Draw hint label.
        final String hintLabel = key.getHintLabel();
        if (hintLabel != null) {
            paint.setTextSize(key.selectHintTextSize(params));
            paint.setColor(key.selectHintTextColor(params));
            // TODO: Should add a way to specify type face for hint letters
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            blendAlpha(paint, params.mAnimAlpha);
            final float labelCharHeight = TypefaceUtils.getReferenceCharHeight(paint);
            final float labelCharWidth = TypefaceUtils.getReferenceCharWidth(paint);
            final float hintX, hintBaseline;
            if (key.hasHintLabel()) {
                // The hint label is placed just right of the key label. Used mainly on
                // "phone number" layout.
                hintX = labelX + params.mHintLabelOffCenterRatio * labelCharWidth;
                if (key.isAlignHintLabelToBottom(mDefaultKeyLabelFlags)) {
                    hintBaseline = labelBaseline;
                } else {
                    hintBaseline = centerY + labelCharHeight / 2.0f;
                }
                paint.setTextAlign(Align.LEFT);
            } else if (key.hasShiftedLetterHint()) {
                // The hint label is placed at top-right corner of the key. Used mainly on tablet.
                hintX = keyWidth - mKeyShiftedLetterHintPadding - labelCharWidth / 2.0f;
                paint.getFontMetrics(mFontMetrics);
                hintBaseline = -mFontMetrics.top;
                paint.setTextAlign(Align.CENTER);
            } else { // key.hasHintLetter()
                // The hint letter is placed at top-right corner of the key. Used mainly on phone.
                final float hintDigitWidth = TypefaceUtils.getReferenceDigitWidth(paint);
                final float hintLabelWidth = TypefaceUtils.getStringWidth(hintLabel, paint);
                hintX = keyWidth - mKeyHintLetterPadding
                        - Math.max(hintDigitWidth, hintLabelWidth) / 2.0f;
                hintBaseline = -paint.ascent();
                paint.setTextAlign(Align.CENTER);
            }
            final float adjustmentY = params.mHintLabelVerticalAdjustment * labelCharHeight;
            canvas.drawText(
                    hintLabel, 0, hintLabel.length(), hintX, hintBaseline + adjustmentY, paint);
        }

        // Draw key icon.
        if (label == null && icon != null) {
            final int iconWidth;
            if (key.getCode() == Constants.CODE_SPACE && icon instanceof NinePatchDrawable) {
                iconWidth = (int)(keyWidth * mSpacebarIconWidthRatio);
            } else {
                iconWidth = Math.min(icon.getIntrinsicWidth(), keyWidth);
            }
            final int iconHeight = icon.getIntrinsicHeight();
            final int iconY;
            if (key.isAlignIconToBottom()) {
                iconY = keyHeight - iconHeight;
            } else {
                iconY = (keyHeight - iconHeight) / 2; // Align vertically center.
            }
            final int iconX = (keyWidth - iconWidth) / 2; // Align horizontally center.
            drawIcon(canvas, icon, iconX, iconY, iconWidth, iconHeight);
        }

        if (key.hasPopupHint() && key.getMoreKeys() != null) {
            drawKeyPopupHint(key, canvas, paint, params);
        }
    }

    // Draw popup hint "..." at the bottom right corner of the key.
    protected void drawKeyPopupHint(@Nonnull final Key key, @Nonnull final Canvas canvas,
            @Nonnull final Paint paint, @Nonnull final KeyDrawParams params) {
        if (TextUtils.isEmpty(mKeyPopupHintLetter)) {
            return;
        }
        final int keyWidth = key.getDrawWidth();
        final int keyHeight = key.getHeight();

        paint.setTypeface(params.mTypeface);
        paint.setTextSize(params.mHintLetterSize);
        paint.setColor(params.mHintLabelColor);
        paint.setTextAlign(Align.CENTER);
        final float hintX = keyWidth - mKeyHintLetterPadding
                - TypefaceUtils.getReferenceCharWidth(paint) / 2.0f;
        final float hintY = keyHeight - mKeyPopupHintLetterPadding;
        canvas.drawText(mKeyPopupHintLetter, hintX, hintY, paint);
    }

    protected static void drawIcon(@Nonnull final Canvas canvas,@Nonnull final Drawable icon,
            final int x, final int y, final int width, final int height) {
        canvas.translate(x, y);
        icon.setBounds(0, 0, width, height);
        icon.draw(canvas);
        canvas.translate(-x, -y);
    }

    public Paint newLabelPaint(@Nullable final Key key) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        if (key == null) {
            paint.setTypeface(mKeyDrawParams.mTypeface);
            paint.setTextSize(mKeyDrawParams.mLabelSize);
        } else {
            paint.setColor(key.selectTextColor(mKeyDrawParams));
            paint.setTypeface(key.selectTypeface(mKeyDrawParams));
            paint.setTextSize(key.selectTextSize(mKeyDrawParams));
        }
        return paint;
    }

    /**
     * Requests a redraw of the entire keyboard. Calling {@link #invalidate} is not sufficient
     * because the keyboard renders the keys to an off-screen buffer and an invalidate() only
     * draws the cached buffer.
     * @see #invalidateKey(Key)
     */
    public void invalidateAllKeys() {
        mInvalidatedKeys.clear();
        mInvalidateAllKeys = true;
        invalidate();
    }

    /**
     * Invalidates a key so that it will be redrawn on the next repaint. Use this method if only
     * one key is changing it's content. Any changes that affect the position or size of the key
     * may not be honored.
     * @param key key in the attached {@link Keyboard}.
     * @see #invalidateAllKeys
     */
    public void invalidateKey(@Nullable final Key key) {
        if (mInvalidateAllKeys || key == null) {
            return;
        }
        mInvalidatedKeys.add(key);
        final int x = key.getX() + getPaddingLeft();
        final int y = key.getY() + getPaddingTop();
        invalidate(x, y, x + key.getWidth(), y + key.getHeight());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        freeOffscreenBuffer();
    }

    public void deallocateMemory() {
        freeOffscreenBuffer();
    }
}

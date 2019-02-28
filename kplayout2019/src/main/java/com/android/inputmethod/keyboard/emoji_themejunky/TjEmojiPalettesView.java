package com.android.inputmethod.keyboard.emoji_themejunky;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.inputmethod.keyboard.emoji.EmojiLayoutParams;
import com.android.inputmethod.keyboard.emoji_themejunky.EmojiDex.Cars;
import com.android.inputmethod.keyboard.emoji_themejunky.EmojiDex.Electr;
import com.android.inputmethod.keyboard.emoji_themejunky.EmojiDex.Food;
import com.android.inputmethod.keyboard.emoji_themejunky.EmojiDex.Nature;
import com.android.inputmethod.keyboard.emoji_themejunky.EmojiDex.People;
import com.android.inputmethod.keyboard.emoji_themejunky.EmojiDex.Sport;
import com.android.inputmethod.keyboard.emoji_themejunky.EmojiDex.Symbols;
import com.keyboard_theme_dictionary.Model.theme_resource.ThemeResource;
import com.keyboard_theme_manager.default_themes.DefaultThemes;
import com.kplayout2019.R;

import java.util.Arrays;
import java.util.List;


public class TjEmojiPalettesView extends PopupWindow implements EmojiconRecents,ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager mEmojiPager;
    private LinearLayout mContainer,mBottomBar,mCategories;
    private RelativeLayout mButtonLeft,mButtonRight;
    private PagerAdapter mEmojisAdapter;
    private HorizontalScrollView mHorizontalScrollView;
    private ImageView mDone,mBackSpace;
    private EmojiCustomListener mEmojiCustomListener;
    private View rootView;
    private Context mContext;
    OnSoftKeyboardOpenCloseListener onSoftKeyboardOpenCloseListener;
    OnEmojiconBackspaceClickedListener onEmojiconBackspaceClickedListener;
    public EmojiconGridView.OnEmojiconClickedListener onEmojiconClickedListener;

    boolean mUseSystemDefault=false;


    public void setSize(int width, int height){
        setWidth(width);
        setHeight(height);
    }
    /**
     * Constructor
     * @param nRootView	The top most layout in your view hierarchy. The difference of this view and the screen height will be used to calculate the keyboard height.
     * @param nContext The context of current activity.
     * @param nUseSystemDefault .
     */
    public TjEmojiPalettesView(View nRootView, Context nContext,boolean nUseSystemDefault,EmojiCustomListener nEmojiCustomListener){
        super(nContext);

        mUseSystemDefault=nUseSystemDefault;
        mContext = nContext;
        rootView = nRootView;
        mEmojiCustomListener = nEmojiCustomListener;


        setContentView(createCustomView());
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        setSize(WindowManager.LayoutParams.MATCH_PARENT, 255);
        setBackgroundDrawable(null);
    }


    private View createCustomView() {

        EmojiLayoutParams mEmojiLayoutParams = new EmojiLayoutParams(mContext.getResources());
        EmojiconRecents recents = this;
        ThemeResource mTheme = DefaultThemes.getmInstance().getCurrentTheme();

        mEmojiPager = (ViewPager) rootView.findViewById(R.id.emojis_pager);
            mEmojiLayoutParams.setPagerProperties(mEmojiPager);

        mBottomBar = (LinearLayout) rootView.findViewById(R.id.mBottomBar);
            mEmojiLayoutParams.setBar(mBottomBar);

        mButtonLeft = (RelativeLayout) rootView.findViewById(R.id.mButtonLeft);
            mButtonLeft.setOnClickListener(this);

        mButtonRight = (RelativeLayout) rootView.findViewById(R.id.mButtonRight);
            mButtonRight.setOnClickListener(this);

        mHorizontalScrollView = (HorizontalScrollView) rootView.findViewById(R.id.mHorizontalScrollView) ;
            mHorizontalScrollView.setBackground(mTheme.keyPreview.background);

        mDone = (ImageView) rootView.findViewById(R.id.mDone);
            mDone.setBackground(mTheme.actionSymbol.done);
        mBackSpace = (ImageView) rootView.findViewById(R.id.mBackSpace);
            mBackSpace.setBackground(mTheme.deleteSymbol);


        mCategories = (LinearLayout) rootView.findViewById(R.id.mCategories);

        int mEmojiCategDim = (int)(mEmojiLayoutParams.getActionBarHeight()*0.65);
        for (int i = 0 ; i<mCategories.getChildCount(); i++) {
            final int finalI = i; final View mChild;

             mChild = mCategories.getChildAt(i);
             mChild.getLayoutParams().height = mEmojiCategDim;
             mChild.getLayoutParams().width = mEmojiCategDim;

             mChild.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetTabs();
                    mChild.setSelected(true);
                    mEmojiPager.setCurrentItem(finalI);
                }
             });
             mChild.requestLayout();
        }

        mContainer = (LinearLayout) rootView.findViewById(R.id.mContainer);
        mContainer.setBackground(mTheme.keyboardBackground);

        mEmojisAdapter = new EmojisPagerAdapter(
                Arrays.asList(
                        new EmojiconRecentsGridView(mContext, null, null, this,mUseSystemDefault),
                        new EmojiconGridView(mContext, People.DATA, recents, this, mUseSystemDefault),
                        new EmojiconGridView(mContext, Nature.DATA, recents, this,mUseSystemDefault),
                        new EmojiconGridView(mContext, Food.DATA, recents, this,mUseSystemDefault),
                        new EmojiconGridView(mContext, Sport.DATA, recents, this,mUseSystemDefault),
                        new EmojiconGridView(mContext, Cars.DATA, recents, this,mUseSystemDefault),
                        new EmojiconGridView(mContext, Electr.DATA, recents, this,mUseSystemDefault),
                        new EmojiconGridView(mContext, Symbols.DATA, recents, this,mUseSystemDefault)
                )
        );

        mEmojiPager.addOnPageChangeListener(this);
        mEmojiPager.setAdapter(mEmojisAdapter);
        mEmojiPager.setCurrentItem(1);
        return null;
    }

    private void resetTabs() {
        for (int i = 0 ; i<mCategories.getChildCount(); i++) {
            mCategories.getChildAt(i).setSelected(false);
        }
    }

    @Override
    public void addRecentEmoji(Context context, Emojicon emojicon) {
        EmojiconRecentsGridView fragment = ((EmojisPagerAdapter)mEmojiPager.getAdapter()).getRecentFragment();
        fragment.addRecentEmoji(context, emojicon);
    }

    @Override
    public void refreshRecentEmojis() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mButtonLeft) {
            mEmojiCustomListener.customEmoji_Hide();
        } else if (v.getId() == R.id.mButtonRight) {
            mEmojiCustomListener.customEmoji_BackSpace();
        }
    }


    public interface OnEmojiconBackspaceClickedListener {
        void onEmojiconBackspaceClicked(View v);
    }

    public interface OnSoftKeyboardOpenCloseListener{
        void onKeyboardOpen(int keyBoardHeight);
        void onKeyboardClose();
    }

    public void setOnEmojiconBackspaceClickedListener(OnEmojiconBackspaceClickedListener listener){
        this.onEmojiconBackspaceClickedListener = listener;
    }
    public void showAtBottom() {
       // showAtLocation(rootView, Gravity.TOP, 0,90);
    }

public void refreshEmojis(){
    Toast.makeText(mContext,"REFRESH",Toast.LENGTH_LONG).show();
}
    public void dismiss() {
        Toast.makeText(mContext,"DISMISS",Toast.LENGTH_LONG).show();
    }

    public  void updateUseSystemDefault(boolean mUseSystemDefault) {
        Toast.makeText(mContext,"updateUseSystemDefault",Toast.LENGTH_LONG).show();
    }
    public void setOnSoftKeyboardOpenCloseListener(OnSoftKeyboardOpenCloseListener listener){
        this.onSoftKeyboardOpenCloseListener = listener;
    }
    public void setOnEmojiconClickedListener(EmojiconGridView.OnEmojiconClickedListener listener){
        this.onEmojiconClickedListener = listener;
    }





    private static class EmojisPagerAdapter extends PagerAdapter {
        private List<EmojiconGridView> views;
        public EmojiconRecentsGridView getRecentFragment(){
            for (EmojiconGridView it : views) {
                if(it instanceof EmojiconRecentsGridView)
                    return (EmojiconRecentsGridView)it;
            }
            return null;
        }
        public EmojisPagerAdapter(List<EmojiconGridView> views) {
            super();
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = views.get(position).rootView;
            ((ViewPager)container).addView(v, 0);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object view) {
            ((ViewPager)container).removeView((View)view);
        }

        @Override
        public boolean isViewFromObject(View view, Object key) {
            return key == view;
        }
    }





    //ViewPage Listener
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        resetTabs();
        mCategories.getChildAt(position).setSelected(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}

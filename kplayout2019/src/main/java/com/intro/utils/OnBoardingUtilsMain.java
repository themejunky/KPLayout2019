package com.intro.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;

import com.android.inputmethod.latin.utils.LeakGuardHandlerWrapper;
import com.android.inputmethod.latin.utils.UncachedInputMethodManagerUtils;
import com.intro.IntroTutorial;
import com.keyboard_settings.screens.activity.Keyboard_Settings;
import com.keyboard_theme_manager.MainActivity;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * This class contains all necessary methods to check keyboard status / implement situation
 */

public class OnBoardingUtilsMain {
    private static final String TAG = "dasdasdfa";
    public static OnBoardingUtilsMain instance = null;
    private InputMethodManager mImmInHandler;
    private Activity mActivity;
    private SettingsPoolingHandler mHandler;

    public static final int ONBOARDING_STEP_1 = 1;
    public static final int ONBOARDING_STEP_2 = 2;
    public static final int ONBOARDING_STEP_3 = 3;

    public OnBoardingUtilsMain(Activity activity) {
        mActivity = activity;
        mImmInHandler = (InputMethodManager) mActivity.getSystemService(INPUT_METHOD_SERVICE);
        mHandler = new SettingsPoolingHandler((Keyboard_Settings) activity, mImmInHandler);
    }

    public static synchronized OnBoardingUtilsMain getInstance(Activity activity){
        if(instance==null){
            return new OnBoardingUtilsMain(activity);
        }else {
            return instance;
        }
    }



    private final class SettingsPoolingHandler extends LeakGuardHandlerWrapper<Keyboard_Settings> {
        private static final int MSG_POLLING_IME_SETTINGS = 0;
        private static final long IME_SETTINGS_POLLING_INTERVAL = 200;

        private final InputMethodManager mImmInHandler;

        SettingsPoolingHandler(final Keyboard_Settings ownerInstance, final InputMethodManager imm) {
            super(ownerInstance);
            mImmInHandler = imm;
        }

        @Override
        public void handleMessage(final Message msg) {
            final Keyboard_Settings setupWizardActivity = getOwnerInstance();
            if (setupWizardActivity == null) {
                return;
            }
            switch (msg.what) {
                case MSG_POLLING_IME_SETTINGS:
                    if (UncachedInputMethodManagerUtils.isThisImeEnabled(setupWizardActivity, mImmInHandler)) {
                        invokeSetupWizardOfThisIme();
                        return;
                    }
                    startPollingImeSettings();
                    break;
            }
        }

        void startPollingImeSettings() {
            sendMessageDelayed(obtainMessage(MSG_POLLING_IME_SETTINGS),IME_SETTINGS_POLLING_INTERVAL);
        }
    }

    /**
     * Check the current implementation of keyboard and/or contacts permission
     *
     * @return the next step for the user to fully implement the keyboard
     */
    public int determineSetupStepNumber() {
        Log.d("Awdaad","determineSetupStepNumber");
        if (!isThisImeEnabled(mActivity, mImmInHandler)) {
            return ONBOARDING_STEP_1;
        }
        if (!isThisImeCurrent(mActivity, mImmInHandler)) {
            return ONBOARDING_STEP_2;
        }else {
            Log.d("Awdaad","determineSetupStepNumber 3");
            return ONBOARDING_STEP_3;
        }

    }

    /**
     * Check if the IME specified by the context is enabled.
     * CAVEAT: This may cause a round trip IPC.
     *
     * @param context package context of the IME to be checked.
     * @param imm the {@link InputMethodManager}.
     * @return true if this IME is enabled.
     */
    private static boolean isThisImeEnabled(final Context context, final InputMethodManager imm) {
        final String packageName = context.getPackageName();
        for (final InputMethodInfo imi : imm.getEnabledInputMethodList()) {
            if (packageName.equals(imi.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the IME specified by the context is the current IME.
     * CAVEAT: This may cause a round trip IPC.
     *
     * @param context package context of the IME to be checked.
     * @param imm the {@link InputMethodManager}.
     * @return true if this IME is the current IME.
     */
    public boolean isThisImeCurrent(final Context context, final InputMethodManager imm) {
        final InputMethodInfo imi = getInputMethodInfoOf(context.getPackageName(), imm);
        final String currentImeId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);
        return imi != null && imi.getId().equals(currentImeId);
    }

    /**
     * Get {@link InputMethodInfo} of the IME specified by the package name.
     * CAVEAT: This may cause a round trip IPC.
     *
     * @param packageName package name of the IME.
     * @param imm the {@link InputMethodManager}.
     * @return the {@link InputMethodInfo} of the IME specified by the <code>packageName</code>,
     * or null if not found.
     */
    private InputMethodInfo getInputMethodInfoOf(final String packageName, final InputMethodManager imm) {
        for (final InputMethodInfo imi : imm.getInputMethodList()) {
            if (packageName.equals(imi.getPackageName())) {
                return imi;
            }
        }
        return null;
    }

    /**
     * Restart the activity after the first step if user interaction appears
     */
    public void invokeSetupWizardOfThisIme() {
        final Intent intent = new Intent();
        intent.setClass(mActivity, IntroTutorial.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                | Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mActivity.startActivity(intent);
    }

    /**
     * Invoke input method picker as popUp interaction for user (Step 2)
     */
    public void invokeInputMethodPicker() {
        mImmInHandler.showInputMethodPicker();
    }

    /**
     * Invoke setting window for imput methos activation (Step 1)
     */
    public void invokeLanguageAndInputSettings() {
        mHandler.startPollingImeSettings();
        final Intent intent = new Intent();
        intent.setAction(Settings.ACTION_INPUT_METHOD_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        mActivity.startActivity(intent);
    }

}

package com.keyboard_settings.screens.activity.Sync.SyncGmail;

import android.Manifest;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.gmail.GmailScopes;
import com.keyboard_settings.utils.SettingsConstants;

import java.util.Arrays;
import java.util.List;

import com.kplayout2019.MainApplication;
import com.kplayout2019.R;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class GmailSyncActivity extends Activity implements EasyPermissions.PermissionCallbacks, View.OnClickListener {
    private GoogleAccountCredential mCredential;
    private Button mStartButton;

    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;

    private static final String[] SCOPES = {GmailScopes.GMAIL_LABELS, GmailScopes.GMAIL_READONLY};

    /**
     * Create the main activity.
     *
     * @param savedInstanceState previously saved instance data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tj_gmail);

        mStartButton = (Button) findViewById(R.id.start);
        mStartButton.setOnClickListener(this);

        mCredential = GoogleAccountCredential.usingOAuth2(getApplicationContext(), Arrays.asList(SCOPES)).setBackOff(new ExponentialBackOff());
    }

    /**
     * Attempt to call the API, after verifying that all the preconditions are
     * satisfied. The preconditions are: Google Play Services installed, an
     * account was selected and the device currently has online access. If any
     * of the preconditions are not satisfied, the app will prompt the user as
     * appropriate.
     */
    private void getResultsFromApi() {
        Log.d("telefon_alin","1");
        if (!isGooglePlayServicesAvailable()) {
            Log.d("telefon_alin","2");
            acquireGooglePlayServices();
        } else if (!isDeviceOnline()) {
            Log.d("telefon_alin","3");
            Snackbar.make(mStartButton, R.string.keyboard_settings_no_internet, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else {
            Log.d("telefon_alin","4");
            chooseAccount();
        }
    }

    /**
     * Attempts to set the account used with the API credentials. If an account
     * name was previously saved it will use that one; otherwise an account
     * picker dialog will be shown to the user. Note that the setting the
     * account to use with the credentials object requires the app to have the
     * GET_ACCOUNTS permission, which is requested here if it is not already
     * present. The AfterPermissionGranted annotation indicates that this
     * function will be rerun automatically whenever the GET_ACCOUNTS permission
     * is granted.
     */
    @AfterPermissionGranted(REQUEST_PERMISSION_GET_ACCOUNTS)
    private void chooseAccount() {
        Log.d("telefon_alin","5");
        if (EasyPermissions.hasPermissions(this, Manifest.permission.GET_ACCOUNTS)) {
            Log.d("telefon_alin","6");
            startActivityForResult(mCredential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
        } else {
            Log.d("telefon_alin","7");
            EasyPermissions.requestPermissions(this,getResources().getString(R.string.keyboard_settings_gmail_permision),REQUEST_PERMISSION_GET_ACCOUNTS,Manifest.permission.GET_ACCOUNTS);
        }
    }

    /**
     * Called when an activity launched here (specifically, AccountPicker
     * and authorization) exits, giving you the requestCode you started it with,
     * the resultCode it returned, and any additional data from it.
     *
     * @param requestCode code indicating which activity result is incoming.
     * @param resultCode  code indicating the result of the incoming
     *                    activity result.
     * @param data        Intent (containing result data) returned by incoming
     *                    activity result.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:

                if (resultCode != RESULT_OK) {
                    Snackbar.make(mStartButton, R.string.keyboard_settings_google_play, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    getResultsFromApi();
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && data != null && data.getExtras() != null) {
                    final String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {

                        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(SettingsConstants.GMAIL_ACCOUNT_NAME, accountName);
                        editor.apply();
                        mCredential.setSelectedAccountName(accountName);
                        if (mCredential.getSelectedAccountName() == null) {
                            getResultsFromApi();
                        } else {
                            Log.d("service_one","lkk3");
                            ((MainApplication) getApplication()).mGoogleCredential = mCredential;
                            Log.d("service_one","p3");
                           startService(new Intent(this,GmailSyncService.class));
                           /*
                              HttpTransport transport = AndroidHttp.newCompatibleTransport();
                            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
                            final Gmail mGmailService = new Gmail.Builder(transport, jsonFactory, ((ThemeJunkyApplication) getApplication()).mGoogleCredential).setApplicationName("GmailSyncActivity API Android Quickstart2").build();

                          new Thread(new Runnable() {
                                @Override
                                public void run() {



try {
    Label infoLabel = mGmailService.users().labels().get(accountName, "SENT").execute();
   // maxEmails = Integer.parseInt(infoLabel.get("messagesTotal").toString());
   // publishProgress(maxEmails, currentEmail);
   // Gmail.Users.Messages.List myQuery = mGmailService.users().messages().list(mGmailAccountName).setLabelIds(mGmailLabel2).setMaxResults(MaxMessages);
    Log.d("telefon_alin","bbbb!! "+infoLabel.get("messagesTotal").toString() );

    Gmail.Users.Messages.List myQuery =  mGmailService.users().messages().list(accountName).setLabelIds(Collections.singletonList("SENT")).setMaxResults(500L);
    Log.d("telefon_alin","bbbb!!? "+myQuery.execute().getMessages().size() );

}
catch (Exception e) {
    Log.d("telefon_alin","bbbb!! "+e.getMessage() );
    Log.d("telefon_alin","bbbb!! "+e.getCause() );
}  }
                            }).start();

*/
 onBackPressed();
                        }
                    }
                }
                break;
            case REQUEST_AUTHORIZATION:
                Log.d("telefon_alin","c");
                if (resultCode == RESULT_OK) {
                    getResultsFromApi();
                }
                break;
        }
    }

    /**
     * Respond to requests for permissions at runtime for API 23 and above.
     *
     * @param requestCode  The request code passed in
     *                     requestPermissions(android.app.Activity, String, int, String[])
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either PERMISSION_GRANTED or PERMISSION_DENIED. Never null.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      //  super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      //  EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * Callback for when a permission is granted using the EasyPermissions
     * library.
     *
     * @param requestCode The request code associated with the requested
     *                    permission
     * @param list        The requested permission list. Never null.
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Do nothing.
    }

    /**
     * Callback for when a permission is denied using the EasyPermissions
     * library.
     *
     * @param requestCode The request code associated with the requested
     *                    permission
     * @param list        The requested permission list. Never null.
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Do nothing.
    }

    /**
     * Checks whether the device currently has a network connection.
     *
     * @return true if the device has a network connection, false otherwise.
     */
    private boolean isDeviceOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Check that Google Play services APK is installed and up to date.
     *
     * @return true if Google Play Services is available and up to
     * date on this device; false otherwise.
     */
    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        final int connectionStatusCode = apiAvailability.isGooglePlayServicesAvailable(this);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }

    /**
     * Attempt to resolve a missing, out-of-date, invalid or disabled Google
     * Play Services installation via a user dialog, if possible.
     */
    private void acquireGooglePlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        final int connectionStatusCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
        }
    }


    /**
     * Display an error dialog showing that Google Play Services is missing
     * or out of date.
     *
     * @param connectionStatusCode code describing the presence (or lack of)
     *                             Google Play Services on this device.
     */
    void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        Dialog dialog = apiAvailability.getErrorDialog(GmailSyncActivity.this,connectionStatusCode,REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            getResultsFromApi();
        }
    }
}
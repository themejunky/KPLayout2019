package com.keyboard_core.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import com.kplayout2019.R;


public class CommonFunc {

    public Intent sendMail(Context nContext) {
        PackageInfo pInfo = null;
        try {
            pInfo = nContext.getPackageManager().getPackageInfo(nContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo.versionName;
        String uriText =
                "mailto:support@keyboard-plus.com" +
                        "?subject=" + "["+nContext.getResources().getString(R.string.app_name)+" ] Help" +
                        "&body= "+nContext.getResources().getString(R.string.app_name)+" "+ version + "\n" +
                        "Android: " + Build.VERSION.RELEASE + "\n" +
                        "Device: " + this.getDeviceName() + "\n\n" +
                        "Please describe your issue" + "\n";

        Uri uri = Uri.parse(uriText);

        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(uri);
        return (Intent.createChooser(sendIntent, "Send email"));
    }

    private String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}

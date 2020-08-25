package com.uae.emiratescar.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.uae.emiratescar.R;

public class Utils {

    private static final String TAG = "UTILS";

    public static boolean isNetworkAvailable(Context mContext) {
        if (mContext == null) return false;

        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                final Network network = connectivityManager.getActiveNetwork();
                if (network != null) {
                    final NetworkCapabilities nc = connectivityManager.getNetworkCapabilities(network);

                    return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                }
            } else {
                NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
                for (NetworkInfo tempNetworkInfo : networkInfos) {
                    if (tempNetworkInfo.isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void requestFullScreen(Activity mActivity) {

        if (Build.VERSION.SDK_INT > 16) {
            View decorView = mActivity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public static String getAppVersion(Context ctx) {
        String versionName = "0.0.0";
        try {
            PackageInfo packageInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            versionName = packageInfo.versionName.trim();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static void goToPlayStory(String appPackageName, Activity ctx) {
        try {
            ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                    .parse("market://details?id=" + appPackageName)));
            ctx.finish();
        } catch (android.content.ActivityNotFoundException anfe) {
            ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                    .parse("https://play.google.com/store/apps/details?id="
                            + appPackageName)));
            ctx.finish();
        }

    }

    public static ProgressDialog showCustomGifProgressDialog(boolean isCancelable,ProgressDialog progressDialog) {

        try {
            progressDialog.setCancelable(isCancelable);
            progressDialog.setCanceledOnTouchOutside(isCancelable);
            progressDialog.show();
            Window window = progressDialog.getWindow();
            if (window != null) {
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                progressDialog.setContentView(R.layout.progress_dialog_custom_layout);

                View rootView = progressDialog.getWindow().getDecorView();
                GifImageView ivProgressLoader = (GifImageView) rootView.findViewById(R.id.iv_progress_loader);
                if (Build.VERSION.SDK_INT >= 11) {
                    ivProgressLoader.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                }
                ivProgressLoader.setGifImageResource(R.drawable.loading1);
            }
        } catch (OutOfMemoryError e) {
            //e.printStackTrace();
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return progressDialog;
    }


}

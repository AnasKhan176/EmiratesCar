package com.uae.emiratescar.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;
import com.uae.emiratescar.R;
import androidx.core.app.ActivityCompat;

import static android.content.Context.MODE_PRIVATE;

public class PermissionUtils {
    private static boolean isDialogShowing = true;
    private static SharedPreferences permissionStatus;
    private static String[] listOfPermission;

    /**
     * this method check permission int three stage
     * 1) if app is install first time and then ask for required permission
     *    to run the app and in that case return false.
     * 2) if first time someone deny the any one of the permission or all the permission
     *    and in that case return false.
     * 3) if app permission deny and clicked on checkbox
     *   (the getErrorMessage mention on that "don't ask again") and in that case return false.
     * 4) if all the permission is granted then return true.
     * @param context point to current activity
     * @param permissionList list of permission
     * @param PERMISSION_CONSTANT specific number constant to permission
     * @return if all permission is granted then return true else false
     */

    public static boolean permissionGranted(Context context, String[] permissionList, int PERMISSION_CONSTANT) {
        if (context == null || permissionList == null || permissionList.length == 0) return false;
        permissionStatus = context.getSharedPreferences("permissionStatus", MODE_PRIVATE);
        boolean isPermissionNotGranted = false;
        listOfPermission = permissionList;
        for (String permission : permissionList) {
            // if any one of the listed permission is deny then isPermissionTrue is true
            // and we break the loop else continue to check all the permission.
            boolean isTrue = ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED;
            SharedPreferences.Editor editor = permissionStatus.edit();
            if (isTrue) {
                isPermissionNotGranted = true;
                editor.putBoolean(permission, false);
            } else {
                isPermissionNotGranted = false;
                editor.putBoolean(permission, true);
            }
            editor.apply();
        }

        if (isPermissionNotGranted) {
            boolean isPermissionGrant = isShouldShowRequestPermissionRationale(context, permissionList);
            if (isPermissionGrant) {
                //Show Information about why you need the permission
                if (isDialogShowing)
                    isDialogShowing = false;
                ActivityCompat.requestPermissions((Activity) context, permissionList, PERMISSION_CONSTANT);
            } else {
                ActivityCompat.requestPermissions((Activity) context, permissionList, PERMISSION_CONSTANT);
            }
            return false;
        } else {
            //You already have the permission, just go ahead.
            proceedAfterPermission(context, false);
            return true;
        }
    }

    public static boolean isGranted(Context context, String[] permissions) {
        if (context == null || permissions == null || permissions.length == 0) {
            return false;
        }

        boolean isPermissionGranted = true;
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                isPermissionGranted = false;
            }
        }
        return isPermissionGranted;
    }

    /**
     * this method is used to open setting where we will give permission to app manually
     * that necessary for the app. this method is called when someone deny the permission
     * and clicked on the checked (in which mention don't ask again)
     * @param context point to current context
     */
    public static void openSettings(final Context context, String[] permissions, final int PERMISSION_CONSTANT) {
        if (context == null || permissions == null || permissions.length == 0) return;
        // Previously Permission Request was cancelled with Don't Ask Again',
        // Redirect to Settings after showing Information about why you need the permission

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.app_name);
        builder.setMessage(permissionMessage(context, permissions));
        builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toast(context, "Go to Setting to grant permission");
                dialog.dismiss();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                ((Activity) context).startActivityForResult(intent, PERMISSION_CONSTANT);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    /**
     * this method is use when someone first time deny the permission
     * and this is called to check permission is granted or not
     *
     * @param context     point to current activity
     * @param permissions list of permission
     * @return if all permission is not granted then return true else false
     */

    public static boolean isShouldShowRequestPermissionRationale(Context context, String[] permissions) {
        if (context == null || permissions == null || permissions.length == 0) return false;
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * this method check current android version of tab
     * if current android version is Marshmallow or above this
     * then its return true else false
     */
    public static boolean isOSVersionMorHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * this method is called after when all permission is granted to show the getErrorMessage
     * @param context point current activity
     * @param isTrue
     */
    public static void proceedAfterPermission(Context context, boolean isTrue) {
        if (context == null) return;
        if (isTrue) {
            //We've got the permission, now we can proceed further
            toast(context, "We got the required Permissions");
        }
    }

    /**
     * show the current getErrorMessage that we want to display
     * @param context point to current Activity
     * @param msg point to current getErrorMessage
     */
    public static void toast (Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    private static String permissionMessage(Context context, String[] permissionList) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("This app need these permissions.\n\n");

        for (String permission : permissionList) {
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                switch (permission) {
                    case Manifest.permission.ACCESS_COARSE_LOCATION: break;
                    case Manifest.permission.ACCESS_FINE_LOCATION:
                        i++;
                        sb.append(String.valueOf(i)).append(") ").append("Location.\n");
                        break;

                    case Manifest.permission.READ_EXTERNAL_STORAGE: break;
                    case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                        i++;
                        sb.append(String.valueOf(i)).append(") ").append("Storage.\n");
                        break;

                    case Manifest.permission.CAMERA:
                        i++;
                        sb.append(String.valueOf(i)).append(") ").append("Camera.\n");
                        break;

                    case Manifest.permission.READ_PHONE_STATE:
                        i++;
                        sb.append(String.valueOf(i)).append(") ").append("Read Phone State.\n");
                        break;
                }
            }
        }
        return sb.toString();
    }
}

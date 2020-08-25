package com.uae.emiratescar.ui.activities;
//https://www.iconfinder.com/search/?q=call&price=free

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.uae.emiratescar.R;
import com.uae.emiratescar.utils.Utils;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    ProgressDialog progressDialog=null;

    public void removeStatusBar() {
        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setActivityTitle(String title) {
        try {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(title);
            actionBar.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showBackButton(boolean isBackButton) {
        try {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(isBackButton);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void toastShort(Activity ctx, String message) {
        Toast toast = null;
        if (!ctx.isFinishing()) {
            if (toast != null) {
                toast.cancel();
            }
            if (message != null && message.length() > 0) {
                toast = Toast.makeText(ctx, message, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void toastLong(Activity ctx, String message) {
        Toast toast = null;
        if (!ctx.isFinishing()) {
            if (toast != null) {
                toast.cancel();
            }
            if (message != null && message.length() > 0) {
                toast = Toast.makeText(ctx, message, Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }


   /* public void showProgressDialog(boolean isCancelable) {
        try {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.please_wait));
            progressDialog.setCancelable(isCancelable);
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }

        } catch (Exception ex) {
        }
    }

    public void dismissProgressDialog() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }

        } catch (Exception ex) {
        }
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void intermediateProgress(boolean isShowProgress) {
       // ProgressDialog pd = null;
        if (isShowProgress) {
            try {
                progressDialog = new ProgressDialog(this);
                if(!progressDialog.isShowing())
                Utils.showCustomGifProgressDialog(false,progressDialog);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        } else {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }


}

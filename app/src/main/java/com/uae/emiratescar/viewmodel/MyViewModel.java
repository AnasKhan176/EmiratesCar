package com.uae.emiratescar.viewmodel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;

import com.uae.emiratescar.R;
import com.uae.emiratescar.network.APIService;
import com.uae.emiratescar.network.request.VersionRequest;
import com.uae.emiratescar.network.response.BaseResponse;
import com.uae.emiratescar.ui.activities.AboutContactUsActivity;
import com.uae.emiratescar.ui.activities.DashboardActivity;
import com.uae.emiratescar.ui.activities.ProductActivity;
import com.uae.emiratescar.ui.activities.SignInActivity;
import com.uae.emiratescar.ui.activities.SplashActivity;
import com.uae.emiratescar.utils.ApiUtils;
import com.uae.emiratescar.utils.Utils;

import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyViewModel extends ViewModel {

    private Context context;

    public MyViewModel(Context context)
    {
        this.context = context;
    }

    private void sendAppVersionRequest(final String appVersion) {

                if(Utils.isNetworkAvailable(context))
                {
                    ((SplashActivity) context).intermediateProgress(true);
                    APIService mAPIService = ApiUtils.getAPIService();
                    VersionRequest request = new VersionRequest();
                    request.setVersion(appVersion);
                    request.setAppType("Android");

                    mAPIService.versionCall(request).enqueue(new Callback<BaseResponse>() {
                        @Override
                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                            try {
                                ((SplashActivity) context).intermediateProgress(false);
                                if (response.isSuccessful()) {
                                    if (response.body().getApiStatus() == true) {
                                        if(response.body().getResults()!=null && response.body().getResults().toString().length()>0)
                                        {
                                            if (response.body().getResults().getAppUpdateStatus() == 1) {
                                                Intent intent = new Intent(context,DashboardActivity.class);
                                                context.startActivity(intent);
                                                ((Activity) context).finish();
                                            } else {
                                                showUpdateAppDialog();
                                            }
                                        }
                                        else
                                        {
                                            ((SplashActivity) context).toastShort((SplashActivity)context, context.getString(R.string.no_data));
                                            ((Activity) context).finish();
                                        }

                                    } else {
                                        ((SplashActivity) context).toastShort((SplashActivity) context, response.body().getMessage());
                                        ((Activity) context).finish();
                                    }
                                }
                            } catch (Exception ex) {
                                ((SplashActivity) context).toastShort((SplashActivity) context, context.getString(R.string.internal_err));
                                ((Activity) context).finish();
                            }

                        }

                        @Override
                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                            ((SplashActivity) context).intermediateProgress(false);
                            ((SplashActivity) context).toastShort((SplashActivity) context, context.getString(R.string.network_err));
                            ((Activity) context).finish();
                        }
                    });

                }
                else
                {
                    ((SplashActivity) context).toastShort((SplashActivity) context, context.getString(R.string.no_internet));
                    ((Activity) context).finish();
                }


    }

    void showUpdateAppDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.update_msg);
        builder.setCancelable(false);

        builder.setPositiveButton(R.string.download_now, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Utils.goToPlayStory(
                        context.getPackageName(),
                        (Activity) context);

            }
        });
       // builder.setNegativeButton(R.string.cancel, null).show();
        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((Activity) context).finish();
            }
        });

        builder.show();
    }

    public void showHandler()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((SplashActivity) context).intermediateProgress(true);
                sendAppVersionRequest(Utils.getAppVersion(context));
            }
        },100);
    }

}
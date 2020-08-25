package com.uae.emiratescar.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.uae.emiratescar.R;
import com.uae.emiratescar.network.APIService;
import com.uae.emiratescar.network.request.SigninRequest;
import com.uae.emiratescar.network.request.SignupRequest;
import com.uae.emiratescar.network.response.BaseResponse;
import com.uae.emiratescar.ui.activities.ForgotPassActivity;
import com.uae.emiratescar.ui.activities.ProductActivity;
import com.uae.emiratescar.ui.activities.SignInActivity;
import com.uae.emiratescar.ui.activities.SignUpActivity;
import com.uae.emiratescar.ui.activities.SplashActivity;
import com.uae.emiratescar.ui.model.User;
import com.uae.emiratescar.utils.ApiUtils;
import com.uae.emiratescar.utils.Utils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> fullName = new MutableLiveData<>();
    public MutableLiveData<String> mobileno = new MutableLiveData<>();
    public MutableLiveData<String> confirmpassword = new MutableLiveData<>();


    private MutableLiveData<Integer> instance;
    private User user;
    private Context context;

    /**
     * Constructor for UserViewModel
     *
     * @param context
     * @param user
     */
    public UserViewModel(Context context, User user) {
        this.user = user;
        this.context = context;
    }


    /**
     * Get Mutable Data instance for progress bar
     *
     * @return
     */
    public MutableLiveData<Integer> getInstance() {

        if (instance == null) {
            instance = new MutableLiveData<>();
            instance.setValue(View.GONE);
        }
        return instance;
    }


    /**
     * Event generated for login button
     *
     * @return
     */
    public void onLoginClick() {
        user.setEmail(email.getValue());
        user.setPassword(password.getValue());

        if (!user.isValidEmail()) {
            ((SignInActivity) context).toastShort((SignInActivity) context, context.getString(R.string.email_err));
        } else if (!user.isValidPassword()) {
            ((SignInActivity) context).toastShort((SignInActivity) context, context.getString(R.string.pass_err));
        } else {
            getInstance().setValue(View.VISIBLE);
            if (Utils.isNetworkAvailable(context)) {
                ((SignInActivity) context).intermediateProgress(true);

                APIService mAPIService = ApiUtils.getAPIService();
                SigninRequest request = new SigninRequest();
                request.setEmail(email.getValue());
                request.setPassword(password.getValue());

                mAPIService.loginCall(request).enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        try {
                            ((SignInActivity) context).intermediateProgress(false);
                            if (response.isSuccessful()) {
                                if (response.body().getApiStatus() == true) {
                                    if (response.body().getResults() != null && response.body().getResults().toString().length() > 0) {
                                        ((SignInActivity) context).toastShort((SignInActivity) context, response.body().getResults().getApiMessage());
                                    } else {
                                        ((SignInActivity) context).toastShort((SignInActivity) context, context.getString(R.string.no_data));
                                    }

                                } else {
                                    ((SignInActivity) context).toastShort((SignInActivity) context, response.body().getMessage());
                                }
                            }
                        } catch (Exception ex) {
                            ((SignInActivity) context).toastShort((SignInActivity) context, context.getString(R.string.internal_err));
                        }

                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        ((SignInActivity) context).toastShort((SignInActivity) context, context.getString(R.string.network_err));
                        ((SignInActivity) context).intermediateProgress(false);

                    }
                });

            } else {
                ((SignInActivity) context).toastShort((SignInActivity) context, context.getString(R.string.no_internet));

            }
        }

    }

    public void onForgotClick() {

        Intent intent = new Intent(context, ForgotPassActivity.class);
        context.startActivity(intent);
    }

    public void onSignUpClick() {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    public void onRegisterClick() {
        user.setEmail(email.getValue());
        user.setPassword(password.getValue());
        user.setConfirmpassword(confirmpassword.getValue());
        user.setMobileno(mobileno.getValue());
        user.setFullName(fullName.getValue());

        if (!user.isValidFullName()) {
            ((SignUpActivity) context).toastShort((SignUpActivity) context, context.getString(R.string.fullname_err));
        } else if (!user.isValidEmail()) {
            ((SignUpActivity) context).toastShort((SignUpActivity) context, context.getString(R.string.email_err));
        } else if (!user.isValidMobile()) {
            ((SignUpActivity) context).toastShort((SignUpActivity) context, context.getString(R.string.mobile_err));
        } else if (!user.isValidPassword()) {
            ((SignUpActivity) context).toastShort((SignUpActivity) context, context.getString(R.string.pass_err));
        } else if (!user.isValidConfirmPassword()) {
            ((SignUpActivity) context).toastShort((SignUpActivity) context, context.getString(R.string.cpass_err));
        } else if (!user.isConfirmPassword()) {
            ((SignUpActivity) context).toastShort((SignUpActivity) context, context.getString(R.string.compare_pass_err));
        } else {
            getInstance().setValue(View.VISIBLE);
            if (Utils.isNetworkAvailable(context)) {
                ((SignUpActivity) context).intermediateProgress(true);
                APIService mAPIService = ApiUtils.getAPIService();
                SignupRequest request = new SignupRequest();
                request.setEmail(email.getValue());
                request.setPassword(password.getValue());
                request.setMobile(mobileno.getValue());
                request.setFullName(fullName.getValue());

                mAPIService.signUpCall(request).enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        try {
                            ((SignUpActivity) context).intermediateProgress(false);
                            if (response.isSuccessful()) {
                                if (response.body().getApiStatus() == true) {
                                    if (response.body().getResults() != null && response.body().getResults().toString().length() > 0) {
                                        ((SignUpActivity) context).toastShort((SignUpActivity) context, response.body().getResults().getApiMessage());
                                        Intent intent = new Intent(context, SignInActivity.class);
                                        context.startActivity(intent);

                                    } else {
                                        ((SignUpActivity) context).toastShort((SignUpActivity) context, context.getString(R.string.no_data));
                                    }

                                } else {
                                    ((SignUpActivity) context).toastShort((SignUpActivity) context, response.body().getMessage());
                                }
                            }
                        } catch (Exception ex) {
                            ((SignUpActivity) context).toastShort((SignUpActivity) context, context.getString(R.string.internal_err));
                        }

                        ((Activity) context).finish();

                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        ((SignUpActivity) context).toastShort((SignUpActivity) context, context.getString(R.string.network_err));
                        ((SignUpActivity) context).intermediateProgress(false);

                    }
                });

            } else {
                ((SignUpActivity) context).toastShort((SignUpActivity) context, context.getString(R.string.no_internet));

            }
        }


    }
}
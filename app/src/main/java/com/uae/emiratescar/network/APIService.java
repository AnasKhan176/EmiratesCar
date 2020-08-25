package com.uae.emiratescar.network;

import com.uae.emiratescar.network.request.PrdTypeRequest;
import com.uae.emiratescar.network.request.SigninRequest;
import com.uae.emiratescar.network.request.SignupRequest;
import com.uae.emiratescar.network.request.VersionRequest;
import com.uae.emiratescar.network.response.BaseResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @POST("versions/" + APITags.version)
    Call<BaseResponse> versionCall(@Body VersionRequest request);

    @POST(APITags.signin)
    Call<BaseResponse> loginCall(@Body SigninRequest request);

    @POST("user/" + APITags.signup)
    Call<BaseResponse> signUpCall(@Body SignupRequest request);

    @POST("ProductDetails/"+APITags.prdTypes)
    Call<BaseResponse> doGetProductList(@Body PrdTypeRequest request);

}

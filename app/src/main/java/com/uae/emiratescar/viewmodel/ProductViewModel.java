package com.uae.emiratescar.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.uae.emiratescar.R;
import com.uae.emiratescar.network.APIService;
import com.uae.emiratescar.network.request.PrdTypeRequest;
import com.uae.emiratescar.network.response.BaseResponse;
import com.uae.emiratescar.ui.activities.ProductActivity;
import com.uae.emiratescar.ui.model.ProductList;
import com.uae.emiratescar.utils.ApiUtils;
import com.uae.emiratescar.utils.Utils;

import java.util.ArrayList;

public class ProductViewModel extends ViewModel {

    MutableLiveData<ArrayList<ProductList>> prdLiveData;
    ArrayList<ProductList> prdArrayList;
    Context context;

    public ProductViewModel(Context context) {
        this.context = context;
    }

    public MutableLiveData<ArrayList<ProductList>> getProductsMutableLiveData(int type) {

        if (prdLiveData == null) {
            prdLiveData = new MutableLiveData<>();
            getProductList(type);
        }
        return prdLiveData;
    }

    public void getProductList(int type) {

        if (Utils.isNetworkAvailable(context)) {
            ((ProductActivity) context).intermediateProgress(true);

            APIService mAPIService = ApiUtils.getAPIService();

            PrdTypeRequest request=new PrdTypeRequest();
            request.setPrdId(type);
            mAPIService.doGetProductList(request).enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    try {
                        ((ProductActivity) context).intermediateProgress(false);
                        if (response.isSuccessful()) {
                            if (response.body().getApiStatus() == true) {
                                if (response.body().getResults() != null && response.body().getResults().toString().length() > 0) {
                                    prdArrayList = new ArrayList<>();

                                    if (response.body().getResults().getData() != null &&
                                            !response.body().getResults().getData().isEmpty() &&
                                            response.body().getResults().getData().size() > 0) {
                                        prdArrayList = (ArrayList<ProductList>) response.body().getResults().getData();
                                        prdLiveData.setValue(prdArrayList);

                                    }
                                } else {
                                    ((ProductActivity) context).toastShort((ProductActivity) context, context.getString(R.string.no_data));
                                }

                            } else {
                                ((ProductActivity) context).toastShort((ProductActivity) context, response.body().getMessage());
                            }
                        }
                    } catch (Exception ex) {
                        ((ProductActivity) context).toastShort((ProductActivity) context, context.getString(R.string.internal_err));
                    }

                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    ((ProductActivity) context).toastShort((ProductActivity) context, context.getString(R.string.network_err));
                    ((ProductActivity) context).intermediateProgress(false);
                }
            });

        } else {
            ((ProductActivity) context).toastShort((ProductActivity) context, context.getString(R.string.no_internet));

        }

    }
}

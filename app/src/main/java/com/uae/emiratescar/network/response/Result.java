package com.uae.emiratescar.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uae.emiratescar.ui.model.ProductList;

import java.util.List;

public class Result {
    @SerializedName("values")
    @Expose
    private int appUpdateStatus;

    @SerializedName("apiMessage")
    @Expose
    private String apiMessage;


    public String getApiMessage() {
        return apiMessage;
    }

    public void setApiMessage(String apiMessage) {
        this.apiMessage = apiMessage;
    }


    public int getAppUpdateStatus() {
        return appUpdateStatus;
    }

    public void setAppUpdateStatus(int appUpdateStatus) {
        this.appUpdateStatus = appUpdateStatus;
    }

    @SerializedName("appCarDetails")
    @Expose
    private List<ProductList> data = null;

    public List<ProductList> getData() {
        return data;
    }

    public void setData(List<ProductList> data) {
        this.data = data;
    }
}

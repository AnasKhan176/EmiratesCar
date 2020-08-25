package com.uae.emiratescar.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrdTypeRequest {

    @SerializedName("ProductTypeId")
    @Expose
    private int prdId;

    public int getPrdId() {
        return prdId;
    }

    public void setPrdId(int prdId) {
        this.prdId = prdId;
    }
}

package com.uae.emiratescar.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("status")
    @Expose
    private boolean apiStatus;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("results")
    @Expose
    private Result results;

    public boolean getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(boolean apiStatus) {
        this.apiStatus = apiStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResults() {
        return results;
    }
    public void setResults(Result results) {
        this.results = results;
    }

}

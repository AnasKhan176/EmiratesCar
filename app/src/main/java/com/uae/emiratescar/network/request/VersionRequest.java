package com.uae.emiratescar.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionRequest {

    @SerializedName("AppVersion")
    @Expose
    private String version;

    @SerializedName("AppType")
    @Expose
    private String appType;

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

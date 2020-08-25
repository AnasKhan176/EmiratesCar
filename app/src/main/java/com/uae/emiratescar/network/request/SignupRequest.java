package com.uae.emiratescar.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupRequest {

    @SerializedName("Email")
    @Expose
    private String email;

    @SerializedName("UserName")
    @Expose
    private String fullName;

    @SerializedName("Password")
    @Expose
    private String password;

    @SerializedName("Mobile")
    @Expose
    private String mobile;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

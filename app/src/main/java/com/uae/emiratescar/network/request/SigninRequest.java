package com.uae.emiratescar.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SigninRequest {

    @SerializedName("Username")
    @Expose
    private String email;

    @SerializedName("Password")
    @Expose
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.uae.emiratescar.ui.model;

import android.text.TextUtils;
import android.util.Patterns;

import java.io.Serializable;


public class User implements Serializable
{

    private String email;
    private String password;
    private String fullName;
    private String mobileno;
    private String confirmpassword;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public boolean isValidEmail()
    {
        if(this.email != null && !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return true;
        }
        return false;
    }

    public boolean isValidFullName()
    {
        if(this.fullName != null && !TextUtils.isEmpty(fullName))
        {
            return true;
        }
        return false;
    }

    public boolean isValidPassword()
    {
        if(this.password != null && this.password.length() >= 6)
        {
            return true;
        }
        return false;
    }
    public boolean isValidMobile()
    {
        if(this.mobileno != null && this.mobileno.length() == 10)
        {
            return true;
        }

        return false;
    }

    public boolean isValidConfirmPassword()
    {
        if(this.confirmpassword != null && this.confirmpassword.length() >= 6)
        {
            return true;
        }
        return false;
    }

    public boolean isConfirmPassword()
    {
        if(this.password.equals(this.confirmpassword))
        {
            return true;
        }
        return false;
    }
}
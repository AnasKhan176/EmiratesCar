package com.uae.emiratescar.ui.model;

import android.text.TextUtils;
import android.util.Patterns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class ProductList implements Serializable {
    @SerializedName("ProductId")
    @Expose
    private int PrdId;

    @SerializedName("ProductName")
    @Expose
    private String PrdName;

    @SerializedName("ProductDescription")
    @Expose
    private String PrdDesc;

    @SerializedName("Kilometers")
    @Expose
    private String Kilometer;

    @SerializedName("Color")
    @Expose
    private String Color;

    @SerializedName("Doors")
    @Expose
    private String Door;

    @SerializedName("Price")
    @Expose
    private String Price;

    @SerializedName("ImageDetails")
    @Expose
    private List<ImageDetails> img = null;

    @SerializedName("UpdatedDate")
    @Expose
    private String updatedDate;

    @SerializedName("Mobile")
    @Expose
    private String mobile;

    @SerializedName("CountryName")
    @Expose
    private String country;

    @SerializedName("CityName")
    @Expose
    private String city;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<ImageDetails> getImg() {
        return img;
    }

    public void setImg(List<ImageDetails> img) {
        this.img = img;
    }

    public int getPrdId() {
        return PrdId;
    }

    public void setPrdId(int prdId) {
        PrdId = prdId;
    }

    public String getPrdName() {
        return PrdName;
    }

    public void setPrdName(String prdName) {
        PrdName = prdName;
    }

    public String getPrdDesc() {
        return PrdDesc;
    }

    public void setPrdDesc(String prdDesc) {
        PrdDesc = prdDesc;
    }

    public String getKilometer() {
        return Kilometer;
    }

    public void setKilometer(String kilometer) {
        Kilometer = kilometer;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getDoor() {
        return Door;
    }

    public void setDoor(String door) {
        Door = door;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}




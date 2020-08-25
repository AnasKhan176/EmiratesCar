package com.uae.emiratescar.ui.model;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uae.emiratescar.R;

import java.io.Serializable;

import androidx.databinding.BindingAdapter;

public class ImageDetails implements Serializable {

    @SerializedName("ImagesUrl")
    @Expose
    private String prdImg;

    public String getPrdImg() {
        return prdImg;
    }

    public void setPrdImg(String prdImg) {
        this.prdImg = prdImg;
    }

    // important code for loading image here
    @BindingAdapter({"bind:ImagesUrl"})
    public static void loadImage(ImageView imageView, String imageURL) {
        Glide.with(imageView.getContext())
                .load(imageURL)
                .fitCenter()
                .centerInside()
                .placeholder(R.drawable.splash_logo)
                .into(imageView);
    }
}




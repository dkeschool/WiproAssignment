package com.example.wiproassignment.models;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wiproassignment.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutCanadaListItemModel {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageHref")
    @Expose
    private String imageHref;

    @Override
    public boolean equals(@Nullable Object obj) {
        AboutCanadaListItemModel model = (AboutCanadaListItemModel)obj;
        if(model!=null) {
            if((title!=null && model.title!=null && title.trim().equalsIgnoreCase(model.title.trim())))
                return true;
            else
                return false;
        } else
            return false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}

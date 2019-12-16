package com.task.wiproassignment.pojo;

import android.support.annotation.Nullable;
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
            return (title != null && model.title != null && title.trim().equalsIgnoreCase(model.title.trim()));
        } else
            return false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageHref() {
        return imageHref;
    }

}

package com.example.wiproassignment.models;

import android.support.annotation.Nullable;

public class AboutCanadaListItemModel {

    private String title;
    private String description;
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

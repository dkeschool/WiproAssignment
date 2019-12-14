package com.example.wiproassignment.models;

import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.wiproassignment.R;
import com.squareup.picasso.Picasso;

public class AboutCanadaListItemModel {

    private String title;
    private String description;
    private String imageHref;

    @BindingAdapter({"android:source"})
    public static void loadImage(ImageView view, String url) {
        if (url == null || url.isEmpty()) {
            view.setImageResource(R.drawable.placeholder_iamge);
        } else {
            Picasso.get().load(url).placeholder(R.drawable.placeholder_iamge).into(view);
        }
    }

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

package com.task.wiproassignment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class AboutCanadaResponseModel {

    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("rows")
    @Expose
    private ArrayList<AboutCanadaListItemModel> mRowitems;

    public String getTitle() {
        return mTitle;
    }

    public ArrayList<AboutCanadaListItemModel> getRows() {
        if (mRowitems == null) {
            mRowitems = new ArrayList<>();
        }
        return mRowitems;
    }
}

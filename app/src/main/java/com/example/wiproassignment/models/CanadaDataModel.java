package com.example.wiproassignment.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CanadaDataModel {

    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("rows")
    @Expose
    private ArrayList<CanadaListItemModel> mRowitems;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public ArrayList<CanadaListItemModel> getRows() {
        if (mRowitems == null) {
            mRowitems = new ArrayList<>();
        }
        return mRowitems;
    }

    public void setRows(ArrayList<CanadaListItemModel> rows) {
        this.mRowitems = rows;
    }

}

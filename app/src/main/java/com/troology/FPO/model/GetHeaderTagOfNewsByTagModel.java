package com.troology.FPO.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetHeaderTagOfNewsByTagModel {
    @SerializedName("getTag")
    @Expose
    private ArrayList<GetAllLiveNewsModel> getTag = null;

    public ArrayList<GetAllLiveNewsModel> getGetTag() {
        return getTag;
    }

    public void setGetTag(ArrayList<GetAllLiveNewsModel> getTag) {
        this.getTag = getTag;
    }
}

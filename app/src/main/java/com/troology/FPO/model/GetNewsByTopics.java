package com.troology.FPO.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetNewsByTopics {
    @SerializedName("Topicss")
    @Expose
    private ArrayList<GetAllLiveNewsModel> topicss = null;

    public ArrayList<GetAllLiveNewsModel> getTopicss() {
        return topicss;
    }

    public void setTopicss(ArrayList<GetAllLiveNewsModel> topicss) {
        this.topicss = topicss;
    }
}

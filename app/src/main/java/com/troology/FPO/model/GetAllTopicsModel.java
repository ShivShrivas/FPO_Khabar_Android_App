package com.troology.FPO.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllTopicsModel {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("Language_Name")
    @Expose
    private String languageName;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("Topic_Name")
    @Expose
    private String topicName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

}
package com.troology.FPO.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllInsightsModel {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("createAt")
    @Expose
    private String createAt;
    @SerializedName("updateAt")
    @Expose
    private String updateAt;
    @SerializedName("Image")
    @Expose
    private List<String> image = null;
    @SerializedName("Image_Link")
    @Expose
    private List<String> imageLink = null;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public List<String> getImageLink() {
        return imageLink;
    }

    public void setImageLink(List<String> imageLink) {
        this.imageLink = imageLink;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
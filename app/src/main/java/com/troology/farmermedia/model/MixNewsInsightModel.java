package com.troology.farmermedia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MixNewsInsightModel {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("createAt")
    @Expose
    private String createAt;
    @SerializedName("updateAt")
    @Expose
    private String updateAt;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("District")
    @Expose
    private String district;
    @SerializedName("Villege")
    @Expose
    private String villege;
    @SerializedName("Link")
    @Expose
    private String link;
    @SerializedName("Image")
    @Expose
    private List<String> image = null;
    @SerializedName("Newss")
    @Expose
    private List<News> newss = null;
    @SerializedName("PreviewImage")
    @Expose
    private List<String> previewImage = null;
    @SerializedName("Image_Link")
    @Expose
    private List<String> imageLink = null;
    @SerializedName("Sequence")
    @Expose
    private String sequence;

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("Type")
    @Expose
    private String type;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getVillege() {
        return villege;
    }

    public void setVillege(String villege) {
        this.villege = villege;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public List<News> getNewss() {
        return newss;
    }

    public void setNewss(List<News> newss) {
        this.newss = newss;
    }

    public List<String> getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(List<String> previewImage) {
        this.previewImage = previewImage;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}


package com.troology.farmermedia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Newsdatum {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("createAt")
    @Expose
    private String createAt;
    @SerializedName("updateAt")
    @Expose
    private String updateAt;
    @SerializedName("Language_Name")
    @Expose
    private List<String> languageName = null;
    @SerializedName("Topic_Name")
    @Expose
    private List<String> topicName = null;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("Heading")
    @Expose
    private String heading;
    @SerializedName("LinkText")
    @Expose
    private String linkText;
    @SerializedName("Short_Description")
    @Expose
    private String shortDescription;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("District")
    @Expose
    private String district;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("Tag")
    @Expose
    private String tag;

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

    public List<String> getLanguageName() {
        return languageName;
    }

    public void setLanguageName(List<String> languageName) {
        this.languageName = languageName;
    }

    public List<String> getTopicName() {
        return topicName;
    }

    public void setTopicName(List<String> topicName) {
        this.topicName = topicName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}

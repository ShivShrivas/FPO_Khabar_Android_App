package com.troology.FPO.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {
    @SerializedName("Language_Name")
    @Expose
    private String languageName;
    @SerializedName("Topic_Name")
    @Expose
    private String topicName;
    @SerializedName("Tag")
    @Expose
    private String tag;

    @SerializedName("footerText")
    @Expose
    private String footerText;

    @SerializedName("Heading")
    @Expose
    private String heading;
    @SerializedName("Short_Description")
    @Expose
    private String shortDescription;
    @SerializedName("_id")
    @Expose
    private String id;
    public String getLanguageName() {
        return languageName;
    }

    public String getFooterText() {
        return footerText;
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}


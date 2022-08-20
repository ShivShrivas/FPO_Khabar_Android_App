package com.troology.FPO.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetTopicByLanguageModel {

    @SerializedName("Languages")
    ArrayList<GetAllTopicsModel> languages;

    public ArrayList<GetAllTopicsModel> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<GetAllTopicsModel> languages) {
        this.languages = languages;
    }
}

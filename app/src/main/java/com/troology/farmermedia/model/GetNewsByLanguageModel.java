package com.troology.farmermedia.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetNewsByLanguageModel {
    @SerializedName("Languages")
    ArrayList<GetAllLiveNewsModel> languages;

    public ArrayList<GetAllLiveNewsModel> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<GetAllLiveNewsModel> languages) {
        this.languages = languages;
    }

}

package com.troology.FPO.RetofitImplementation;

import com.troology.FPO.model.GetAllInsightsModel;
import com.troology.FPO.model.GetAllLiveNewsModel;
import com.troology.FPO.model.GetAllMyFeedModel;
import com.troology.FPO.model.GetHeaderTagOfNewsByTagModel;
import com.troology.FPO.model.GetNewsByLanguageModel;
import com.troology.FPO.model.GetNewsByTopics;
import com.troology.FPO.model.GetTopicByLanguageModel;
import com.google.gson.JsonObject;
import com.troology.FPO.model.MixNewsInsightModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public  interface ApiService {

    @POST("Topic/user/gettopicbylanguage")
    Call<GetTopicByLanguageModel> getAllTopics(@Body JsonObject jsonObject);

    @GET("Live/getLiveNews")
    Call<ArrayList<MixNewsInsightModel>> getAllNews();


    @POST("News/getNewsbyLanguage")
    Call<GetNewsByLanguageModel> getAllNewsHindi(@Body JsonObject jsonObject);


    @GET("Live/getLiveInsight")
    Call<ArrayList<GetAllInsightsModel>> getAllInsilghts();

    @POST("News/getNewsbytopics")
    Call<GetNewsByTopics> getNewsByTopics(@Body JsonObject jsonObject);

    @POST("userlogin/adduser")
    Call<JsonObject> addUserTodatabase(@Body JsonObject jsonObject);

    @POST("news/getNewsbytag")
    Call<GetHeaderTagOfNewsByTagModel> getNewsByTags(@Body JsonObject jsonObject);


   @POST("userlogin/sendotp")
    Call<JsonObject> getOTP(@Body JsonObject jsonObject);


   @POST("userlogin/loginwithotp")
    Call<JsonObject> validateOTP(@Body JsonObject jsonObject);


   @POST("News/addbookmark")
    Call<JsonObject> aadBookMark(@Header("Authorization") String Authorization, @Body JsonObject jsonObject);


    @DELETE("News/addbookmark/{id}")
    Call<JsonObject> deleteBookMark(@Path("id") String itemId);

    @GET("News/bookmark/{id}")
    Call<ArrayList<GetAllMyFeedModel>> getallBookmark(@Header("Authorization") String auth,@Path("id") String itemId);


    @POST("News/addmyfeed")
    Call<JsonObject> addFeed(@Header("Authorization") String Authorization, @Body JsonObject jsonObject);


    @GET("News/getmyfeed/{id}")
    Call<ArrayList<GetAllMyFeedModel>> getAllMyFeed(@Header("Authorization") String auth,@Path("id") String itemId);



    @GET("News/search/{id}")
    Call<ArrayList<GetAllLiveNewsModel>> searchNews(@Path("id") String itemId);


    @GET("News/livenews/{id}")
    Call<ArrayList<MixNewsInsightModel>> searchNewsbyId(@Path("id") String itemId);


}

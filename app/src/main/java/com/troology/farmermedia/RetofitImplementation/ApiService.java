package com.troology.farmermedia.RetofitImplementation;

import com.troology.farmermedia.model.GetAllInsightsModel;
import com.troology.farmermedia.model.GetAllLanguageModel;
import com.troology.farmermedia.model.GetAllLiveNewsModel;
import com.troology.farmermedia.model.GetAllMyFeedModel;
import com.troology.farmermedia.model.GetHeaderTagOfNewsByTagModel;
import com.troology.farmermedia.model.GetNewsByLanguageModel;
import com.troology.farmermedia.model.GetNewsByTopics;
import com.troology.farmermedia.model.GetTopicByLanguageModel;
import com.google.gson.JsonObject;
import com.troology.farmermedia.model.MixNewsInsightModel;

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

    @GET("Language/getLanguageforuser")
    Call<ArrayList<GetAllLanguageModel>> getAllLanguage();


}

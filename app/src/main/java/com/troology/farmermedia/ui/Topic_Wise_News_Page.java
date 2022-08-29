package com.troology.farmermedia.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.troology.farmermedia.ApplicationController;
import com.troology.farmermedia.R;
import com.troology.farmermedia.RetofitImplementation.ApiService;
import com.troology.farmermedia.RetofitImplementation.RestClient;
import com.troology.farmermedia.adapters.AllNewsDetailsAdapter;
import com.troology.farmermedia.model.GetAllLiveNewsModel;
import com.troology.farmermedia.model.GetNewsByTopics;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Topic_Wise_News_Page extends AppCompatActivity {
ViewPager viewPagerForTopic;
    AllNewsDetailsAdapter adapter;
    Intent i;
ApplicationController applicationController;
String topic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_wise_news_page);
        viewPagerForTopic=findViewById(R.id.viewPagerForTopic);
        Window window = this.getWindow();
        applicationController= (ApplicationController) getApplication();
        i=getIntent();
        topic=i.getStringExtra("Topic");
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        JsonObject jsonObject=new JsonObject();
        Log.d("TAG", "onCreate: "+topic);
        jsonObject.addProperty("Topic_Name",topic);
        Call<GetNewsByTopics> call=apiService.getNewsByTopics(jsonObject);
        Log.d("TAG", "onCreate: "+jsonObject);
        call.enqueue(new Callback<GetNewsByTopics>() {
            @Override
            public void onResponse(Call<GetNewsByTopics> call, Response<GetNewsByTopics> response) {
                Log.d("TAG", "onResponse: "+response.body());
                if (response.body()!=null){
                    GetNewsByTopics getNewsByTopics=response.body();
                    Log.d("TAG", "onResponse: "+getNewsByTopics.getTopicss().size());
                    ArrayList<GetAllLiveNewsModel> arrayList=getNewsByTopics.getTopicss();
                    adapter=new AllNewsDetailsAdapter(Topic_Wise_News_Page.this,arrayList, applicationController.getTextSize());
                    viewPagerForTopic.setOffscreenPageLimit(5);
                    viewPagerForTopic.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<GetNewsByTopics> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());

            }
        });
    }
}
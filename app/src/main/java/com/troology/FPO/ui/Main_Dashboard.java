package com.troology.FPO.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.troology.FPO.ApplicationController;
import com.troology.FPO.R;
import com.troology.FPO.RetofitImplementation.ApiService;
import com.troology.FPO.RetofitImplementation.RestClient;
import com.troology.FPO.adapters.AllTopicsAdapter;
import com.troology.FPO.adapters.DasboardRecViewAdapter;
import com.troology.FPO.adapters.DasboardRecViewAdapterInsights;
import com.troology.FPO.model.GetAllInsightsModel;
import com.troology.FPO.model.GetAllTopicsModel;
import com.troology.FPO.model.GetTopicByLanguageModel;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_Dashboard extends AppCompatActivity {
    RecyclerView recyclerViewDashboard,dasboradRecViewInsights,recviewAllTopics;
    LinearLayout allNewsCategory;
    ImageView search_img_btn;
    ImageView insight_more,settingsPageBtn;
    SharedPreferences prefs;
    CardView trendingCradlayout,topStoriesCard,myFeedCard,bookmarkCard,unreadcard;
    ApplicationController applicationController;
    AllTopicsAdapter allTopicsAdapter;
    String userId,token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        prefs = getSharedPreferences("UserData", MODE_PRIVATE);

        userId=prefs.getString("userId","");
        token=prefs.getString("token","");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notification_channel", "notification_channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed Successfully";
                        if (!task.isSuccessful()) {
                            msg = "Subscription failed";
                        }
                    }
                });
        recyclerViewDashboard=findViewById(R.id.dasboradRecView);
        unreadcard=findViewById(R.id.unreadcard);
        recviewAllTopics=findViewById(R.id.recviewAllTopics);
        trendingCradlayout=findViewById(R.id.trendingCradlayout);
        bookmarkCard=findViewById(R.id.bookmarkCard);
        applicationController= (ApplicationController) getApplication();
        topStoriesCard=findViewById(R.id.topStoriesCard);
        myFeedCard=findViewById(R.id.myFeedCard);
        trendingCradlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main_Dashboard.this, NewsPageForCategoryByTag.class);
                i.putExtra("Tag","Trending");
                startActivity(i);
            }
        });

        topStoriesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main_Dashboard.this, NewsPageForCategoryByTag.class);
                i.putExtra("Tag","Top");

                startActivity(i);
            }
        });
        unreadcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main_Dashboard.this, UnreadNewsActivity.class);

                startActivity(i);
            }
        });
        myFeedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userId.equals("")) {
                    Intent i=new Intent(Main_Dashboard.this, GetAllMyFeedPage.class);

                    startActivity(i);
                }else {
                    Toast.makeText(Main_Dashboard.this, "Please Login First", Toast.LENGTH_SHORT).show();

                }

            }
        });
        bookmarkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userId.equals("")) {
                    Intent i = new Intent(Main_Dashboard.this, GetAllBookmarkPage.class);

                    startActivity(i);
                }else {
                    Toast.makeText(Main_Dashboard.this, "Please Login First", Toast.LENGTH_SHORT).show();

                }
            }
        });
        GridLayoutManager gridLayoutManager=new GridLayoutManager(Main_Dashboard.this,4){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        recviewAllTopics.setLayoutManager(gridLayoutManager);
        allNewsCategory=findViewById(R.id.allNewsCategory);
        insight_more=findViewById(R.id.insight_more);
        settingsPageBtn=findViewById(R.id.settingsPageBtn);
        search_img_btn=findViewById(R.id.search_img_btn);
        search_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Dashboard.this,SearchNews.class));
            }
        });
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Language_Name",applicationController.getLang());
        Call<GetTopicByLanguageModel> call=apiService.getAllTopics(jsonObject);
        call.enqueue(new Callback<GetTopicByLanguageModel>() {
            @Override
            public void onResponse(Call<GetTopicByLanguageModel> call, Response<GetTopicByLanguageModel> response) {
                GetTopicByLanguageModel arrayList=response.body();
                Log.d("TAG", "onResponse: "+response.body());
                if (response.body()!=null){
                    ArrayList<GetAllTopicsModel> arrayList1=arrayList.getLanguages();
                    allTopicsAdapter =new AllTopicsAdapter(Main_Dashboard.this,arrayList1);
                    recviewAllTopics.setAdapter(allTopicsAdapter);
                    allTopicsAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(Main_Dashboard.this, "Topic are not loaded please contact to your developer", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<GetTopicByLanguageModel> call, Throwable t) {
                Log.d("TAG", "onResponse: "+t.getMessage());

            }
        });
        insight_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main_Dashboard.this,Insights_Page.class);
                i.putExtra("itemNo",0);
                startActivity(i);
            }
        });
        settingsPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Dashboard.this,Settings_Page.class));
                finish();
            }
        });
        allNewsCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Dashboard.this,News_Details_Page.class));
            }
        });
        dasboradRecViewInsights=findViewById(R.id.dasboradRecViewInsights);


        Call<ArrayList<GetAllInsightsModel>> call1=apiService.getAllInsilghts();
        call1.enqueue(new Callback<ArrayList<GetAllInsightsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetAllInsightsModel>> call, Response<ArrayList<GetAllInsightsModel>> response) {
                ArrayList<GetAllInsightsModel> arrayList=response.body();
                if (response.body()!=null){
                    dasboradRecViewInsights.setLayoutManager(new LinearLayoutManager(Main_Dashboard.this,RecyclerView.HORIZONTAL,false));
                    ArrayList<String> arrayList1=new ArrayList<>();
                    for (int i=0;i<arrayList.size();i++){
                       arrayList1.add(arrayList.get(i).getImage().get(0));
                    }
                    dasboradRecViewInsights.setAdapter(new DasboardRecViewAdapterInsights(Main_Dashboard.this,arrayList1));

                }else{
                    Toast.makeText(Main_Dashboard.this, "Topic are not loaded please contact to your developer", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetAllInsightsModel>> call, Throwable t) {

            }
        });

        recyclerViewDashboard.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("AAA");
        arrayList.add("AAA");
        arrayList.add("AAA");
        arrayList.add("AAA");
        arrayList.add("AAA");
        arrayList.add("AAA");
        arrayList.add("AAA");
        recyclerViewDashboard.setAdapter(new DasboardRecViewAdapter(this,arrayList));
    }
}
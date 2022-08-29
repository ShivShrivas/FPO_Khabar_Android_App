package com.troology.farmermedia.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.troology.farmermedia.ApplicationController;
import com.troology.farmermedia.R;
import com.troology.farmermedia.RetofitImplementation.ApiService;
import com.troology.farmermedia.RetofitImplementation.RestClient;
import com.troology.farmermedia.adapters.StaggeredGridAdapter;
import com.troology.farmermedia.model.MixNewsInsightModel;
import com.troology.farmermedia.utils.OnSwipeGestureListener;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AfterSplashPage extends AppCompatActivity {
RecyclerView recyclerViewAfterSplasPage;
CardView readNewsCard;
ImageView imageView3;
TextView read_news_txt,textView24;
ApplicationController applicationController;
ConstraintLayout AfterSplashPageLayout,swipeup_layout;
int setLang=0;

StaggeredGridAdapter adapter;

    Animation animeSlideup;

    @Override
    protected void onRestart() {
        super.onRestart();
        recyclerViewAfterSplasPage.startAnimation(animeSlideup);
        if (adapter!=null)
            adapter.notifyDataSetChanged();



    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter!=null)
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewAfterSplasPage.startAnimation(animeSlideup);

    }

    @Override
    protected void onPause() {
        super.onPause();
        recyclerViewAfterSplasPage.startAnimation(animeSlideup);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_splash_page);
        recyclerViewAfterSplasPage=findViewById(R.id.recyclerViewAfterSplasPage);
        readNewsCard=findViewById(R.id.readNewsCard);
        imageView3=findViewById(R.id.imageView3);
        textView24=findViewById(R.id.textView24);
        swipeup_layout=findViewById(R.id.swipeup_layout);
        read_news_txt=findViewById(R.id.read_news_txt);
        applicationController= (ApplicationController) getApplication();
        AfterSplashPageLayout=findViewById(R.id.AfterSplashPageLayout);
        animeSlideup= AnimationUtils.loadAnimation(AfterSplashPage.this,R.anim.slidepupanime);
        swipeup_layout.setOnTouchListener(new OnSwipeGestureListener(AfterSplashPage.this){
            @Override
            public void onSwipeUp() {
                super.onSwipeUp();
                startActivity(new Intent(AfterSplashPage.this,Main_Dashboard.class));
            }
        });

        read_news_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: "+"click");
                startActivity(new Intent(AfterSplashPage.this,News_Details_Page.class));
            }
        });


        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Language_Name",applicationController.getLang());
        Log.d("TAG", "onCreate: "+jsonObject);
        Call<ArrayList<MixNewsInsightModel>> call=apiService.getAllNews();
        call.enqueue(new Callback<ArrayList<MixNewsInsightModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MixNewsInsightModel>> call, Response<ArrayList<MixNewsInsightModel>> response) {
                if (response.body()!=null){
                    ArrayList<MixNewsInsightModel> arrayList=response.body();

                    textView24.setText(arrayList.size()+" NEWS");
                    ArrayList arrayList1=new ArrayList();
                    for (int i=0;i<arrayList.size();i++){
                        for (int j=0;j<arrayList.size();j++){

                            arrayList1.add(arrayList.get(j).getImage().get(0));
                        }
                    }

                    Log.d("TAG", "onResponse: "+arrayList1.size());
                    recyclerViewAfterSplasPage.setLayoutManager(staggeredGridLayoutManager);
                    adapter=new StaggeredGridAdapter(AfterSplashPage.this,arrayList1);


                    recyclerViewAfterSplasPage.setAdapter(adapter);
                    recyclerViewAfterSplasPage.startAnimation(animeSlideup);
                    adapter.notifyDataSetChanged();

                }else{
                    Toast.makeText(AfterSplashPage.this, applicationController.getLang()+" News are not loaded please contact to your developer", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<MixNewsInsightModel>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });

        readNewsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AfterSplashPage.this,Main_Dashboard.class));
            }
        });
    }
}
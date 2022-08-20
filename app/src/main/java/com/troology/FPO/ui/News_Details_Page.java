package com.troology.FPO.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.troology.FPO.ApplicationController;
import com.troology.FPO.R;
import com.troology.FPO.RetofitImplementation.ApiService;
import com.troology.FPO.RetofitImplementation.RestClient;
import com.troology.FPO.adapters.AllNewsAndInsightsAdapter;
import com.troology.FPO.model.MixNewsInsightModel;
import com.troology.FPO.utils.VerticalViewPager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class News_Details_Page extends AppCompatActivity {
VerticalViewPager viewPager;
AllNewsAndInsightsAdapter adapter;
ApplicationController applicationController;
ConstraintLayout newsDetailsPage;
LinearLayout fontSizeView;
    String lang;

    SharedPreferences sharedpreferences;
public static Toolbar toolbar;




    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        applicationController= (ApplicationController) getApplication();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
        sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);
        lang=sharedpreferences.getString("lang","");
        setContentView(R.layout.activity_news_details_page);
        viewPager=findViewById(R.id.viewPager);
        newsDetailsPage=findViewById(R.id.newsDetailsPage);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        ApplicationController.set.add(0);
            viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {

                ApplicationController.set.add(position);
                super.onPageSelected(position);
            }
        } );

            Call<ArrayList<MixNewsInsightModel>> call=apiService.getAllNews();
            call.enqueue(new Callback<ArrayList<MixNewsInsightModel>>() {
                @Override
                public void onResponse(Call<ArrayList<MixNewsInsightModel>> call, Response<ArrayList<MixNewsInsightModel>> response) {
                    Log.d("TAG", "onResponse: "+response.body());

                    if (response.body()!=null){
                        Log.d("TAG", "onResponse: "+response.body());
                        ArrayList<MixNewsInsightModel> getNewsByLanguageModel=response.body();
                    adapter=new AllNewsAndInsightsAdapter(News_Details_Page.this,getNewsByLanguageModel,applicationController.getTextSize());
                    viewPager.setOffscreenPageLimit(5);
                    viewPager.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                        Toast.makeText(News_Details_Page.this, applicationController.getLang()+" News are not loaded please contact to your developer", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<ArrayList<MixNewsInsightModel>> call, Throwable t) {

                }
            });


    }

}
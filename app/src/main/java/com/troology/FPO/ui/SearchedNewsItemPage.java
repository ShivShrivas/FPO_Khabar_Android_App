package com.troology.FPO.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
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
import com.troology.FPO.adapters.SearchNewsDetailsAdapter;
import com.troology.FPO.model.MixNewsInsightModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchedNewsItemPage extends AppCompatActivity {
    SearchNewsDetailsAdapter adapter;
    ApplicationController applicationController;
    ConstraintLayout newsDetailsPage;
    LinearLayout fontSizeView;
    String id;
    String lang;
ViewPager viewPager;
Intent i;
    SharedPreferences sharedpreferences;
    public static Toolbar toolbar;



    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_news_item_page);
        Window window = this.getWindow();
        i=getIntent();
        id=i.getStringExtra("id");
        applicationController= (ApplicationController) getApplication();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
        sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);
        lang=sharedpreferences.getString("lang","");
        viewPager=findViewById(R.id.viewPagerAfterSearch);
        newsDetailsPage=findViewById(R.id.newsDetailsPage);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        String mainId=id.replace("\"","");
        Call<ArrayList<MixNewsInsightModel>> call=apiService.searchNewsbyId(mainId);
        Log.d("TAG", "onCreate: "+mainId);
        call.enqueue(new Callback<ArrayList<MixNewsInsightModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MixNewsInsightModel>> call, Response<ArrayList<MixNewsInsightModel>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                if (response.body()!=null){
                    ArrayList<MixNewsInsightModel> arrayList=response.body();
                    adapter=new SearchNewsDetailsAdapter(SearchedNewsItemPage.this,arrayList,applicationController.getTextSize());
                    viewPager.setOffscreenPageLimit(5);
                    viewPager.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(SearchedNewsItemPage.this, applicationController.getLang()+" News are not loaded please contact to your developer", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<MixNewsInsightModel>> call, Throwable t) {

            }
        });


    }


}
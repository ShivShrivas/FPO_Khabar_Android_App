package com.troology.FPO.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.troology.FPO.R;
import com.troology.FPO.RetofitImplementation.ApiService;
import com.troology.FPO.RetofitImplementation.RestClient;
import com.troology.FPO.adapters.InsightsPageAdapter;
import com.troology.FPO.model.GetAllInsightsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Insights_Page extends AppCompatActivity {
    ViewPager2 insights_menu;
    Toolbar toolbar2;
    Intent i;
    int itemNo;
    InsightsPageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insights_page);
        insights_menu=findViewById(R.id.viewPager2);
        i=getIntent();
        itemNo=i.getIntExtra("itemNo",0);

        toolbar2=findViewById(R.id.toolbar2);
        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<ArrayList<GetAllInsightsModel>> call1=apiService.getAllInsilghts();
        call1.enqueue(new Callback<ArrayList<GetAllInsightsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetAllInsightsModel>> call, Response<ArrayList<GetAllInsightsModel>> response) {
                ArrayList<GetAllInsightsModel> arrayList=response.body();
                if (response.body()!=null){
                    adapter=new InsightsPageAdapter(Insights_Page.this,arrayList);
                    insights_menu.setAdapter(adapter);
                    insights_menu.setCurrentItem(itemNo);
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(Insights_Page.this, "Topic are not loaded please contact to your developer", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetAllInsightsModel>> call, Throwable t) {

            }
        });


    }
}
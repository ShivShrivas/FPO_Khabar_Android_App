package com.troology.FPO.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.troology.FPO.ApplicationController;
import com.troology.FPO.R;
import com.troology.FPO.RetofitImplementation.ApiService;
import com.troology.FPO.RetofitImplementation.RestClient;
import com.troology.FPO.adapters.AllNewsDetailsAdapter;
import com.troology.FPO.model.GetAllLiveNewsModel;
import com.troology.FPO.model.GetHeaderTagOfNewsByTagModel;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPageForCategoryByTag extends AppCompatActivity {
ViewPager viewPagerForCategory;
Intent i;
String Tag;
ApplicationController applicationController;
    AllNewsDetailsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page_for_category_by_tag);
        viewPagerForCategory=findViewById(R.id.viewPagerForCategory);
        Window window = this.getWindow();
        i=getIntent();
        applicationController= (ApplicationController) getApplication();
        Tag=i.getStringExtra("Tag");
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Tag",Tag);
        Call<GetHeaderTagOfNewsByTagModel> call=apiService.getNewsByTags(jsonObject);
        call.enqueue(new Callback<GetHeaderTagOfNewsByTagModel>() {
            @Override
            public void onResponse(Call<GetHeaderTagOfNewsByTagModel> call, Response<GetHeaderTagOfNewsByTagModel> response) {
                Log.d("TAG", "onResponse: tags "+response.body());
                if (response.body()!=null) {
                    ArrayList<GetAllLiveNewsModel> list = response.body().getGetTag();
                    ArrayList<GetAllLiveNewsModel> list2 = new ArrayList<>();
                        for (int i=0;i<list.size();i++){
                            if (list.get(i).getNewss().size()!=0){
                                list2.add(list.get(i));
                            }

                        }
                        if (list2.size()!=0){
                            adapter = new AllNewsDetailsAdapter(NewsPageForCategoryByTag.this, list2,applicationController.getTextSize());
                            viewPagerForCategory.setOffscreenPageLimit(5);
                            viewPagerForCategory.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }

                }{
                    Toast.makeText(NewsPageForCategoryByTag.this, "Api in not working please contact to developer", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetHeaderTagOfNewsByTagModel> call, Throwable t) {

            }
        });

    }
}
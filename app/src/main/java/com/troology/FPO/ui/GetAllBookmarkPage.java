package com.troology.FPO.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.troology.FPO.ApplicationController;
import com.troology.FPO.R;
import com.troology.FPO.RetofitImplementation.ApiService;
import com.troology.FPO.RetofitImplementation.RestClient;
import com.troology.FPO.adapters.AllMyFeedDetailsAdapter;
import com.troology.FPO.model.GetAllMyFeedModel;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllBookmarkPage extends AppCompatActivity {
    ApplicationController applicationController;
    Window window;
    SharedPreferences sharedpreferences;
    SharedPreferences prefs;
    ConstraintLayout newsDetailsPage;
    AllMyFeedDetailsAdapter adapter;
    String lang;
    String userId, token;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_bookmark_page);
        applicationController = (ApplicationController) getApplication();
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);
        prefs = getSharedPreferences("UserData", MODE_PRIVATE);

        userId = prefs.getString("userId", "");
        token = prefs.getString("token", "");
        lang = sharedpreferences.getString("lang", "");
        viewPager = findViewById(R.id.viewPagerBookmark);
        newsDetailsPage = findViewById(R.id.newsDetailsPage);
        RestClient restClient = new RestClient();
        ApiService apiService = restClient.getApiService();
        if (!userId.equals("")) {
            if (!token.equals("")) {
                Log.d("TAG", "onClick: kf" + token);
                JsonObject jsonObject = new JsonObject();
                String uId = userId.replace("\"", "");
                jsonObject.addProperty("user_id", uId);
                Log.d("TAG", "onClick: " + jsonObject);
                String tokenId = token.replace("\"", "");
            Call<ArrayList<GetAllMyFeedModel>> call=apiService.getallBookmark(tokenId,uId);
            call.enqueue(new Callback<ArrayList<GetAllMyFeedModel>>() {
                @Override
                public void onResponse(Call<ArrayList<GetAllMyFeedModel>> call, Response<ArrayList<GetAllMyFeedModel>> response) {
                    if (response.body()!=null){
                        ArrayList<GetAllMyFeedModel> arrayList=response.body();
                        adapter=new AllMyFeedDetailsAdapter(GetAllBookmarkPage.this,arrayList, applicationController.getTextSize());
                        viewPager.setOffscreenPageLimit(5);
                        viewPager.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(GetAllBookmarkPage.this, "Bookmark API is not working!!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ArrayList<GetAllMyFeedModel>> call, Throwable t) {

                }
            });
            }else   Toast.makeText(GetAllBookmarkPage.this, "please login first", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(GetAllBookmarkPage.this, "please login first", Toast.LENGTH_SHORT).show();


        }

    }
}
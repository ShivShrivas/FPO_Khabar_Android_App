package com.troology.FPO.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

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
import com.troology.FPO.adapters.AllMyFeedDetailsAdapter;
import com.troology.FPO.model.GetAllMyFeedModel;
import com.troology.FPO.utils.VerticalViewPager;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllMyFeedPage extends AppCompatActivity {
    VerticalViewPager viewPager;
    AllMyFeedDetailsAdapter adapter;
    ApplicationController applicationController;
    ConstraintLayout newsDetailsPage;
    LinearLayout fontSizeView;
    String lang;
    SharedPreferences prefs;
    SharedPreferences sharedpreferences;
    String userId,token;
    public static Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_my_feed_page);

        Window window = this.getWindow();
        applicationController= (ApplicationController) getApplication();
        prefs = getSharedPreferences("UserData", MODE_PRIVATE);

        userId=prefs.getString("userId","");
        token=prefs.getString("token","");
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
        sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);
        lang=sharedpreferences.getString("lang","");
        viewPager=findViewById(R.id.viewPagerAllMyFeed);
        newsDetailsPage=findViewById(R.id.newsDetailsPage);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        String uId=userId.replace("\"","");
        Log.d("TAG", "onCreate: "+uId);
        if (!userId.equals("")) {
            if (!token.equals("")) {
                Log.d("TAG", "onClick: kf" + token);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("user_id", uId);
                Log.d("TAG", "onClick: " + jsonObject);
                String tokenId = token.replace("\"", "");
                Call<ArrayList<GetAllMyFeedModel>> call=apiService.getAllMyFeed(tokenId,uId);
                call.enqueue(new Callback<ArrayList<GetAllMyFeedModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<GetAllMyFeedModel>> call, Response<ArrayList<GetAllMyFeedModel>> response) {
                        Log.d("TAG", "onResponse: "+response.body());

                        if (response.body()!=null){
                            ArrayList<GetAllMyFeedModel> arrayList=response.body();
                            adapter=new AllMyFeedDetailsAdapter(GetAllMyFeedPage.this,arrayList,applicationController.getTextSize());
                            viewPager.setOffscreenPageLimit(5);
                            viewPager.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(GetAllMyFeedPage.this, "Service is not working", Toast.LENGTH_SHORT).show();

                        }



                    }

                    @Override
                    public void onFailure(Call<ArrayList<GetAllMyFeedModel>> call, Throwable t) {
                        Log.d("TAG", "onFailure: "+t.getMessage());
                    }
                });

            }else   Toast.makeText(GetAllMyFeedPage.this, "please login first", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(GetAllMyFeedPage.this, "please login first", Toast.LENGTH_SHORT).show();


        }





    }
}
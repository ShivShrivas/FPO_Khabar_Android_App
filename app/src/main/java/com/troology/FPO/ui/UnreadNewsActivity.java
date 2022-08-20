package com.troology.FPO.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.troology.FPO.ApplicationController;
import com.troology.FPO.R;
import com.troology.FPO.RetofitImplementation.ApiService;
import com.troology.FPO.RetofitImplementation.RestClient;
import com.troology.FPO.adapters.AllNewsDetailsAdapter;
import com.troology.FPO.model.GetAllLiveNewsModel;
import com.troology.FPO.model.GetNewsByLanguageModel;
import com.troology.FPO.utils.VerticalViewPager;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnreadNewsActivity extends AppCompatActivity {
    VerticalViewPager viewPager;
    AllNewsDetailsAdapter adapter;
    ApplicationController applicationController;
    ConstraintLayout newsDetailsPage;
    LinearLayout fontSizeView;
    String lang;

    SharedPreferences sharedpreferences;
    public static Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unread_news);
        Window window = this.getWindow();
        applicationController= (ApplicationController) getApplication();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
        sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);
        lang=sharedpreferences.getString("lang","");
        viewPager=findViewById(R.id.viewPagerUnread);
        newsDetailsPage=findViewById(R.id.newsDetailsPage);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Language_Name",applicationController.getLang());
        Call<GetNewsByLanguageModel> call=apiService.getAllNewsHindi(jsonObject);
        call.enqueue(new Callback<GetNewsByLanguageModel>() {
            @Override
            public void onResponse(Call<GetNewsByLanguageModel> call, Response<GetNewsByLanguageModel> response) {
                if (response.body()!=null){
                    GetNewsByLanguageModel getNewsByLanguageModel=response.body();
                    ArrayList<GetAllLiveNewsModel> arrayList=getNewsByLanguageModel.getLanguages();
                    ArrayList<GetAllLiveNewsModel> arrayListUnread=new ArrayList<GetAllLiveNewsModel>();
                    List<Integer> list = new ArrayList<Integer>(ApplicationController.set);

                    for (int i=0;i<arrayList.size();i++){
                       try {
                           if (i>=list.size()){
                               arrayListUnread.add(arrayList.get(i));
                           }
                       }catch (Exception e){
                           arrayListUnread.add(arrayList.get(i));

                       }
                    }
                    adapter=new AllNewsDetailsAdapter(UnreadNewsActivity.this,arrayListUnread,applicationController.getTextSize());
                    viewPager.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(UnreadNewsActivity.this, applicationController.getLang()+" News are not loaded please contact to your developer", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<GetNewsByLanguageModel> call, Throwable t) {

            }
        });



    }
}
package com.troology.FPO.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.troology.FPO.ApplicationController;
import com.troology.FPO.R;
import com.troology.FPO.RetofitImplementation.ApiService;
import com.troology.FPO.RetofitImplementation.RestClient;
import com.troology.FPO.adapters.AllLanguageAdapter;
import com.troology.FPO.model.GetAllLanguageModel;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Language_Change_Page_New extends AppCompatActivity {
ConstraintLayout englishCard,hindiCard;
    ApplicationController applicationController;
    String lang;
    AllLanguageAdapter adapter;
    Button buttonForLanguageChange;
    RecyclerView recyclerViewLanguagePage;
    ImageView SymbImgEng, SymbImgHindi;
    SharedPreferences sharedpreferences;
    TextView langSymbHindi,langSymbEng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_change_page_new);
        applicationController= (ApplicationController) getApplication();
        sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);
        recyclerViewLanguagePage=findViewById(R.id.recyclerViewLanguagePage);
        recyclerViewLanguagePage.setLayoutManager(new GridLayoutManager(this,2));
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<ArrayList<GetAllLanguageModel>> call=apiService.getAllLanguage();
        call.enqueue(new Callback<ArrayList<GetAllLanguageModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetAllLanguageModel>> call, Response<ArrayList<GetAllLanguageModel>> response) {
                ArrayList<GetAllLanguageModel> arrayList=response.body();
                adapter=new AllLanguageAdapter(Language_Change_Page_New.this,arrayList);
                recyclerViewLanguagePage.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<GetAllLanguageModel>> call, Throwable t) {

            }
        });

        englishCard=findViewById(R.id.englishCard);
        hindiCard=findViewById(R.id.hindiCard);
        langSymbHindi=findViewById(R.id.langSymbHindi);
        langSymbEng=findViewById(R.id.langSymbEng);
        SymbImgEng=findViewById(R.id.symbleImgEng);
        SymbImgHindi=findViewById(R.id.symbleImgHindi);
        buttonForLanguageChange=findViewById(R.id.buttonForLanguageChange);

        lang=sharedpreferences.getString("lang","");
        Log.d("TAG", "onCreate: "+lang);
        if (lang.equals("hi")){
            hindiCard.setBackground(ContextCompat.getDrawable(Language_Change_Page_New.this,R.drawable.lang_card_choosedbg));
            langSymbHindi.setTextColor(ContextCompat.getColor(Language_Change_Page_New.this,R.color.purple_500));
            englishCard.setBackground(ContextCompat.getDrawable(Language_Change_Page_New.this,R.drawable.lang_card_defaultbg));
            langSymbEng.setTextColor(ContextCompat.getColor(Language_Change_Page_New.this,R.color.purple_500));
            String languageToLoad  = "hi"; // your language code
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
            getBaseContext().getResources().getDisplayMetrics());
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("lang", "hi");
            editor.commit();
            applicationController.setLang("Hindi");
            SymbImgHindi.setVisibility(View.VISIBLE);
            SymbImgEng.setVisibility(View.GONE);

        }else{
            englishCard.setBackground(ContextCompat.getDrawable(Language_Change_Page_New.this,R.drawable.lang_card_choosedbg));
            langSymbEng.setTextColor(ContextCompat.getColor(Language_Change_Page_New.this,R.color.purple_500));
            hindiCard.setBackground(ContextCompat.getDrawable(Language_Change_Page_New.this,R.drawable.lang_card_defaultbg));
            langSymbHindi.setTextColor(ContextCompat.getColor(Language_Change_Page_New.this,R.color.purple_500));
            String languageToLoad  = "en"; // your language code
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
            getBaseContext().getResources().getDisplayMetrics());
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("lang", "en");
            editor.commit();
            applicationController.setLang("English");
            SymbImgEng.setVisibility(View.VISIBLE);
            SymbImgHindi.setVisibility(View.GONE);
        }

        hindiCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hindiCard.setBackground(ContextCompat.getDrawable(Language_Change_Page_New.this,R.drawable.lang_card_choosedbg));
                langSymbHindi.setTextColor(ContextCompat.getColor(Language_Change_Page_New.this,R.color.purple_500));
                langSymbEng.setTextColor(ContextCompat.getColor(Language_Change_Page_New.this,R.color.gray));
                englishCard.setBackground(ContextCompat.getDrawable(Language_Change_Page_New.this,R.drawable.lang_card_defaultbg));
                SymbImgEng.setVisibility(View.GONE);
                SymbImgHindi.setVisibility(View.VISIBLE);
                String languageToLoad  = "hi"; // your language code
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                SharedPreferences.Editor editor = sharedpreferences.edit();
                applicationController.setLang("Hindi");

                editor.putString("lang", "hi");
                editor.apply();

                buttonForLanguageChange.setText("भाषा चुनें");


            }
        });

        englishCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                englishCard.setBackground(ContextCompat.getDrawable(Language_Change_Page_New.this,R.drawable.lang_card_choosedbg));
                langSymbEng.setTextColor(ContextCompat.getColor(Language_Change_Page_New.this,R.color.purple_500));
                hindiCard.setBackground(ContextCompat.getDrawable(Language_Change_Page_New.this,R.drawable.lang_card_defaultbg));
                langSymbHindi.setTextColor(ContextCompat.getColor(Language_Change_Page_New.this,R.color.purple_500));
                SymbImgEng.setVisibility(View.VISIBLE);
                SymbImgHindi.setVisibility(View.GONE);
                String languageToLoad  = "en"; // your language code
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

                SharedPreferences.Editor editor = sharedpreferences.edit();
                applicationController.setLang("English");

                editor.putString("lang", "en");
                editor.apply();
             buttonForLanguageChange.setText("Choose Language");
            }
        });

        buttonForLanguageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Language_Change_Page_New.this,Settings_Page.class);
                startActivity(intent);

                finish();
            }
        });
    }
}
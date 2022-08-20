package com.troology.FPO.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.troology.FPO.ApplicationController;
import com.troology.FPO.R;
import com.troology.FPO.utils.OnSwipeGestureListener;

import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class Language_Change_Page extends AppCompatActivity {
    Button btnHindi,btnEnglish;
    ConstraintLayout languageChangePageLayout;
    ApplicationController applicationController;
    GifImageView gifImageView;
    String lang;
    SharedPreferences sharedpreferences;
    TextView textView13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_change_page);
        applicationController= (ApplicationController) getApplication();
        btnHindi=findViewById(R.id.btnHindi);
        gifImageView=findViewById(R.id.gifImageView);
        textView13=findViewById(R.id.textView13);
        btnEnglish=findViewById(R.id.btnEnglish);
        sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);
        lang=sharedpreferences.getString("lang","");
        Log.d("TAG", "onCreate: "+lang);

        if (lang.equals("hi")){
            btnHindi.setBackground(ContextCompat.getDrawable(Language_Change_Page.this,R.drawable.language_btn_bg_dark));
            btnHindi.setTextColor(ContextCompat.getColor(Language_Change_Page.this,R.color.white));
            btnEnglish.setBackground(ContextCompat.getDrawable(Language_Change_Page.this,R.drawable.language_btn_bg));
            btnEnglish.setTextColor(ContextCompat.getColor(Language_Change_Page.this,R.color.purple_200));
            String languageToLoad  = "hi"; // your language code
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());

            textView13.setText("ऊपर स्वाइप करें");
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("lang", "hi");
            editor.apply();
            applicationController.setLang("Hindi");
            gifImageView.setVisibility(View.VISIBLE);
            textView13.setVisibility(View.VISIBLE);
        }else{
            btnEnglish.setBackground(ContextCompat.getDrawable(Language_Change_Page.this,R.drawable.language_btn_bg_dark));
            btnEnglish.setTextColor(ContextCompat.getColor(Language_Change_Page.this,R.color.white));
            btnHindi.setBackground(ContextCompat.getDrawable(Language_Change_Page.this,R.drawable.language_btn_bg));
            btnHindi.setTextColor(ContextCompat.getColor(Language_Change_Page.this,R.color.purple_200));
            String languageToLoad  = "en"; // your language code
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
            textView13.setText("Swipe up");

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("lang", "en");
            editor.apply();
            applicationController.setLang("English");
            gifImageView.setVisibility(View.VISIBLE);
            textView13.setVisibility(View.VISIBLE);
        }

        languageChangePageLayout=findViewById(R.id.languageChangePageLayout);
        btnHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHindi.setBackground(ContextCompat.getDrawable(Language_Change_Page.this,R.drawable.language_btn_bg_dark));
                btnHindi.setTextColor(ContextCompat.getColor(Language_Change_Page.this,R.color.white));
                btnEnglish.setBackground(ContextCompat.getDrawable(Language_Change_Page.this,R.drawable.language_btn_bg));
                btnEnglish.setTextColor(ContextCompat.getColor(Language_Change_Page.this,R.color.purple_200));
                String languageToLoad  = "hi"; // your language code
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                SharedPreferences.Editor editor = sharedpreferences.edit();
                textView13.setText("ऊपर स्वाइप करें");

                editor.putString("lang", "hi");
                editor.apply();
                applicationController.setLang("Hindi");
                gifImageView.setVisibility(View.VISIBLE);
                textView13.setVisibility(View.VISIBLE);
            }
        });

        languageChangePageLayout.setOnTouchListener(new OnSwipeGestureListener(Language_Change_Page.this){
            @Override
            public void onSwipeUp() {
                super.onSwipeUp();
                Toast.makeText(Language_Change_Page.this, "swipe up", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Language_Change_Page.this,AfterSplashPage.class));
                finish();
            }
        });
        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEnglish.setBackground(ContextCompat.getDrawable(Language_Change_Page.this,R.drawable.language_btn_bg_dark));
                btnEnglish.setTextColor(ContextCompat.getColor(Language_Change_Page.this,R.color.white));
                btnHindi.setBackground(ContextCompat.getDrawable(Language_Change_Page.this,R.drawable.language_btn_bg));
                btnHindi.setTextColor(ContextCompat.getColor(Language_Change_Page.this,R.color.purple_200));
                String languageToLoad  = "en"; // your language code
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                textView13.setText("Swipe up");

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("lang", "en");
                editor.apply();
                applicationController.setLang("English");
                gifImageView.setVisibility(View.VISIBLE);
                textView13.setVisibility(View.VISIBLE);
            }
        });


    }
}
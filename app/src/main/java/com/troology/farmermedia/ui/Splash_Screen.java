package com.troology.farmermedia.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.troology.farmermedia.ApplicationController;
import com.troology.farmermedia.R;

import java.util.ArrayList;
import java.util.Locale;

public class Splash_Screen extends AppCompatActivity {
    ArrayAdapter<String> arrayAdapter;
    private static Dialog dialog;
    String lang;
    ApplicationController applicationController;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        applicationController = (ApplicationController) getApplication();
        sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            try{
                lang=sharedpreferences.getString("lang","");
                if (lang.equals("hi")){
                    String languageToLoad  = "hi"; // your language code
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                applicationController.setLang("Hindi");
                    startActivity(new Intent(Splash_Screen.this,AfterSplashPage.class));
                    finish();


                }else{
                    String languageToLoad  = "en"; // your language code
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                    applicationController.setLang("English");
                    startActivity(new Intent(Splash_Screen.this,AfterSplashPage.class));
                    finish();

                }
            }catch (Exception e){
                startActivity(new Intent(Splash_Screen.this,Language_Change_Page.class));
                finish();


            }


            }
        },2000);
    }
    void showDialogForLang(){
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("English");
        arrayList.add("Hindi");

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(R.layout.custom_text_spiiner);
        dialog = new Dialog(Splash_Screen.this);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setContentView(R.layout.language_card);
        Spinner spinnerDialog_Lang=(Spinner) dialog.findViewById(R.id.spinnerDialog_Lang);
        spinnerDialog_Lang.setAdapter(arrayAdapter);

        Button changeLanguage = (Button) dialog.findViewById(R.id.changeLanguage);
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerDialog_Lang.getSelectedItem().toString().equals("Hindi")){

                    startActivity(new Intent(Splash_Screen.this,AfterSplashPage.class));
                    dialog.dismiss();
                }else{

                    startActivity(new Intent(Splash_Screen.this,AfterSplashPage.class));
                    dialog.dismiss();
                }

            }
        });

        dialog.show();
    }

}
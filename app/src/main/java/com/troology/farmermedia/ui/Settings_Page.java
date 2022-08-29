package com.troology.farmermedia.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.troology.farmermedia.ApplicationController;
import com.troology.farmermedia.R;
import com.troology.farmermedia.RetofitImplementation.ApiService;
import com.troology.farmermedia.RetofitImplementation.RestClient;
import com.troology.farmermedia.ui.AfterSplashPage;
import com.troology.farmermedia.ui.Language_Change_Page_New;
import com.troology.farmermedia.ui.Main_Dashboard;
import com.troology.farmermedia.ui.loginActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Settings_Page extends AppCompatActivity {
    Spinner language_spinner;
    ConstraintLayout languageLayout;
    Button signInBtn;
    GraphRequest request;
    AccessToken accessToken;
    Spinner textSizeSpinner;
    TextView langName;
    ApplicationController applicationController;
    String userFbId;
    String isUserLoggedIn;
    SharedPreferences prefs;
    Boolean isDarkModeOn;
    String userId,token;
    LoginManager loginManager;
    CallbackManager callbackManager;
    String img;
    Switch swithThemeApp;
    ImageView imageView77;
    GoogleSignInOptions googleSignInOptions;
    ConstraintLayout signInLayout;
    GoogleSignInClient googleSignInClient;
    CircleImageView circleImageViewprofilePic;
    ImageView loginButton;
    String[] language_list = { "English", "Hindi"};

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,Main_Dashboard.class));
        finish();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        img=prefs.getString("imageUrl","");
        isUserLoggedIn=prefs.getString("logged_in","");
        userId=prefs.getString("userId","");
        try {
            Glide
                    .with(this)
                    .load(img)
                    .centerCrop()
                    .placeholder(R.drawable.ic_person)
                    .into(circleImageViewprofilePic);
            if (isUserLoggedIn.equals("1")){
                signInLayout.setVisibility(View.GONE);
                circleImageViewprofilePic.setEnabled(true);


            }else{
                signInLayout.setVisibility(View.VISIBLE);
                circleImageViewprofilePic.setEnabled(false);
            }

        }catch (Exception e){

        }
        if (!userId.equals("")){
            RestClient restClient=new RestClient();
            ApiService apiService=restClient.getApiService();
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("email",userId);
            jsonObject.addProperty("phone",0);
            Log.d("TAG", "onCreate: "+jsonObject);
            Call<JsonObject> call=apiService.addUserTodatabase(jsonObject);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.body()!=null){
                        //save value
                        try { Log.d("TAG", "onResponse: "+response.body().get("userregisster"));
                            JsonElement userregisterModel=response.body().get("userregisster");
                            JsonObject jsonObject1= (JsonObject) userregisterModel;
                            Log.d("TAG", "onResponse: "+jsonObject1.get("_id"));


                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("userMainId", String.valueOf(jsonObject1.get("_id")));
                            editor.putString("token", response.body().get("token").toString());  //save value
                            editor.commit();
                        }catch (Exception e){
                            Toast.makeText(Settings_Page.this, "we did not find token", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(Settings_Page.this, "Add user to server is not working", Toast.LENGTH_SHORT).show();
                    }



                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.d("TAG", "onFailure: "+t.getMessage());
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
//        language_spinner=findViewById(R.id.language_spinner);
        applicationController= (ApplicationController) getApplication();
        signInBtn=findViewById(R.id.signInBtn);
        langName=findViewById(R.id.langName);
        swithThemeApp=findViewById(R.id.swithThemeApp);
        imageView77=findViewById(R.id.imageView77);
        textSizeSpinner=findViewById(R.id.textSizeSpinner);
        circleImageViewprofilePic=findViewById(R.id.circleImageViewprofilePic);
        Intent i=getIntent();
        loginButton =  findViewById(R.id.login_button);
        languageLayout =  findViewById(R.id.languageLayout);
        signInLayout =  findViewById(R.id.signInLayout);
        loginManager=LoginManager.getInstance();
        List<String> categories = new ArrayList<String>();
        categories.add("Default");
        categories.add("Large");
        Log.d("TAG", "onCreate: "+ApplicationController.set);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textSizeSpinner.setAdapter(dataAdapter);
        textSizeSpinner.setSelection(applicationController.getTextSize());
        textSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    applicationController.setTextSize(0);
                }else {
                    applicationController.setTextSize(1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        isDarkModeOn=prefs.getBoolean("isDarkModeOn",false);
        Log.d("TAG", "onCreate: "+isDarkModeOn);
        if (!isDarkModeOn.equals(null)){
            if (isDarkModeOn){
                swithThemeApp.setChecked(true);
            }else{
                swithThemeApp.setChecked(false);
            }
        }
        imageView77.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(Settings_Page.this, Main_Dashboard.class));
            finish();
                    }
        });
        langName.setText(applicationController.getLang());
        googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient= GoogleSignIn.getClient(this,googleSignInOptions);
        GoogleSignInAccount googleSignInAccount=GoogleSignIn.getLastSignedInAccount(this);
        if (googleSignInAccount!=null){
            try {
                img=googleSignInAccount.getPhotoUrl().toString();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("logged_in", "1");  //save value
                editor.putString("imageUrl", img);  //save value
                editor.commit();
            }catch (Exception e){

            }



        }
        swithThemeApp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("isDarkModeOn", true);
                    editor.commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    finish();
                    startActivity(getIntent());

                } else {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("isDarkModeOn", false);
                    editor.commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    finish();
                    startActivity(getIntent());


                }

            }
        });
        img=prefs.getString("imageUrl","");
        isUserLoggedIn=prefs.getString("logged_in","");
        userId=prefs.getString("userId","");
        token=prefs.getString("token","");

      try {
          if (token.equals(null)){
              signInLayout.setVisibility(View.VISIBLE);
          }
      }catch (Exception exception){
          signInLayout.setVisibility(View.VISIBLE);

      }
        if (isUserLoggedIn.equals("1")){
            signInLayout.setVisibility(View.GONE);
            circleImageViewprofilePic.setEnabled(true);


        }else{
            signInLayout.setVisibility(View.VISIBLE);
            circleImageViewprofilePic.setEnabled(false);
        }
        try {
            Glide
                    .with(this)
                    .load(img)
                    .centerCrop()
                    .into(circleImageViewprofilePic);

        }catch (Exception e){

        }
        if (!userId.equals("")){
            RestClient restClient=new RestClient();
            ApiService apiService=restClient.getApiService();
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("email",userId);
            jsonObject.addProperty("phone",0);
            Log.d("TAG", "onCreate: "+jsonObject);
            Call<JsonObject> call=apiService.addUserTodatabase(jsonObject);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.body()!=null){
                        try {
                            Log.d("TAG", "onResponse: "+response.body().get("userregisster"));
                            JsonElement userregisterModel=response.body().get("userregisster");
                            JsonObject jsonObject1= (JsonObject) userregisterModel;
                            Log.d("TAG", "onResponse: "+jsonObject1.get("_id"));

                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("userMainId", String.valueOf(jsonObject1.get("_id")));  //save value

                            editor.putString("token", response.body().get("token").toString());  //save value
                            editor.commit();
                        }catch (Exception e){
                            Toast.makeText(Settings_Page.this, "User profile is not generated!! ", Toast.LENGTH_SHORT).show();

                        }

                    }else{
                        Toast.makeText(Settings_Page.this, "User profile services are not working", Toast.LENGTH_SHORT).show();
                    }



                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.d("TAG", "onFailure: "+t.getMessage());
                }
            });
        }
        callbackManager=CallbackManager.Factory.create();
        languageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings_Page.this, Language_Change_Page_New.class));
                finish();
            }
        });

        circleImageViewprofilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog dialog=new BottomSheetDialog(Settings_Page.this);
//                View sheetView = getLayoutInflater().inflate(R.layout.fontsize_increase_dialog, newsDetailsPage);
                dialog.setContentView(R.layout.logout_dialog);
                TextView cancleTextView=dialog.findViewById(R.id.cancleTextView);
                TextView logouttxtView=dialog.findViewById(R.id.logouttxtView);
                CircleImageView circleImageViewprofilePicView=dialog.findViewById(R.id.circleImageViewprofilePicView);
                Glide
                        .with(Settings_Page.this)
                        .load(img)
                        .centerCrop()
                        .placeholder(R.drawable.ic_person)
                        .into(circleImageViewprofilePicView);

                cancleTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                logouttxtView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("logged_in", "0");  //save value
                                editor.putString("imageUrl", "");  //save value
                                editor.putString("userMainId", "");  //save value
                                editor.putString("userId", "");  //save value
                                editor.apply();

                                finish();
                                startActivity(new Intent(Settings_Page.this, AfterSplashPage.class));
                            }
                        });
                    }
                });
                dialog.show();
            }
        });
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,language_list);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        language_spinner.setAdapter(aa);
//        sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings_Page.this, loginActivity.class));
            }
        });

//        language_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (language_spinner.getSelectedItem().equals("English")){
//                    String languageToLoad  = "en"; // your language code
//                    Locale locale = new Locale(languageToLoad);
//                    Locale.setDefault(locale);
//                    Configuration config = new Configuration();
//                    config.locale = locale;
//                    getBaseContext().getResources().updateConfiguration(config,
//                            getBaseContext().getResources().getDisplayMetrics());
//
//
//                    SharedPreferences.Editor editor = sharedpreferences.edit();
//                    editor.putString("lang", "en");
//                    editor.apply();
//                    recreate();
//                }else{
//                    String languageToLoad  = "hi"; // your language code
//                    Locale locale = new Locale(languageToLoad);
//                    Locale.setDefault(locale);
//                    Configuration config = new Configuration();
//                    config.locale = locale;
//                    getBaseContext().getResources().updateConfiguration(config,
//                            getBaseContext().getResources().getDisplayMetrics());
//
//
//                    SharedPreferences.Editor editor = sharedpreferences.edit();
//                    editor.putString("lang", "hi");
//                    editor.apply();
//                    recreate();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }

}
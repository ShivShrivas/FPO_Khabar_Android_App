package com.troology.FPO.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.troology.FPO.R;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class loginActivity extends AppCompatActivity {
Button buttonGoogleLogin,buttonFbLogin,buttonTweeterLogin,buttonCallLogin;
GoogleSignInOptions googleSignInOptions;
GoogleSignInClient googleSignInClient;
    SharedPreferences prefs;
    CallbackManager callbackManager;
    private static final String EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonFbLogin=findViewById(R.id.buttonFbLogin);
        buttonGoogleLogin=findViewById(R.id.buttonGoogleLogin);
        buttonCallLogin=findViewById(R.id.buttonCallLogin);
        buttonTweeterLogin=findViewById(R.id.buttonTweeterLogin);
         prefs = getSharedPreferences("UserData", MODE_PRIVATE);



        googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient= GoogleSignIn.getClient(this,googleSignInOptions);
        buttonGoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        buttonTweeterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i=new Intent(loginActivity.this,TwitterLoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
            }
        });
        callbackManager = CallbackManager.Factory.create();
        buttonFbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginManager.getInstance().logInWithReadPermissions(loginActivity.this, Arrays.asList("public_profile"));
            }
        });



        buttonCallLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(loginActivity.this,PhoneNumberValidationActivity.class));
            finish();
            }
        });
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(loginActivity.this, "Login Via facebook success", Toast.LENGTH_SHORT).show();
                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.d("TAG",object.toString());
                                Log.d("TAG",response.toString());

                                try {
                                 String userId = object.getString("id");
                                   URL profilePicture = new URL("https://graph.facebook.com/" + userId + "/picture?width=500&height=500");



                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putString("logged_in", "1");  //save value
                                    editor.putString("userId", userId);  //save value
                                    editor.putString("imageUrl", profilePicture.toString());  //save value
                                    editor.commit();
                                onBackPressed();
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        //Here we put the requested fields to be returned from the JSONObject
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id, first_name, last_name, email, birthday, gender");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                    }


                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    private void signIn() {
        Intent intent=googleSignInClient.getSignInIntent();
        startActivityForResult(intent,1000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000){

               Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
               handleSignInResult(task);


        }else{
            Toast.makeText(this, "Try Again!!", Toast.LENGTH_SHORT).show();
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            if(completedTask.isSuccessful()) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("logged_in", "1");  //save value
               try {
                   editor.putString("imageUrl",account.getPhotoUrl().toString());  //save value
               }catch (Exception x){

               }
                editor.putString("userId",account.getId().toString());  //save value
                editor.commit();
                gotoProfile();
            }
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("TAG", "handleSignInResult: "+e.getMessage());
            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();

        }
    }

    private void gotoProfile(){
        onBackPressed();
        finish();
    }
}

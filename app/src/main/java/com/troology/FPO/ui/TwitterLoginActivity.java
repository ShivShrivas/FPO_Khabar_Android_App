package com.troology.FPO.ui;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.OAuthProvider;

public class TwitterLoginActivity extends loginActivity {
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth=FirebaseAuth.getInstance();
        OAuthProvider.Builder provider = OAuthProvider.newBuilder("twitter.com");
        provider.addCustomParameter("lang", "en");
        Task<AuthResult> pendingResultTask = firebaseAuth.getPendingAuthResult();
        if (pendingResultTask != null) {
            // There's something already here! Finish the sign-in for your user.
            pendingResultTask
                    .addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(TwitterLoginActivity.this, "Login Success full", Toast.LENGTH_SHORT).show();

                                        String userId = authResult.getUser().getProviderId();
                                        Uri imgUrl=authResult.getUser().getPhotoUrl();


                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putString("logged_in", "1");  //save value
                                        editor.putString("userId", userId);  //save value
                                        editor.putString("imageUrl", imgUrl.toString());  //save value
                                        editor.commit();
                                    Intent i=new Intent(TwitterLoginActivity.this,Settings_Page.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
                                        finish();

                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle failure.
                                    Log.d("TAG", "fail: "+e.getMessage());

                                    Toast.makeText(TwitterLoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
        } else {
            firebaseAuth
                    .startActivityForSignInWithProvider(/* activity= */ this, provider.build())
                    .addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(TwitterLoginActivity.this, "Login Success full", Toast.LENGTH_SHORT).show();

                                        String userId = authResult.getUser().getProviderId();
                                        Uri imgUrl=authResult.getUser().getPhotoUrl();


                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putString("logged_in", "1");  //save value
                                        editor.putString("userId", userId);  //save value
                                        editor.putString("imageUrl", imgUrl.toString());  //save value
                                        editor.commit();
                                    Intent i=new Intent(TwitterLoginActivity.this,Settings_Page.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
                                        finish();

                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(TwitterLoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d("TAG", "fail: "+e.getMessage());

                                }
                            });
        }

    }
}
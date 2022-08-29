package com.troology.farmermedia.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.troology.farmermedia.R;
import com.troology.farmermedia.RetofitImplementation.ApiService;
import com.troology.farmermedia.RetofitImplementation.RestClient;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValidateOTPPage extends AppCompatActivity {
Intent i;
String OTP,phone;
EditText editTextforOTP;
    SharedPreferences prefs;
    TextView mobileNumber;

Button veriFyOTPBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_otppage);
        editTextforOTP=findViewById(R.id.editTextforOTP);
        veriFyOTPBtn=findViewById(R.id.veriFyOTPBtn);
        mobileNumber=findViewById(R.id.mobileNumber);
        i=getIntent();
        OTP=i.getStringExtra("OTP");
        phone=i.getStringExtra("phone");
        prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        mobileNumber.setText(phone);

        veriFyOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObject jsonObject=new JsonObject();
                jsonObject.addProperty("number",phone);
                try {
                    jsonObject.addProperty("otp",Integer.parseInt(editTextforOTP.getText().toString()));
                    RestClient restClient=new RestClient();
                    ApiService apiService=restClient.getApiService();
                    Call<JsonObject> call=apiService.validateOTP(jsonObject);
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(ValidateOTPPage.this, ""+response.body().get("message"), Toast.LENGTH_SHORT).show();
                                JsonElement userregisterModel=response.body().get("userregister");
                                JsonObject jsonObject1= (JsonObject) userregisterModel;
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("logged_in", "1");  //save value
                                editor.putString("userMainId", jsonObject1.get("_id").toString());  //save value
                                editor.putString("userId", jsonObject1.get("_id").toString());  //save value
                                editor.commit();
                                startActivity(new Intent(ValidateOTPPage.this,Settings_Page.class));
                                finish();
                            }else{
                                Toast.makeText(ValidateOTPPage.this, "something went wrong", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });
                }catch (Exception e){
                    editTextforOTP.setError("Enter correct OTP!!");
                }


            }
        });




    }
}
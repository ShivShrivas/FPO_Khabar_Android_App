package com.troology.farmermedia.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.troology.farmermedia.R;
import com.troology.farmermedia.RetofitImplementation.ApiService;
import com.troology.farmermedia.RetofitImplementation.RestClient;
import com.google.gson.JsonObject;
import com.hbb20.CountryCodePicker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneNumberValidationActivity extends AppCompatActivity {
    CountryCodePicker countryCodePicker;
    Button button;

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_validation);
        countryCodePicker=findViewById(R.id.countryCodePicker);
        editText=findViewById(R.id.MOBILE);
        button=findViewById(R.id.button3);

        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String number=editText.getText().toString();
                    long num=Long.parseLong(number);
                    JsonObject jsonObject=new JsonObject();
                    jsonObject.addProperty("number",num);
                    Call<JsonObject> call=apiService.getOTP(jsonObject);
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(PhoneNumberValidationActivity.this, ""+response.body().get("message"), Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(PhoneNumberValidationActivity.this,ValidateOTPPage.class);
                                i.putExtra("OTP",response.body().get("Otp").toString());
                                i.putExtra("phone",String.valueOf(num));
                                startActivity(i);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });
                }catch (Exception e){
                    Toast.makeText(PhoneNumberValidationActivity.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
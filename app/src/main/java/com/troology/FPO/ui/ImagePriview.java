package com.troology.FPO.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.troology.FPO.R;

public class ImagePriview extends AppCompatActivity {
TextView textView;
ImageView imageView16,imageView17;
Intent i;
String Heading,ImgLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_priview);
        i=getIntent();
        ImgLink=i.getStringExtra("ImgLink");
        Heading=i.getStringExtra("Heading");

        imageView16=findViewById(R.id.imageView16);
        imageView17=findViewById(R.id.imageView17);
        textView=findViewById(R.id.textView);
        imageView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        textView.setText(Heading);
        Glide.with(imageView17).load(ImgLink).into(imageView17);

    }
}
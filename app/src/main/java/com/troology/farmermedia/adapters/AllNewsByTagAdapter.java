package com.troology.farmermedia.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.troology.farmermedia.R;
import com.troology.farmermedia.RetofitImplementation.ApiService;
import com.troology.farmermedia.RetofitImplementation.RestClient;
import com.troology.farmermedia.model.GetAllNewsByTagModel;
import com.troology.farmermedia.ui.ImagePriview;
import com.troology.farmermedia.ui.Main_Dashboard;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllNewsByTagAdapter extends PagerAdapter
{
    Context context;
    LayoutInflater layoutInflater;
    SharedPreferences prefs;
    int showingBottomLayout=1;
    int bookMark=1;
    String isUserLoggedIn;
    int textSize;
    String userId,token;
    int showingToolbar=0;
    int like=0;
    ArrayList<GetAllNewsByTagModel> arrayList;
    public AllNewsByTagAdapter(Context context, ArrayList<GetAllNewsByTagModel> arrayList, int textSize) {
        this.context=context;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList=arrayList;
        this.textSize=textSize;
    }



    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        LinearLayout fontSizeView=view.findViewById(R.id.fontSizeView);
        TextView textView5=view.findViewById(R.id.textView5);
        ImageView backArrow=view.findViewById(R.id.backArrow);
        ImageView likeImageView=view.findViewById(R.id.likeImageView);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Main_Dashboard.class));
                ((Activity)context).finish();
            }
        });
        fontSizeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (like==0){
                    likeImageView.setImageDrawable(context.getDrawable(R.drawable.ic_heart));
                    like++;
                }else{
                    likeImageView.setImageDrawable(context.getDrawable(R.drawable.ic_heart_red));
                    like--;
                }
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    if (showingToolbar==0){
                        view.findViewById(R.id.toolbar).setVisibility(View.generateViewId());

                        SlideDownToolbar(view.findViewById(R.id.toolbar),context);

                        showingToolbar=1;
                    }else{


                        SlideUpToolbar(view.findViewById(R.id.toolbar),context);
                        view.findViewById(R.id.toolbar).setVisibility(View.GONE);

                        showingToolbar=0;
                    }

                    if (showingBottomLayout==0){

                        SlideDownBottomLayout(view.findViewById(R.id.linearLayout7),context);
                        view.findViewById(R.id.linearLayout9).setEnabled(true);
                        view.findViewById(R.id.linearLayout7).setEnabled(false);

                        view.findViewById(R.id.linearLayout7).setVisibility(View.GONE);


                        showingBottomLayout=1;
                    }else{
                        view.findViewById(R.id.linearLayout7).setVisibility(View.VISIBLE);
                        view.findViewById(R.id.linearLayout9).setEnabled(false);
                        view.findViewById(R.id.linearLayout7).setEnabled(true);

                        SlideUpBottomLayout(view.findViewById(R.id.linearLayout7),context);
                        showingBottomLayout=0;
                    }

                    return true;
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("TEST", "Raw event: " + event.getAction() + ", (" + event.getRawX() + ", " + event.getRawY() + ")");
                gestureDetector.onTouchEvent(event);

                return true;
            }
        });
        return view == (ConstraintLayout) object;

    }

    private void SlideDownBottomLayout(View toolbar, Context context) {

        toolbar.startAnimation(AnimationUtils.loadAnimation(context,
                R.anim.slide_down_bottomlayout));
    }

    private void SlideUpBottomLayout(View toolbar, Context context) {
        toolbar.startAnimation(AnimationUtils.loadAnimation(context,
                R.anim.slide_up_bottonlayout));
    }

    public void SlideDownToolbar(View view, Context context)
    {
        view.startAnimation(AnimationUtils.loadAnimation(context,
                R.anim.slide_down_toolbar));
    }

    private void SlideUpToolbar(View view, Context context) {
        view.startAnimation(AnimationUtils.loadAnimation(context,
                R.anim.slide_up_toolbar));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=layoutInflater.inflate(R.layout.news_details_page_layout,container,false);
        prefs = context.getSharedPreferences("UserData", MODE_PRIVATE);
        isUserLoggedIn=prefs.getString("logged_in","");

        LinearLayout bookmark_ic=view.findViewById(R.id.bookmark_ic);
        userId=prefs.getString("userId","");
        token=prefs.getString("token","");

        view.findViewById(R.id.linearLayout7).setVisibility(View.GONE);
        view.findViewById(R.id.toolbar).setVisibility(View.GONE);
        this.showingBottomLayout=1;
        this.showingToolbar=0;
        ImageView bookmarkImage=view.findViewById(R.id.bookmarkImage);
        TextView textView5=view.findViewById(R.id.textView5);
        ImageView imageView2=view.findViewById(R.id.imageView2);
        ImageView imageView15=view.findViewById(R.id.imageView15);
        TextView textView18=view.findViewById(R.id.textView18);
        TextView textView25=view.findViewById(R.id.textView25);
        LinearLayout linearLayout9=view.findViewById(R.id.linearLayout9);
        LinearLayout shareLayout=view.findViewById(R.id.shareLayout);
        textView25.setText(arrayList.get(position).getHeading());
        textView5.setText(arrayList.get(position).getShortDescription());
        if (textSize==0){
            textView5.setTextSize(18);
        }else{
            textView5.setTextSize(26);
        }
        linearLayout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = arrayList.get(position).getLinkText();
                Intent defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER);
                try {
                    defaultBrowser.setData(Uri.parse(data));
                    context.startActivity(defaultBrowser);
                }catch (Exception e){
                    Toast.makeText(context, "url is not supported", Toast.LENGTH_SHORT).show();
                }

            }
        });
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        bookmark_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!userId.equals("") && !isUserLoggedIn.equals("0")){
                    if (bookMark==1){
                        bookmarkImage.setImageDrawable(context.getDrawable(R.drawable.ic_bi_bookmark_fill));
                        if (!token.equals("")){
                            Log.d("TAG", "onClick: kf"+token);
                            RestClient restClient=new RestClient();
                            ApiService apiService=restClient.getApiService();
                            JsonObject jsonObject=new JsonObject();
                            String uId=userId.replace("\"","");
                            jsonObject.addProperty("user_id",uId);
                            jsonObject.addProperty("News_id",arrayList.get(position).getId());
                            Log.d("TAG", "onClick: "+jsonObject);
                            String tokenId=token.replace("\"","");
                            Call<JsonObject> call=apiService.aadBookMark(tokenId,jsonObject);
                            call.enqueue(new Callback<JsonObject>() {
                                @Override
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    if (response.isSuccessful()){
                                        Toast.makeText(context, "Bookmark added successfully", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<JsonObject> call, Throwable t) {

                                }
                            });
                        }else{
                            Toast.makeText(context, "Token is not available", Toast.LENGTH_SHORT).show();
                        }
                        bookMark++;
                    }else{
                        bookmarkImage.setImageDrawable(context.getDrawable(R.drawable.ic_bi_bookmark));
                        Call<JsonObject> call=apiService.deleteBookMark(arrayList.get(position).getId());
                        call.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                Log.d("TAG", "onResponse: delete"+response.body());
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {

                            }
                        });
                        bookMark--;
                    }


                }else{
                    Toast.makeText(context, "please login first", Toast.LENGTH_SHORT).show();

                }
            }
        });
        shareLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, arrayList.get(position).getLinkText());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, arrayList.get(position).getHeading());
                context.startActivity(shareIntent);
            }
        });
        String newImgUrl=("http://fpokhabar.evalue8.info/"+ arrayList.get(position).getImage().toString().trim());
        String newUrl2=newImgUrl.replaceAll("\"","");
        Glide.with(imageView2).load(newUrl2).into(imageView2);
        Glide.with(imageView15).load(newUrl2).into(imageView15);

        textView18.setText(arrayList.get(position).getShortDescription());
        Log.d("Tag", "instantiateItem: "+newUrl2);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, ImagePriview.class);
                i.putExtra("ImgLink",newUrl2);
                i.putExtra("Heading",arrayList.get(position).getHeading());
                context.startActivity(new Intent(i));
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
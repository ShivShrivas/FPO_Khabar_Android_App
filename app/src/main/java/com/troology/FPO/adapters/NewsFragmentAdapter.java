package com.troology.FPO.adapters;

import android.content.Context;
import android.content.Intent;
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
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.troology.FPO.R;
import com.troology.FPO.model.GetAllLiveNewsModel;
import com.troology.FPO.ui.Main_Dashboard;

import java.util.ArrayList;

public class NewsFragmentAdapter extends PagerAdapter {
        Context context;
    private LayoutInflater layoutInflater;
    ArrayList<GetAllLiveNewsModel> arrayList;
    int showingBottomLayout=1;
    int showingToolbar=0;


    public NewsFragmentAdapter(Context context) {
        this.context=context;
    }

    @Override
    public int getCount() {
        return 50;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        view.findViewById(R.id.backArrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            context.startActivity(new Intent(context, Main_Dashboard.class));


            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    Log.d("TEST", "onDoubleTap");

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
                        view.findViewById(R.id.linearLayout7).setVisibility(View.GONE);

                        showingBottomLayout=1;
                    }else{
                        view.findViewById(R.id.linearLayout7).setVisibility(View.VISIBLE);

                      SlideUpBottomLayout(view.findViewById(R.id.linearLayout7),context);
                        showingBottomLayout=0;
                    }


                    return super.onDoubleTap(e);
                }

            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.d("TEST", "Raw event: " + event.getAction() + ", (" + event.getRawX() + ", " + event.getRawY() + ")");
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
        return (view==object);
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

//        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=LayoutInflater.from(context).inflate(R.layout.news_details_page_layout,container,false);

        view.findViewById(R.id.linearLayout7).setVisibility(View.GONE);
        view.findViewById(R.id.toolbar).setVisibility(View.GONE);
        this.showingBottomLayout=1;
        this.showingToolbar=0;
        TextView textView5=view.findViewById(R.id.textView5);
        ImageView imageView2=view.findViewById(R.id.imageView2);
        TextView textView18=view.findViewById(R.id.textView18);
        TextView textView25=view.findViewById(R.id.textView25);
        LinearLayout linearLayout9=view.findViewById(R.id.linearLayout9);
        textView25.setText(arrayList.get(position).getNewss().get(0).getHeading());
        textView5.setText(arrayList.get(position).getNewss().get(0).getShortDescription());
        linearLayout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = arrayList.get(position).getLink();
                Intent defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER);
                try {
                    defaultBrowser.setData(Uri.parse(data));
                    context.startActivity(defaultBrowser);
                }catch (Exception e){
                    Toast.makeText(context, "url is not supported", Toast.LENGTH_SHORT).show();
                }

            }
        });
        String newImgUrl=("http://fpokhabar.evalue8.info/"+ arrayList.get(position).getImage().toString().trim());
        String newUrl2=newImgUrl.replaceAll("\"","");
        Glide.with(imageView2).load(newUrl2).into(imageView2);

        textView18.setText(arrayList.get(position).getNewss().get(0).getShortDescription());
        Log.d("Tag", "instantiateItem: "+newUrl2);


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}

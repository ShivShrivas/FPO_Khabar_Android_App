package com.troology.farmermedia.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.troology.farmermedia.R;
import com.troology.farmermedia.ui.News_Details_Page;

import java.util.ArrayList;

public class StaggeredGridAdapter extends RecyclerView.Adapter<StaggeredGridAdapter.StaggerViewHolder>{
    Context context;
    ArrayList images;
    public StaggeredGridAdapter(Context context, ArrayList images) {
        this.context=context;
        this.images=images;
    }

    @NonNull
    @Override
    public StaggerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return  new StaggerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaggerViewHolder holder, int position) {
        try{
            String newImgUrl=("http://fpokhabar.evalue8.info/"+ images.get(position).toString().trim());
            Log.d("TAG", "onBindViewHolder: "+newImgUrl);
            String newUrl2=newImgUrl.replaceAll("\"","");
            Glide.with(holder.imageView).load(newUrl2).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, News_Details_Page.class));

                }
            });
        }catch (Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return images.size();
    }
    private void setFadeAnimation(View view) {
        Animation animeSlideup= AnimationUtils.loadAnimation(context,R.anim.slidepupanime);
        view.startAnimation(animeSlideup);
    }
    public class StaggerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public StaggerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView4);

        }
    }
}

package com.troology.farmermedia.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.troology.farmermedia.R;

import com.troology.farmermedia.model.GetAllTopicsModel;
import com.troology.farmermedia.ui.Topic_Wise_News_Page;

import java.util.ArrayList;

public class AllTopicsAdapter extends RecyclerView.Adapter<AllTopicsAdapter.AllTopicsViewHolder> {
    Context context;
    ArrayList<GetAllTopicsModel> arrayList=new ArrayList<>();
    public AllTopicsAdapter(Context context, ArrayList<GetAllTopicsModel> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public AllTopicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.all_topics_item_card,parent,false);
        return new AllTopicsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllTopicsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("TAG", "onBindViewHolder: "+arrayList.get(position).getTopicName().trim());
        holder.allToipcsItemtxt.setText(arrayList.get(position).getTopicName());
        Log.d("TAG", "onBindViewHolder: "+arrayList.get(position).getTopicName());
        String newImgUrl=("http://fpokhabar.evalue8.info/"+ arrayList.get(position).getImage().toString().trim());
        String newUrl2=newImgUrl.replaceAll("\"","");
        Glide.with(holder.allToipcsItemImg).load(newUrl2).into(holder.allToipcsItemImg);
        Log.d("Tag", "instantiateItem: "+newUrl2);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, Topic_Wise_News_Page.class);
                i.putExtra("Topic",arrayList.get(position).getTopicName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class AllTopicsViewHolder extends RecyclerView.ViewHolder{
        ImageView allToipcsItemImg;
        TextView allToipcsItemtxt;
        public AllTopicsViewHolder(@NonNull View itemView) {
            super(itemView);
            allToipcsItemtxt=itemView.findViewById(R.id.allToipcsItemtxt);
            allToipcsItemImg=itemView.findViewById(R.id.allToipcsItemImg);
        }
    }
}

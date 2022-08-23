package com.troology.FPO.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.troology.FPO.R;
import com.troology.FPO.model.GetAllLiveNewsModel;
import com.troology.FPO.ui.SearchedNewsItemPage;

import java.util.ArrayList;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchViewHolder> {
    Context context;
    ArrayList<GetAllLiveNewsModel> arrayList;
    public SearchNewsAdapter(Context context, ArrayList<GetAllLiveNewsModel> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recview_item_card,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try{
            holder.searchNewsHeadingText.setText(arrayList.get(position).getNewss().get(0).getHeading());
            String newImgUrl=("http://fpokhabar.evalue8.info/"+ arrayList.get(position).getImage().get(0).toString().trim());
            String newUrl2=newImgUrl.replaceAll("\"","");
            Glide.with(holder.searchNewsImageView).load(newUrl2).into(holder.searchNewsImageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context, SearchedNewsItemPage.class);
                    i.putExtra("id",arrayList.get(position).getId());
                    context.startActivity(i);
                }
            });
        }catch (Exception e){


        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        ImageView searchNewsImageView;
        TextView searchNewsHeadingText,timeStamp;
        public SearchViewHolder(@NonNull View itemView) {

            super(itemView);
            searchNewsHeadingText=itemView.findViewById(R.id.newsHeadingTxt);
            timeStamp=itemView.findViewById(R.id.timeStampTxt);
            searchNewsImageView=itemView.findViewById(R.id.newsImageSearch);
        }
    }
}

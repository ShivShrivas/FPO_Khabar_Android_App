package com.troology.FPO.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.troology.FPO.R;
import com.troology.FPO.model.MixNewsInsightModel;

public class InsightsPageInNewsAdapter extends RecyclerView.Adapter<InsightsPageInNewsAdapter.InsightItemViewHolder> {
    Context context;
    MixNewsInsightModel getAllInsightsModel;
    public InsightsPageInNewsAdapter(Context context, MixNewsInsightModel getAllInsightsModel) {
        this.context=context;
        this.getAllInsightsModel=getAllInsightsModel;

    }

    @NonNull
    @Override
    public InsightItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.insight_item_card,parent,false);
        view.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return new InsightItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InsightItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String newImgUrl=("http://fpokhabar.evalue8.info/"+ getAllInsightsModel.getImage().get(position+1));
        Log.d("TAG", "onBindViewHolder: "+newImgUrl);
        String newUrl2=newImgUrl.replaceAll("\"","");
//        Glide.with(holder.imageView5).load("https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80").into(holder.imageView5);
        Glide.with(holder.imageView5).load(newUrl2).placeholder(context.getDrawable(R.drawable.dummy)).into(holder.imageView5);
        holder.imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = getAllInsightsModel.getImageLink().get(position);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,getAllInsightsModel.getImageLink().get(position));
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, getAllInsightsModel.getImageLink().get(position));
                context.startActivity(shareIntent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(getItemCount()-1);
    }

    @Override
    public int getItemCount() {
        return getAllInsightsModel.getImage().size()-1;
    }

    public class InsightItemViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView5;
        public InsightItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView5=itemView.findViewById(R.id.imageView5);
        }
    }
}

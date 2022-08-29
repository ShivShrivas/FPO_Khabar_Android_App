package com.troology.farmermedia.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.troology.farmermedia.R;
import com.troology.farmermedia.ui.Insights_Page;

import java.util.ArrayList;

public class DasboardRecViewAdapterInsights extends RecyclerView.Adapter<DasboardRecViewAdapterInsights.DashBoardRecViewAdapter> {
    Context context;
    ArrayList<String> arrayList;
    public DasboardRecViewAdapterInsights(Context context, ArrayList<String> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public DashBoardRecViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dasboard_recview_itemcard_insights,parent,false);
        return new DashBoardRecViewAdapter(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DashBoardRecViewAdapter holder, int position) {
        String newImgUrl=("http://fpokhabar.evalue8.info/"+ arrayList.get(position).toString().trim());
        Log.d("TAG", "onBindViewHolder: "+newImgUrl);
        String newUrl2=newImgUrl.replaceAll("\"","");
        Glide.with(holder.imageView5).load(newUrl2).into(holder.imageView5);
        holder.imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, Insights_Page.class);
                i.putExtra("itemNo",position);
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DashBoardRecViewAdapter extends RecyclerView.ViewHolder{
        ImageView imageView5;
        public DashBoardRecViewAdapter(@NonNull View itemView) {
            super(itemView);
            imageView5=itemView.findViewById(R.id.insightsImage);
        }
    }
}

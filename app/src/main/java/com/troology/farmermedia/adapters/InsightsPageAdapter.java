package com.troology.farmermedia.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.troology.farmermedia.R;
import com.troology.farmermedia.model.GetAllInsightsModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class InsightsPageAdapter extends RecyclerView.Adapter<InsightsPageAdapter.InsightMainViewHolder> {
    Context context;
    ArrayList<GetAllInsightsModel> arrayList;
    InsightsPageItemsAdapter adapter;
    public   InsightsPageAdapter(Context context, ArrayList<GetAllInsightsModel> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public InsightMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.insight_all_item_page,parent,false);
        view.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return new InsightMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InsightMainViewHolder holder, int position) {
        adapter=new InsightsPageItemsAdapter(context,arrayList.get(position));
        holder.viewPagerAllItems.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(holder.tabLayout, holder.viewPagerAllItems, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) { }
        });
        tabLayoutMediator.attach();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class InsightMainViewHolder extends RecyclerView.ViewHolder {
        ViewPager2 viewPagerAllItems;
        TabLayout tabLayout ;

        public InsightMainViewHolder(@NonNull View itemView) {
            super(itemView);
           tabLayout = itemView.findViewById(R.id.tabDots);
            viewPagerAllItems=itemView.findViewById(R.id.viewPagerAllItems);

        }
    }
}
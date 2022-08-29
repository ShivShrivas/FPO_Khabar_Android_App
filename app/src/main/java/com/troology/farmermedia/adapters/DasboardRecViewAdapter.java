package com.troology.farmermedia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.troology.farmermedia.R;
import java.util.ArrayList;

public class DasboardRecViewAdapter extends RecyclerView.Adapter<DasboardRecViewAdapter.DashBoardRecViewAdapter> {
    Context context;
    ArrayList<String> arrayList;
    public DasboardRecViewAdapter(Context context, ArrayList<String> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public DashBoardRecViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dasboard_recview_itemcard,parent,false);
        return new DashBoardRecViewAdapter(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DashBoardRecViewAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DashBoardRecViewAdapter extends RecyclerView.ViewHolder{
        public DashBoardRecViewAdapter(@NonNull View itemView) {
            super(itemView);
        }
    }
}

package com.troology.FPO.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.troology.FPO.R;
import com.troology.FPO.model.GetAllLanguageModel;

import java.util.ArrayList;

import retrofit2.Callback;

public class AllLanguageAdapter extends RecyclerView.Adapter<AllLanguageAdapter.AllLanguageViewHolder> {
    Context  context;
    ArrayList<GetAllLanguageModel> arrayList;
    public AllLanguageAdapter(Context  context, ArrayList<GetAllLanguageModel> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public AllLanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.language_item_card,parent,false);
        return new AllLanguageViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AllLanguageViewHolder holder, int position) {
        holder.textView27.setText(arrayList.get(position).getLanguage());
        holder.textView28.setText(arrayList.get(position).getLanguageName());
        String newImgUrl=("http://fpokhabar.evalue8.info/"+ arrayList.get(position).getImage().trim());
        String newUrl2=newImgUrl.replaceAll("\"","");
        Glide.with(holder.langSymbHindi).load(newUrl2).into(holder.langSymbHindi);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class AllLanguageViewHolder extends RecyclerView.ViewHolder {
        TextView textView27,textView28;
        ImageView langSymbHindi;
        public AllLanguageViewHolder(@NonNull View itemView) {
            super(itemView);
            textView28=itemView.findViewById(R.id.textView28);
            textView27=itemView.findViewById(R.id.textView27);
            langSymbHindi=itemView.findViewById(R.id.langSymbHindi);
        }
    }
}

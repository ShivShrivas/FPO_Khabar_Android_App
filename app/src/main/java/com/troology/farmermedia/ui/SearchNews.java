package com.troology.farmermedia.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;


import com.troology.farmermedia.R;
import com.troology.farmermedia.RetofitImplementation.ApiService;
import com.troology.farmermedia.RetofitImplementation.RestClient;
import com.troology.farmermedia.adapters.SearchNewsAdapter;
import com.troology.farmermedia.model.GetAllLiveNewsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchNews extends AppCompatActivity {
SearchView searchBar;
RecyclerView recyclerViewSearhView;
ImageView backArrowSearch;
com.troology.farmermedia.adapters.SearchNewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news);
        searchBar=findViewById(R.id.searchBar);
        backArrowSearch=findViewById(R.id.backArrowSearch);
        searchBar.onActionViewExpanded();
        recyclerViewSearhView=findViewById(R.id.recyclerViewSearhView);
        backArrowSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (!newText.equals("") && !newText.isEmpty()){
                    getData(newText);
                }
                else {
                    recyclerViewSearhView.setAdapter(null);
                }
                if (adapter!=null){
                    adapter.notifyDataSetChanged();

                }
                return true;
            }
        });

    }

    private void getData(String newText) {
        recyclerViewSearhView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSearhView.setAdapter(null);
        RestClient restClient=new RestClient();
        ApiService apiService=restClient.getApiService();
        Call<ArrayList<GetAllLiveNewsModel>> call=apiService.searchNews(newText);
        call.enqueue(new Callback<ArrayList<GetAllLiveNewsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetAllLiveNewsModel>> call, Response<ArrayList<GetAllLiveNewsModel>> response) {
                Log.d("TAG", "onResponse: "+response.body());
                if (response.body()!=null) {

                    ArrayList<GetAllLiveNewsModel> arrayList = response.body();
                    ArrayList<GetAllLiveNewsModel> arrayList2 = new ArrayList<>();
                    for (int i=0;i<arrayList.size();i++){
                     try {
                         if (arrayList.get(i).getType().equals("News")){
                             arrayList2.add(arrayList.get(i));

                         }
                     }catch (Exception e){
                     }
                    }
                    adapter = new com.troology.farmermedia.adapters.SearchNewsAdapter(SearchNews.this, arrayList2);
                    recyclerViewSearhView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else Toast.makeText(SearchNews.this, "No news found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrayList<GetAllLiveNewsModel>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
    }

}
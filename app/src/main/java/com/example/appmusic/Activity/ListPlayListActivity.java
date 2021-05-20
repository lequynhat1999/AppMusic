package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appmusic.Adapter.ListPlayListAdapter;
import com.example.appmusic.Model.Playlist;
import com.example.appmusic.R;
import com.example.appmusic.Retrofit2.APIUtils;
import com.example.appmusic.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPlayListActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewListPlayList;
    ListPlayListAdapter listPlayListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_play_list);
        Mapping();
        init();
        GetData();
    }

    private void GetData() {
        DataClient dataClient = APIUtils.getData();
        Call<List<Playlist>> callback = dataClient.GetDataListPlayList();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> arrListPlayList = (ArrayList<Playlist>) response.body();
                listPlayListAdapter = new ListPlayListAdapter(ListPlayListActivity.this,arrListPlayList);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(ListPlayListActivity.this,2);
                recyclerViewListPlayList.setLayoutManager(gridLayoutManager);
                recyclerViewListPlayList.setAdapter(listPlayListAdapter);

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Log.d("1234",t.getMessage());
            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("PlayList");
        toolbar.setTitleTextColor(getResources().getColor(R.color.purple));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Mapping() {
        toolbar = findViewById(R.id.toolbarListPlayList);
        recyclerViewListPlayList = findViewById(R.id.recyclerViewListPlayList);

    }
}
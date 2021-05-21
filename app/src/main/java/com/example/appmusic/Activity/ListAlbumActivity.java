package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appmusic.Adapter.ListAlbumAdapter;
import com.example.appmusic.Model.Album;
import com.example.appmusic.R;
import com.example.appmusic.Retrofit2.APIUtils;
import com.example.appmusic.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAlbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewListAlbum;
    Toolbar toolbarAlbum;
    ListAlbumAdapter listAlbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_album);
        Mapping();
        init();
        GetData();
    }

    private void GetData() {
        DataClient dataClient = APIUtils.getData();
        Call<List<Album>> callback = dataClient.GetDataListAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> arrAlbum = (ArrayList<Album>) response.body();
                listAlbumAdapter = new ListAlbumAdapter(ListAlbumActivity.this,arrAlbum);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(ListAlbumActivity.this,2);
                recyclerViewListAlbum.setLayoutManager(gridLayoutManager);
                recyclerViewListAlbum.setAdapter(listAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbarAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarAlbum.setTitleTextColor(getResources().getColor(R.color.purple));
        getSupportActionBar().setTitle("Tất Cả Album");
        toolbarAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Mapping() {
        recyclerViewListAlbum = findViewById(R.id.recyclerViewListAlbum);
        toolbarAlbum = findViewById(R.id.toolbarAlbum);
    }
}
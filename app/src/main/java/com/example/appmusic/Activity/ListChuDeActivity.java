package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.appmusic.Adapter.ListChuDeAdapter;
import com.example.appmusic.Model.ChuDe;
import com.example.appmusic.R;
import com.example.appmusic.Retrofit2.APIUtils;
import com.example.appmusic.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListChuDeActivity extends AppCompatActivity {
    RecyclerView recyclerViewListChuDe;
    Toolbar toolbarListChuDe;
    ListChuDeAdapter listChuDeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chu_de);
        Mapping();
        init();
        GetData();
    }

    private void GetData() {
        DataClient dataClient = APIUtils.getData();
        Call<List<ChuDe>> callback = dataClient.GetDataListChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> arrChuDe = (ArrayList<ChuDe>) response.body();
                listChuDeAdapter = new ListChuDeAdapter(ListChuDeActivity.this,arrChuDe);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(ListChuDeActivity.this,1);
                recyclerViewListChuDe.setLayoutManager(gridLayoutManager);
                recyclerViewListChuDe.setAdapter(listChuDeAdapter);

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbarListChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ đề");
        toolbarListChuDe.setTitleTextColor(getResources().getColor(R.color.purple));
        toolbarListChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Mapping() {
        recyclerViewListChuDe = findViewById(R.id.recyclerViewListChuDe);
        toolbarListChuDe = findViewById(R.id.toolbarListChuDe);
    }
}
package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appmusic.Adapter.ListTheLoaiTheoChuDeAdapter;
import com.example.appmusic.Model.ChuDe;
import com.example.appmusic.Model.TheLoai;
import com.example.appmusic.R;
import com.example.appmusic.Retrofit2.APIUtils;
import com.example.appmusic.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTheLoaiTheoChuDeActivity extends AppCompatActivity {
    ChuDe chude;
    TheLoai theloai;
    Toolbar toolbarListTheLoaiTheoChuDe;
    RecyclerView recyclerViewListTheLoaiTHeoChuDe;
    ListTheLoaiTheoChuDeAdapter listTheLoaiTheoChuDeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_the_loai_theo_chu_de);
        GetIntent();
        Mapping();
        init();
        GetData(chude.getIdChuDe());

    }

    private void GetData(String idchude) {
        DataClient dataClient = APIUtils.getData();
        Call<List<TheLoai>> callback = dataClient.GetDataTheLoaiTheoChuDe(idchude);
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> arrTheLoai = (ArrayList<TheLoai>) response.body();
                listTheLoaiTheoChuDeAdapter = new ListTheLoaiTheoChuDeAdapter(ListTheLoaiTheoChuDeActivity.this,arrTheLoai);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(ListTheLoaiTheoChuDeActivity.this,2);
                recyclerViewListTheLoaiTHeoChuDe.setLayoutManager(gridLayoutManager);
                recyclerViewListTheLoaiTHeoChuDe.setAdapter(listTheLoaiTheoChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbarListTheLoaiTheoChuDe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarListTheLoaiTheoChuDe.setTitleTextColor(getResources().getColor(R.color.purple));
        getSupportActionBar().setTitle("Chủ Đề " + chude.getTenChuDe());
        toolbarListTheLoaiTheoChuDe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void Mapping() {
        toolbarListTheLoaiTheoChuDe = findViewById(R.id.toolbarListTheLoaiTheoChuDe);
        recyclerViewListTheLoaiTHeoChuDe = findViewById(R.id.recyclerViewListTheLoaiTHeoChuDe);
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("chude"))
        {
            chude = (ChuDe) intent.getSerializableExtra("chude");
        }
    }
}
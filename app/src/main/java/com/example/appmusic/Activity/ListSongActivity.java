package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appmusic.Adapter.DanhSachBaiHatAdapter;
import com.example.appmusic.Model.Quangcao;
import com.example.appmusic.Model.WhiteList;
import com.example.appmusic.R;
import com.example.appmusic.Retrofit2.APIUtils;
import com.example.appmusic.Retrofit2.DataClient;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongActivity extends AppCompatActivity {
    Quangcao quangcao;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewListSong;
    FloatingActionButton floatingActionButton;
    ImageView imageViewListSong;
    ArrayList<WhiteList> arrSong;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        Mapping();
        DataIntent();
        init();
        if(quangcao != null && !quangcao.getTenBaiHat().equals(""))
        {
            setValueInView(quangcao.getTenBaiHat(),quangcao.getHinhAnh());
            GetDataQuangCao(quangcao.getIdQuangCao());
        }
    }

    private void GetDataQuangCao(String idquangcao) {
        DataClient dataClient = APIUtils.getData();
        Call<List<WhiteList>> callback = dataClient.GetDataDanhSachBaiHatTheoQuangCao(idquangcao);
        callback.enqueue(new Callback<List<WhiteList>>() {
            @Override
            public void onResponse(Call<List<WhiteList>> call, Response<List<WhiteList>> response) {
                arrSong = (ArrayList<WhiteList>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(ListSongActivity.this,arrSong);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListSongActivity.this);
                recyclerViewListSong.setLayoutManager(linearLayoutManager);
                recyclerViewListSong.setAdapter(danhSachBaiHatAdapter);


            }

            @Override
            public void onFailure(Call<List<WhiteList>> call, Throwable t) {
            }
        });
    }

    private void setValueInView(String name, String img) {
        collapsingToolbarLayout.setTitle(name); // gán giá trị của tên bài hát
        try {
            URL url = new URL(img); // do đường dẫn đang dưới dạng url nên phải convert về dạng bitmap mới gán dữ liệu được
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap); // convert về dạng bitmap drawable
            collapsingToolbarLayout.setBackground(bitmapDrawable); // gắn layout vào collapsingToolbarLayout

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(img).into(imageViewListSong);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // hiển thị nút back
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT); // hiển thị text bg
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.TRANSPARENT); // hiển thị text khi kéo view lên

    }

    private void Mapping() {
        coordinatorLayout = findViewById(R.id.coordinatorListSong);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        toolbar = findViewById(R.id.toolbarList);
        recyclerViewListSong = findViewById(R.id.recyclerViewListSong);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        imageViewListSong = findViewById(R.id.imageViewListSong);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent != null)
        {
            if(intent.hasExtra("banner"))
            {
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
            }
        }
    }
}
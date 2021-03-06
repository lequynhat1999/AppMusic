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
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appmusic.Adapter.DanhSachBaiHatAdapter;
import com.example.appmusic.Model.Album;
import com.example.appmusic.Model.Playlist;
import com.example.appmusic.Model.Quangcao;
import com.example.appmusic.Model.TheLoai;
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
    Playlist playlist;
    TheLoai theLoai;
    Album album;
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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // ki???m tra k???t n???i m???ng
        StrictMode.setThreadPolicy(policy);
        Mapping();
        DataIntent();
        init();
        if(quangcao != null && !quangcao.getTenBaiHat().equals(""))
        {
            setValueInView(quangcao.getTenBaiHat(),quangcao.getHinhAnh());
            GetDataQuangCao(quangcao.getIdQuangCao());
        }

        if(playlist != null && !playlist.getTen().equals(""))
        {
            setValueInView(playlist.getTen(),playlist.getHinhPlayList());
            GetDataPlayList(playlist.getIdPlayList());
        }

        if(theLoai !=null && !theLoai.getTenTheLoai().equals(""))
        {
            setValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
        }

        if(album !=null && !album.getTenAlbum().equals(""))
        {
            setValueInView(album.getTenAlbum(),album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
    }

    private void GetDataAlbum(String idalbum) {
        DataClient dataClient = APIUtils.getData();
        Call<List<WhiteList>> callback = dataClient.GetDataDanhSachBaiHatTheoAlbum(idalbum);
        callback.enqueue(new Callback<List<WhiteList>>() {
            @Override
            public void onResponse(Call<List<WhiteList>> call, Response<List<WhiteList>> response) {
                arrSong = (ArrayList<WhiteList>) response.body();
                for (int i = 0; i < arrSong.size() ; i++) {
                    String[] strings = arrSong.get(i).getLinkBaiHat().split("%20");
                    arrSong.get(i).setLinkBaiHat(strings[0]);
                }
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(ListSongActivity.this,arrSong);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListSongActivity.this);
                recyclerViewListSong.setLayoutManager(linearLayoutManager);
                recyclerViewListSong.setAdapter(danhSachBaiHatAdapter);
                eventClick();

            }

            @Override
            public void onFailure(Call<List<WhiteList>> call, Throwable t) {

            }
        });
    }

    private void GetDataTheLoai(String idtheloai) {
        DataClient dataClient = APIUtils.getData();
        Call<List<WhiteList>> callback = dataClient.GetDataDanhSachBaiHatTheoTheLoai(idtheloai);
        callback.enqueue(new Callback<List<WhiteList>>() {
            @Override
            public void onResponse(Call<List<WhiteList>> call, Response<List<WhiteList>> response) {
                arrSong = (ArrayList<WhiteList>) response.body();
                for (int i = 0; i < arrSong.size() ; i++) {
                    String[] strings = arrSong.get(i).getLinkBaiHat().split("%20");
                    arrSong.get(i).setLinkBaiHat(strings[0]);
                }
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(ListSongActivity.this,arrSong);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListSongActivity.this);
                recyclerViewListSong.setLayoutManager(linearLayoutManager);
                recyclerViewListSong.setAdapter(danhSachBaiHatAdapter);
                eventClick();

            }

            @Override
            public void onFailure(Call<List<WhiteList>> call, Throwable t) {
            }
        });
    }

    private void GetDataPlayList(String idplaylist) {
        DataClient dataClient = APIUtils.getData();
        Call<List<WhiteList>> callback = dataClient.GetDataDanhSachBaiHatTheoPlayList(idplaylist);
        callback.enqueue(new Callback<List<WhiteList>>() {
            @Override
            public void onResponse(Call<List<WhiteList>> call, Response<List<WhiteList>> response) {
                arrSong = (ArrayList<WhiteList>) response.body();
                for (int i = 0; i < arrSong.size() ; i++) {
                    String[] strings = arrSong.get(i).getLinkBaiHat().split("%20");
                    arrSong.get(i).setLinkBaiHat(strings[0]);
                }
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(ListSongActivity.this,arrSong);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListSongActivity.this);
                recyclerViewListSong.setLayoutManager(linearLayoutManager);
                recyclerViewListSong.setAdapter(danhSachBaiHatAdapter);
                eventClick();


            }

            @Override
            public void onFailure(Call<List<WhiteList>> call, Throwable t) {

            }
        });
    }

    private void GetDataQuangCao(String idquangcao) {
        DataClient dataClient = APIUtils.getData();
        Call<List<WhiteList>> callback = dataClient.GetDataDanhSachBaiHatTheoQuangCao(idquangcao);
        callback.enqueue(new Callback<List<WhiteList>>() {
            @Override
            public void onResponse(Call<List<WhiteList>> call, Response<List<WhiteList>> response) {
                arrSong = (ArrayList<WhiteList>) response.body();
                for (int i = 0; i < arrSong.size() ; i++) {
                    String[] strings = arrSong.get(i).getLinkBaiHat().split("%20");
                    arrSong.get(i).setLinkBaiHat(strings[0]);
                }
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(ListSongActivity.this,arrSong);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListSongActivity.this);
                recyclerViewListSong.setLayoutManager(linearLayoutManager);
                recyclerViewListSong.setAdapter(danhSachBaiHatAdapter);
                eventClick();



            }

            @Override
            public void onFailure(Call<List<WhiteList>> call, Throwable t) {
            }
        });
    }

    private void setValueInView(String name, String img) {
        collapsingToolbarLayout.setTitle(name); // g??n gi?? tr??? c???a t??n b??i h??t
        try {
            URL url = new URL(img); // do ???????ng d???n ??ang d?????i d???ng url n??n ph???i convert v??? d???ng bitmap m???i g??n d??? li???u ???????c
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap); // convert v??? d???ng bitmap drawable
            collapsingToolbarLayout.setBackground(bitmapDrawable); // g???n layout v??o collapsingToolbarLayout

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(img).into(imageViewListSong);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // hi???n th??? n??t back
        toolbar.setTitleTextColor(getResources().getColor(R.color.purple));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT); // hi???n th??? text bg
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.TRANSPARENT); // hi???n th??? text khi k??o view l??n
        floatingActionButton.setEnabled(false);

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

            if (intent.hasExtra("itemPlaylist"))
            {
                playlist = (Playlist) intent.getSerializableExtra("itemPlaylist");
            }

            if(intent.hasExtra("idtheloai"))
            {
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }

            if(intent.hasExtra("album"))
            {
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }

    private  void eventClick()
    {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListSongActivity.this,PlayMusicActivity.class);
                intent.putExtra("cacbaihat",arrSong);
                startActivity(intent);
            }
        });
    }
}
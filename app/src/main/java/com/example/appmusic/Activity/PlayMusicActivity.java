package com.example.appmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmusic.Model.WhiteList;
import com.example.appmusic.R;

import java.util.ArrayList;

public class PlayMusicActivity extends AppCompatActivity {
    Toolbar toolbarPlayMusic;
    TextView textViewTimeSong,textViewTotalTimeSong;
    SeekBar seekBarSong;
    ImageButton imageBtnShuffle,imageBtnPre,imageBtnPlay,imageBtnNext,imageBtnRepeat;
    ViewPager viewPagerPlayMusic;
    public static ArrayList<WhiteList> arrSong = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        Mapping();
        init();
        GetDataFromIntent();



    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        arrSong.clear();

        if(intent != null)
        {
            if(intent.hasExtra("playmusic"))
            {
                WhiteList whiteList = intent.getParcelableExtra("playmusic");
                arrSong.add(whiteList);
            }

            if(intent.hasExtra("cacbaihat"))
            {
                ArrayList<WhiteList> arrListSong = intent.getParcelableArrayListExtra("cacbaihat");
                arrSong = arrListSong;
            }
        }


    }

    private void init() {
        setSupportActionBar(toolbarPlayMusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayMusic.setTitleTextColor(getResources().getColor(R.color.white));
        toolbarPlayMusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Mapping() {
        toolbarPlayMusic = findViewById(R.id.toolbarPlayMusic);
        textViewTimeSong = findViewById(R.id.textViewTimeSong);
        textViewTotalTimeSong = findViewById(R.id.textViewTotalTimeSong);
        seekBarSong = findViewById(R.id.seekBarSong);
        imageBtnShuffle = findViewById(R.id.imageBtnShuffle);
        imageBtnPre = findViewById(R.id.imageBtnPre);
        imageBtnPlay = findViewById(R.id.imageBtnPlay);
        imageBtnNext = findViewById(R.id.imageBtnNext);
        imageBtnRepeat = findViewById(R.id.imageBtnRepeat);
        viewPagerPlayMusic = findViewById(R.id.viewPagerPlayMusic);
    }
}
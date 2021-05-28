package com.example.appmusic.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmusic.Adapter.ViewPagerPlayListMusic;
import com.example.appmusic.Fragment.FragmentDiaNhac;
import com.example.appmusic.Fragment.FragmentPlayListMusic;
import com.example.appmusic.Fragment.FragmentPlaylist;
import com.example.appmusic.Model.WhiteList;
import com.example.appmusic.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {
    Toolbar toolbarPlayMusic;
    TextView textViewTimeSong,textViewTotalTimeSong;
    SeekBar seekBarSong;
    ImageButton imageBtnShuffle,imageBtnPre,imageBtnPlay,imageBtnNext,imageBtnRepeat;
    ViewPager viewPagerPlayMusic;
    public static ArrayList<WhiteList> arrSong = new ArrayList<>();
    public static ViewPagerPlayListMusic viewPagerPlayListMusic;
    FragmentDiaNhac fragmentDiaNhac;
    FragmentPlayListMusic fragmentPlayListMusic;
    MediaPlayer mediaPlayer;
    int position = 0; // bắt vị trí bài hát để pre or next
    boolean repeat = false; // lặp lại
    boolean checkRandom = false; // giá trị random
    boolean next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // kiểm tra kết nối mạng
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        Mapping();
        init();
        eventClick();



    }

    private void eventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(viewPagerPlayListMusic.getItem(1) != null)
                {
                    if (arrSong.size() > 0)
                    {
                        fragmentDiaNhac.PlayNhac(arrSong.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }
                    else
                    {
                        handler.postDelayed(this,100);
                    }
                }
            }
        },100);

        imageBtnPlay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    imageBtnPlay.setImageResource(R.drawable.iconplay);
                    if (fragmentDiaNhac.objectAnimator!=null){
                        fragmentDiaNhac.objectAnimator.pause();
                    }
                }
                else
                {
                    mediaPlayer.start();
                    imageBtnPlay.setImageResource(R.drawable.iconpause);
                    if (fragmentDiaNhac.objectAnimator!=null){
                        fragmentDiaNhac.objectAnimator.resume();
                    }
                }

            }
        });


        imageBtnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false)
                {
                    if (checkRandom == true)
                    {
                        checkRandom = false;
                        imageBtnRepeat.setImageResource(R.drawable.iconsyned);
                        imageBtnShuffle.setImageResource(R.drawable.iconsuffle);
                    }
                    imageBtnRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }
                else
                {
                    imageBtnRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        imageBtnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkRandom == false)
                {
                    if (repeat == true)
                    {
                        repeat = false;
                        imageBtnShuffle.setImageResource(R.drawable.iconshuffled);
                        imageBtnRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    checkRandom = true;
                    imageBtnShuffle.setImageResource(R.drawable.iconshuffled);
                }
                else
                {
                    imageBtnShuffle.setImageResource(R.drawable.iconsuffle);
                    checkRandom = false;
                }
            }
        });

        seekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { //kéo đến đâu thả ra sẽ nhận giá trị
                mediaPlayer.seekTo(seekBarSong.getProgress());
            }
        });

        imageBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrSong.size() > 0)
                {
                    if(mediaPlayer.isPlaying() || mediaPlayer != null)
                    {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (arrSong.size()))
                    {
                        imageBtnPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat == true)
                        {
                            if (position == 0)
                            {
                                position = arrSong.size();
                            }
                            position -= 1;
                        }
                        if (checkRandom == true)
                        {
                            Random random = new Random();
                            int index = random.nextInt(arrSong.size());
                            if (index == position)
                            {
                                position = index -1;
                            }
                            position = index;
                        }
                        if (position > (arrSong.size()-1)  )
                        {
                            position = 0;
                        }
                        new PlayMp3().execute(arrSong.get(position).getLinkBaiHat());
                        fragmentDiaNhac.PlayNhac(arrSong.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(arrSong.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imageBtnPre.setClickable(false);
                imageBtnNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageBtnPre.setClickable(true);
                        imageBtnNext.setClickable(true);
                    }
                },2000);
            }
        });
//
        imageBtnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrSong.size() > 0)
                {
                    if(mediaPlayer.isPlaying() || mediaPlayer != null)
                    {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }

                    if (position < (arrSong.size()))
                    {
                        imageBtnPlay.setImageResource(R.drawable.iconpause);
                        position--;

                        if (position < 0)
                        {
                            position = arrSong.size() - 1 ;
                        }

                        if (repeat == true)
                        {
                            position += 1;
                        }

                        if (checkRandom == true)
                        {
                            Random random = new Random();
                            int index = random.nextInt(arrSong.size());
                            if (index == position)
                            {
                                position = index -1;
                            }
                            position = index;
                        }

                        new PlayMp3().execute(arrSong.get(position).getLinkBaiHat());
                        fragmentDiaNhac.PlayNhac(arrSong.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(arrSong.get(position).getTenBaiHat());
                        UpdateTime();
                    }

                }
                imageBtnPre.setClickable(false);
                imageBtnNext.setClickable(false);
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageBtnPre.setClickable(true);
                        imageBtnNext.setClickable(true);
                    }
                },2000);
            }
        });

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
                mediaPlayer.stop();
                arrSong.clear();
            }
        });
        fragmentDiaNhac = new FragmentDiaNhac();
        fragmentPlayListMusic = new FragmentPlayListMusic();

        viewPagerPlayListMusic = new ViewPagerPlayListMusic(getSupportFragmentManager());
        viewPagerPlayListMusic.AddFragment(fragmentPlayListMusic);
        viewPagerPlayListMusic.AddFragment(fragmentDiaNhac);
        viewPagerPlayMusic.setAdapter(viewPagerPlayListMusic);

        fragmentDiaNhac = (FragmentDiaNhac) viewPagerPlayListMusic.getItem(1);

        if(arrSong.size() > 0)
        {
            getSupportActionBar().setTitle(arrSong.get(0).getTenBaiHat());
            new PlayMp3().execute(arrSong.get(0).getLinkBaiHat());
            imageBtnPlay.setImageResource(R.drawable.iconpause);
        }
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

    class PlayMp3 extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {

            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC); // play ca khúc dưới dạng online
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat); // khởi tạo dữ liệu từ đường link của ca khúc
                mediaPlayer.prepare(); // gọi hàm này để chuẩn bị cho việc play
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration())); // tổng thời gian của bài nhạc
        seekBarSong.setMax(mediaPlayer.getDuration()); //cập nhật cho seekbar

    }

    private void UpdateTime()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null)
                {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    textViewTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    seekBarSong.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(this,300); // 0,3s cập nhật timeSong lại 1 lần
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // hàm lắng nghe khi media đã chạy hết
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);

        // khi chạy hết bài, sleep 1s xong chuyển sang handler1
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true)
                {
                    if (position < (arrSong.size()))
                    {
                        imageBtnPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat == true)
                        {
                            if (position == 0)
                            {
                                position = arrSong.size();
                            }
                            position -= 1;
                        }
                        if (checkRandom == true)
                        {
                            Random random = new Random();
                            int index = random.nextInt(arrSong.size());
                            if (index == position)
                            {
                                position = index -1;
                            }
                            position = index;
                        }
                        if (position > (arrSong.size()-1)  )
                        {
                            position = 0;
                        }
                        new PlayMp3().execute(arrSong.get(position).getLinkBaiHat());
                        fragmentDiaNhac.PlayNhac(arrSong.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(arrSong.get(position).getTenBaiHat());

                    }

                    imageBtnPre.setClickable(false);
                    imageBtnNext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageBtnPre.setClickable(true);
                            imageBtnNext.setClickable(true);
                        }
                    },2000);
                    next = false;
                    handler1.removeCallbacks(this); // xoá sự kiện lắng nghe này để tạo ra thread mới

                }
                else
                {
                    handler1.postDelayed(this,1000); // khi chưa hết bài, gọi lại postDeplay, lắng nghe đến khi nào next == true thì nhảy lên if
                }
            }
        },1000);
    }

}
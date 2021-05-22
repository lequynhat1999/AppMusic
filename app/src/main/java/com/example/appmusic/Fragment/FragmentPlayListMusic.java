package com.example.appmusic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.PlayMusicActivity;
import com.example.appmusic.Adapter.PlayListMusicAdapter;
import com.example.appmusic.R;

public class FragmentPlayListMusic extends Fragment {
    View view;
    RecyclerView recyclerViewListMusic;
    PlayListMusicAdapter playListMusicAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_list_music,container,false);
        recyclerViewListMusic = view.findViewById(R.id.recyclerViewListMusic);
        if (PlayMusicActivity.arrSong.size() > 0)
        {
            playListMusicAdapter = new PlayListMusicAdapter(getActivity(), PlayMusicActivity.arrSong);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerViewListMusic.setLayoutManager(linearLayoutManager);
            recyclerViewListMusic.setAdapter(playListMusicAdapter);

        }

        return view;
    }
}

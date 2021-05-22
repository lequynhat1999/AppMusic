package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Model.WhiteList;
import com.example.appmusic.R;

import java.util.ArrayList;

public class PlayListMusicAdapter extends  RecyclerView.Adapter<PlayListMusicAdapter.ViewHolder> {
    Context context;
    ArrayList<WhiteList> arrWhiteList;

    public PlayListMusicAdapter(Context context, ArrayList<WhiteList> arrWhiteList) {
        this.context = context;
        this.arrWhiteList = arrWhiteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_play_list_music,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WhiteList whiteList = arrWhiteList.get(position);
        holder.textViewIndexListMusic.setText(position + 1 + "");
        holder.textViewNameMusicalListMusic.setText(whiteList.getCaSi());
        holder.textViewNameSongListMusic.setText(whiteList.getTenBaiHat());
    }

    @Override
    public int getItemCount() {
        return arrWhiteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewIndexListMusic,textViewNameSongListMusic,textViewNameMusicalListMusic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIndexListMusic = itemView.findViewById(R.id.textViewIndexListMusic);
            textViewNameSongListMusic = itemView.findViewById(R.id.textViewNameSongListMusic);
            textViewNameMusicalListMusic = itemView.findViewById(R.id.textViewNameMusicalListMusic);

        }
    }
}

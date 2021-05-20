package com.example.appmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.ListSongActivity;
import com.example.appmusic.Model.Playlist;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListPlayListAdapter extends RecyclerView.Adapter<ListPlayListAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> arrPlayList;

    public ListPlayListAdapter(Context context, ArrayList<Playlist> arrPlayList) {
        this.context = context;
        this.arrPlayList = arrPlayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_list_play_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = arrPlayList.get(position);
        Picasso.with(context).load(playlist.getHinhPlayList()).into(holder.imageViewListPlayList);
        holder.texViewNameListPlayList.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return arrPlayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewListPlayList;
        TextView texViewNameListPlayList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewListPlayList = itemView.findViewById(R.id.imageViewListPlayList);
            texViewNameListPlayList = itemView.findViewById(R.id.texViewNameListPlayList);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("itemPlaylist",arrPlayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

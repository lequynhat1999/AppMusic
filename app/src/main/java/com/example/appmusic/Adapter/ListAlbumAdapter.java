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
import com.example.appmusic.Model.Album;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAlbumAdapter extends RecyclerView.Adapter<ListAlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> arrAlbum;

    public ListAlbumAdapter(Context context, ArrayList<Album> arrAlbum) {
        this.context = context;
        this.arrAlbum = arrAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_list_album,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = arrAlbum.get(position);
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imageViewListAlbum);
        holder.texViewNameListAlbum.setText(album.getTenAlbum());
    }

    @Override
    public int getItemCount() {
        return arrAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageViewListAlbum;
        TextView texViewNameListAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewListAlbum = itemView.findViewById(R.id.imageViewListAlbum);
            texViewNameListAlbum = itemView.findViewById(R.id.texViewNameListAlbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("album",arrAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

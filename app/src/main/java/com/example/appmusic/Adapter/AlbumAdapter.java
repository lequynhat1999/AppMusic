package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Model.Album;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> arrAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> arrAlbum) {
        this.context = context;
        this.arrAlbum = arrAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //func dùng để gắn layout, gán giá trị
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_album,parent,false); // gán layout và thực hiện gán giá trị


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {// gán giá trị
        Album album = arrAlbum.get(position);
        holder.textViewNameMusicalAlbum.setText("Ca sĩ thực hiện: " + album.getTenCaSiAlbum());
        holder.textViewNameAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imageViewAlbum);

    }

    @Override
    public int getItemCount() { // trả về số item
        return arrAlbum.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageViewAlbum;
        TextView textViewNameAlbum,textViewNameMusicalAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAlbum = itemView.findViewById(R.id.imageViewAlbum);
            textViewNameAlbum = itemView.findViewById(R.id.textViewNameAlbum);
            textViewNameMusicalAlbum = itemView.findViewById(R.id.textViewNameMusicalAlbum);

        }
    }
}

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

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> { // trong adapter truyền vào class chứa giá trị ánh xạ của viewHolder
    Context context;
    ArrayList<Album> arrAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> arrAlbum) {
        this.context = context;
        this.arrAlbum = arrAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //func dùng để gắn layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_album,parent,false); // gán layout


        return new ViewHolder(view); // sau khi trả về biến view, biến view này chính là itemView trong ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {// sau khi thực hiện ánh xạ các view, thì gán giá trị cho view
        Album album = arrAlbum.get(position);
        holder.textViewNameMusicalAlbum.setText("Ca sĩ thực hiện: " + album.getTenCaSiAlbum()); // view được giữ trong viewHolder, nên khi muốn lấy ra sd thì gọi holder
        holder.textViewNameAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imageViewAlbum);

    }

    @Override
    public int getItemCount() { // trả về số item
        return arrAlbum.size() ;
    }

    // trong listView có class ViewHolder để giữ lại các ánh xạ, trong recyclerView những view này phải nằm trong ViewHolder để thực hiện gán ánh xạ
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageViewAlbum;
        TextView textViewNameAlbum,textViewNameMusicalAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAlbum = itemView.findViewById(R.id.imageViewAlbum);
            textViewNameAlbum = itemView.findViewById(R.id.textViewNameAlbum);
            textViewNameMusicalAlbum = itemView.findViewById(R.id.textViewNameMusicalAlbum);
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

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
import com.example.appmusic.Model.TheLoai;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<ListTheLoaiTheoChuDeAdapter.ViewHolder> {
    Context context;
    ArrayList<TheLoai> arrTheLoai;

    public ListTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> arrTheLoai) {
        this.context = context;
        this.arrTheLoai = arrTheLoai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_list_the_loai_theo_chu_de,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = arrTheLoai.get(position);
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imageViewListTheLoaiTheoChuDe);
        holder.textViewNameTheLoaiTheoChuDe.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return arrTheLoai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageViewListTheLoaiTheoChuDe;
        TextView textViewNameTheLoaiTheoChuDe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewListTheLoaiTheoChuDe = itemView.findViewById(R.id.imageViewListTheLoaiTheoChuDe);
            textViewNameTheLoaiTheoChuDe = itemView.findViewById(R.id.textViewNameTheLoaiTheoChuDe);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("idtheloai",arrTheLoai.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

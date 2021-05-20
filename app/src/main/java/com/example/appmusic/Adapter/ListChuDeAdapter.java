package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Model.ChuDe;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListChuDeAdapter extends  RecyclerView.Adapter<ListChuDeAdapter.ViewHolder> {
    Context context;
    ArrayList<ChuDe> arrChuDe;

    public ListChuDeAdapter(Context context, ArrayList<ChuDe> arrChuDe) {
        this.context = context;
        this.arrChuDe = arrChuDe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_list_chu_de,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = arrChuDe.get(position);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.imageViewListChuDe);
    }

    @Override
    public int getItemCount() {
        return arrChuDe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageViewListChuDe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewListChuDe = itemView.findViewById(R.id.imageViewListChuDe);

        }
    }
}

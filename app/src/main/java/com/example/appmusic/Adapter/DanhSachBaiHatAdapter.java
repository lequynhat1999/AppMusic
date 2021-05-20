package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Model.WhiteList;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<WhiteList> arrWhiteList;

    public DanhSachBaiHatAdapter(Context context, ArrayList<WhiteList> arrWhiteList) {
        this.context = context;
        this.arrWhiteList = arrWhiteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // khởi tạo ra RecyclerView
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_danh_sach_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WhiteList whiteList = arrWhiteList.get(position);
        holder.textViewNameMusical.setText(whiteList.getCaSi());
        holder.textViewNameSong.setText(whiteList.getTenBaiHat());
        holder.textViewDanhSachInDex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return arrWhiteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewDanhSachInDex,textViewNameSong,textViewNameMusical;
        ImageView imageViewLuotThichDanhSachBaiHat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDanhSachInDex = itemView.findViewById(R.id.textViewDanhSachInDex);
            textViewNameMusical = itemView.findViewById(R.id.textViewNameMusical);
            textViewNameSong = itemView.findViewById(R.id.textViewNameSong);
            imageViewLuotThichDanhSachBaiHat = itemView.findViewById(R.id.imageViewLuotThichDanhSachBaiHat);
        }

    }
}

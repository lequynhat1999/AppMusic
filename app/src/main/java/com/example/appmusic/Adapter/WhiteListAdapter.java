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

public class WhiteListAdapter extends RecyclerView.Adapter<WhiteListAdapter.ViewHolder> {
    Context context;
    ArrayList<WhiteList> arrWhiteList;

    public WhiteListAdapter(Context context, ArrayList<WhiteList> arrWhiteList) {
        this.context = context;
        this.arrWhiteList = arrWhiteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.row_whitelist,parent,false); // biến view để định vị lại những view nằm trên layout này


        return new ViewHolder(view); // khởi tạo lại viewHolder để dùng lại những cái view đã ánh xạ bên trong
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WhiteList whiteList = arrWhiteList.get(position);
        holder.textViewNameWhiteList.setText(whiteList.getTenBaiHat());
        holder.textViewNameMusicalWhiteList.setText(whiteList.getCaSi());
        Picasso.with(context).load(whiteList.getHinhBaiHat()).into(holder.imageViewWhiteList);
    }

    @Override
    public int getItemCount() {
        return arrWhiteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewNameWhiteList,textViewNameMusicalWhiteList;
        ImageView imageViewWhiteList,imageViewLike;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNameWhiteList = itemView.findViewById(R.id.textViewNameWhiteList);
            textViewNameMusicalWhiteList = itemView.findViewById(R.id.textViewNameMusicalWhiteList);
            imageViewWhiteList = itemView.findViewById(R.id.imageViewWhiteList);
            imageViewLike = itemView.findViewById(R.id.imageViewLike);
        }
    }
}

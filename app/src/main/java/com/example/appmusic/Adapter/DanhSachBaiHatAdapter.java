package com.example.appmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusic.Activity.PlayMusicActivity;
import com.example.appmusic.Model.WhiteList;
import com.example.appmusic.R;
import com.example.appmusic.Retrofit2.APIUtils;
import com.example.appmusic.Retrofit2.DataClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

            imageViewLuotThichDanhSachBaiHat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageViewLuotThichDanhSachBaiHat.setImageResource(R.drawable.iconloved);
                    DataClient dataClient = APIUtils.getData();
                    Call<String> callback = dataClient.UpdateLike("1",arrWhiteList.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if(result.equals("Success"))
                            {
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
//                    imageViewLike.setEnabled(false);
                    imageViewLuotThichDanhSachBaiHat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageViewLuotThichDanhSachBaiHat.setImageResource(R.drawable.iconlove);
                            DataClient dataClient1 = APIUtils.getData();
                            Call<String> callback = dataClient1.UpdateDislike("1",arrWhiteList.get(getPosition()).getIdBaiHat());
                            callback.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    String result1 = response.body();
                                    if(result1.equals("success"))
                                    {
                                        Toast.makeText(context, "Đã bỏ thích", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });
                        }
                    });
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("playmusic",arrWhiteList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }
}

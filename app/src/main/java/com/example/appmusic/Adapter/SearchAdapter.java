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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    ArrayList<WhiteList> arrSong;

    public SearchAdapter(Context context, ArrayList<WhiteList> arrSong) {
        this.context = context;
        this.arrSong = arrSong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_search_song,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WhiteList whiteList = arrSong.get(position);
        holder.textViewSearhNameSong.setText(whiteList.getTenBaiHat());
        holder.textViewSearchNameMusican.setText(whiteList.getCaSi());
        Picasso.with(context).load(whiteList.getHinhBaiHat()).into(holder.imageViewSearchSong);
    }

    @Override
    public int getItemCount() {
        return arrSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder // recyclerview khai báo các view thông qua class ViewHolder
    {
        TextView textViewSearhNameSong,textViewSearchNameMusican;
        ImageView imageViewSearchSong,imageViewSearchLike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSearhNameSong = itemView.findViewById(R.id.textViewSearhNameSong);
            textViewSearchNameMusican = itemView.findViewById(R.id.textViewSearchNameMusican);
            imageViewSearchSong = itemView.findViewById(R.id.imageViewSearchSong);
            imageViewSearchLike = itemView.findViewById(R.id.imageViewSearchLike);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("playmusic",arrSong.get(getPosition()));
                    context.startActivity(intent);
                }
            });

            imageViewSearchLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageViewSearchLike.setImageResource(R.drawable.iconloved);
                    DataClient dataClient = APIUtils.getData();
                    Call<String> callback = dataClient.UpdateLike("1",arrSong.get(getPosition()).getIdBaiHat());
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
                    imageViewSearchLike.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageViewSearchLike.setImageResource(R.drawable.iconlove);
                            DataClient dataClient1 = APIUtils.getData();
                            Call<String> callback = dataClient1.UpdateDislike("1",arrSong.get(getPosition()).getIdBaiHat());
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
        }
    }
}

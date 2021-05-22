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

            imageViewLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageViewLike.setImageResource(R.drawable.iconloved);
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
                    imageViewLike.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageViewLike.setImageResource(R.drawable.iconlove);
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

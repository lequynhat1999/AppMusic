package com.example.appmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.appmusic.Activity.ListChuDeActivity;
import com.example.appmusic.Activity.ListSongActivity;
import com.example.appmusic.Activity.ListTheLoaiTheoChuDeActivity;
import com.example.appmusic.Model.ChuDe;
import com.example.appmusic.Model.TheLoai;
import com.example.appmusic.Model.TheLoaiChuDe;
import com.example.appmusic.R;
import com.example.appmusic.Retrofit2.APIUtils;
import com.example.appmusic.Retrofit2.DataClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTheLoaiChuDe extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView textViewViewMoreChuDeTheLoai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai,container,false);
        Mapping();
        textViewViewMoreChuDeTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListChuDeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void Mapping() {
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        textViewViewMoreChuDeTheLoai = view.findViewById(R.id.textViewViewMoreChuDeTheLoai);
    }

    private void GetData() {
        DataClient dataClient = APIUtils.getData();
        Call<TheLoaiChuDe> callback = dataClient.GetDataTheLoaiChuDe();
        callback.enqueue(new Callback<TheLoaiChuDe>() {
            @Override
            public void onResponse(Call<TheLoaiChuDe> call, Response<TheLoaiChuDe> response) {
                TheLoaiChuDe theLoaiChuDe = response.body();

                final ArrayList<ChuDe> arrChuDe = new ArrayList<>();
                arrChuDe.addAll(theLoaiChuDe.getChuDe()); // add mảng cùng kiểu dữ liệu từ thằng cha vào

                final ArrayList<TheLoai> arrTheLoai = new ArrayList<>();
                arrTheLoai.addAll(theLoaiChuDe.getTheLoai());

                // trong horizontalScrollView sẽ có 1 cái viewGroup để chứa các giá trị hình ảnh này
                LinearLayout linearLayout = new LinearLayout(getActivity()); // khởi tạo viewGroup
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);// hiển thị các view theo chiều ngang

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(670,290); // set lại kích thước cho phần viewGroup
                layoutParams.setMargins(10,20,10,30);

                for(int i =0; i<arrChuDe.size();i++)
                {
                    CardView cardView = new CardView(getActivity()); //view này bo xung quanh
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity()); // đưa hình ảnh vào trong cardView
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);// set lại kích cỡ của ảnh giãn đều

                    if(arrChuDe.get(i).getHinhChuDe() != null) // kiểm tra xem mảng có chứa giá trị k
                    {
                        Picasso.with(getActivity()).load(arrChuDe.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams); //set kích thước cho cardView
                    cardView.addView(imageView); //truyền imageView vào trong cardView
                    linearLayout.addView(cardView); // add cardView vào trong ViewGroup

                    int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListTheLoaiTheoChuDeActivity.class);
                            intent.putExtra("chude",arrChuDe.get(finalI));
                            startActivity(intent);
                        }
                    });

                }

                for(int j =0; j<arrTheLoai.size();j++)
                {
                    CardView cardView = new CardView(getActivity()); //view này bo xung quanh
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);// set lại kích cỡ của ảnh giãn đều

                    if(arrTheLoai.get(j).getHinhTheLoai() != null) // kiểm tra xem mảng có chứa giá trị k
                    {
                        Picasso.with(getActivity()).load(arrTheLoai.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams); //set kích thước cho cardView
                    cardView.addView(imageView); //truyền imageView vào trong cardView
                    linearLayout.addView(cardView); // add cardView vào trong ViewGroup
                    int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListSongActivity.class);
                            intent.putExtra("idtheloai",arrTheLoai.get(finalJ));
                            startActivity(intent);


                        }
                    });

                }
                horizontalScrollView.addView(linearLayout); // add viewGroup vào trong horizontal
            }

            @Override
            public void onFailure(Call<TheLoaiChuDe> call, Throwable t) {

            }
        });
    }
}

package com.example.appmusic.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentDiaNhac extends Fragment {
    View view;
    CircleImageView circleImageView;
    public ObjectAnimator objectAnimator; // tạo ra 1 object, khi click vào tạo hoạt ảnh
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dia_nhac,container,false);
        circleImageView = view.findViewById(R.id.imageViewCircle);
        objectAnimator = ObjectAnimator.ofFloat(circleImageView,"rotation",0f,360f); // quay tròn từ 0 đến 360
        objectAnimator.setDuration(40000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE); // Lặp lại
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator()); // tránh việc đang chạy bị ngừng lại 1 lúc
        objectAnimator.getInterpolator();
        objectAnimator.start();

        return view;
    }
    public void PlayNhac(String img)
    {
        Picasso.with(getActivity()).load(img).into(circleImageView);
    }
}

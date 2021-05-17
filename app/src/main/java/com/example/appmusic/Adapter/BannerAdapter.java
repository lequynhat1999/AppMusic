package com.example.appmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.appmusic.Model.Quangcao;
import com.example.appmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {

    Context context;
    ArrayList<Quangcao> arrListBanner;

    public BannerAdapter(Context context, ArrayList<Quangcao> arrListBanner) {
        this.context = context;
        this.arrListBanner = arrListBanner;
    }

    @Override
    public int getCount() { // trong mảng có bao nhiêu tấm hình thì sẽ vẽ ra bấy nhiêu pager
        return arrListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) { // trả về view tùy theo object được định hình
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) { // func này dùng để định hình và gán dữ liệu cho mỗi object, mỗi object tượng trưng cho 1 pager
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_banner,null); // gán layout vào view

        ImageView imageViewBackgroundBanner = view.findViewById(R.id.imageviewBackgroundBanner); // ánh xạ view trong layout row_banner
        ImageView imageViewBanner = view.findViewById(R.id.imageviewBanner);
        TextView textViewTitleSong = view.findViewById(R.id.textviewTitleSong);
        TextView textViewContent = view.findViewById(R.id.textviewContent);



        Picasso.with(context).load(arrListBanner.get(position).getHinhAnh()).into(imageViewBackgroundBanner); // sử dụng thư viện Picasso để load hình ảnh
        Picasso.with(context).load(arrListBanner.get(position).getHinhBaiHat()).into(imageViewBanner);
        textViewTitleSong.setText(arrListBanner.get(position).getTenBaiHat());
        textViewContent.setText(arrListBanner.get(position).getNoiDung());

        ConstraintLayout constraintLayout = view.findViewById(R.id.constraintBottom);
        constraintLayout.setAlpha(0.6f);

        container.addView(view); // add giá trị view vào trong viewPager
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) { // func này để khi chuyển sang pager cuối cùng sẽ k bị gặp lỗi
        container.removeView((View) object);
    }
}

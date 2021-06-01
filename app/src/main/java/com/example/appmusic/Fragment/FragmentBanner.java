package com.example.appmusic.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusic.Adapter.BannerAdapter;
import com.example.appmusic.Model.Quangcao;
import com.example.appmusic.R;
import com.example.appmusic.Retrofit2.APIUtils;
import com.example.appmusic.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBanner  extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable; // để quản lý tiến trình
    Handler handler;
    int currentItem; // gán giá trị của từng page, phục vụ cho việc load page tiếp theo

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);
        Mapping();
        GetData();
        return view;

    }

    private void Mapping() {
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.indicatordefault);

    }

    private void GetData() {
        DataClient dataClient = APIUtils.getData(); // khởi tạo dataClient và khởi tạo những phương thức để đẩy lên
        Call<List<Quangcao>> calback = dataClient.GetDataBanner(); // khi gửi lên thì gửi phương thức GetDataBanner
        calback.enqueue(new Callback<List<Quangcao>>() { // khi thực hiện việc gửi lên xong thì dữ liệu sẽ trả về cho biến callback
            @Override
            public void onResponse(Call<List<Quangcao>> call, Response<List<Quangcao>> response) {
                ArrayList<Quangcao> arrbanner = (ArrayList<Quangcao>) response.body();
                bannerAdapter = new BannerAdapter(getActivity(),arrbanner); // khởi tạo bannerAdapter
                viewPager.setAdapter(bannerAdapter);    // khi đã có dữ liệu cho từng pager r thì set cho viewPager
                circleIndicator.setViewPager(viewPager); // có bao nhiêu pager thì hiển thị bằng đấy chấm
                handler = new Handler();
                runnable = new Runnable() { // lắng nghe và thực hiện hành động khi mà handler gọi
                    @Override
                    public void run() { // để tự dộng chuyển viewPager
                        currentItem = viewPager.getCurrentItem(); // kiểm tra xem hiện tại đang ở pager nào
                        currentItem ++; // mỗi khi xem 1 pager, biến currentItem tăng lên 1 để có thể chuyển sang pager tiếp theo
                        if(currentItem >= viewPager.getAdapter().getCount()) // khi đến pager cuối cùng thì set cho = 0
                        {
                            currentItem = 0;
                        }
                        viewPager.setCurrentItem(currentItem,true); // mỗi lần tăng giá trị lên, set lại giá trị cho viewPager
                        handler.postDelayed(runnable,4000); // sau 4s thì chạy 1 lần
                    }
                };
                handler.postDelayed(runnable,4000);
            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {
            }
        });

    }
}

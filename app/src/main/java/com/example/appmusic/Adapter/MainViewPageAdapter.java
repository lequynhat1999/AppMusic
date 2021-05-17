package com.example.appmusic.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> arrFragment = new ArrayList<>(); // mảng dùng để add vào fragment
    private ArrayList<String> arrTitle = new ArrayList<>(); // hiển thị title mỗi khi vuốt viewpager

    public MainViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrFragment.get(position); // khi click vào thì trả về vị trí của fragment đó
    }

    @Override
    public int getCount() {
        return arrFragment.size(); // hiển thị toàn bộ fragment trong mảng
    }

    public void addFragment (Fragment fragment, String title)
    {
        arrFragment.add(fragment);
        arrTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) { // tên của mỗi page hiển thị trên phần tab
        return arrTitle.get(position);
    }
}

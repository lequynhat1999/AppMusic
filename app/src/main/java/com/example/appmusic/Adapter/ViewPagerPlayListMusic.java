package com.example.appmusic.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerPlayListMusic  extends FragmentPagerAdapter {
    public final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    public ViewPagerPlayListMusic(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position); // trả về vị trí fragment
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size(); // trả về số lượng fragment trong mảng
    }

    public void AddFragment(Fragment fragment)
    {
        fragmentArrayList.add(fragment);
    }

}

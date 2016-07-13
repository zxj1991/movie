package com.example.movie.Fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movie.Adapter.MainPagerAdapter;
import com.example.movie.R;

import java.util.ArrayList;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class HomeFragment extends Fragment {
    private ArrayList<Fragment> fragments_list = new ArrayList<Fragment>();
    private MainPagerAdapter mainPagerAdapter;
    private ViewPager main_viewpager;
    private TabLayout tablayout;
    private String[] taglist = {"精选", "电影", "电视剧", "综艺", "动漫", "娱乐"};
    private View view;
    MainVideoFragment mainVideoFragment = new MainVideoFragment();

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        view = paramLayoutInflater.inflate(R.layout.homefragment_layout, null);
        for (int i = 0; i < taglist.length; i++) {
            fragments_list.add(new MainVideoFragment().setFragment(i));

        }

        tablayout = (TabLayout) view.findViewById(R.id.tablayout_1);
        main_viewpager = (ViewPager) view.findViewById(R.id.main_viewpager);
        mainPagerAdapter = new MainPagerAdapter(getActivity().getFragmentManager(), fragments_list, taglist);
        main_viewpager.setAdapter(mainPagerAdapter);
        tablayout.setupWithViewPager(main_viewpager);
        main_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mainVideoFragment.setItem(position);

            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return view;
    }

}

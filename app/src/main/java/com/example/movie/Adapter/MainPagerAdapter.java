package com.example.movie.Adapter;

import android.app.Fragment;
import android.app.FragmentManager;


import com.example.movie.Utils.FragmentPagerAdapter;

import java.util.ArrayList;


/**
 * 用于
 * Created by 潇舰 on 2016/7/10.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> list;
    String[] taglist;

    public MainPagerAdapter(FragmentManager FragmentManager, ArrayList<Fragment> paramArrayList, String[] paramArrayOfString)
    {
        super(FragmentManager);
        this.list = paramArrayList;
        this.taglist = paramArrayOfString;
    }

    public int getCount()
    {
        return this.list.size();
    }

    public Fragment getItem(int paramInt)
    {
        return (Fragment)this.list.get(paramInt);
    }

    public CharSequence getPageTitle(int paramInt)
    {
        if (paramInt > this.taglist.length)
            return "未加载";
        return this.taglist[paramInt];
    }

}

package com.example.movie.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.movie.Activity.DiffMoviesActivity;
import com.example.movie.Activity.MoviesPlayActivity;
import com.example.movie.Activity.SearchActivity;
import com.example.movie.Bean.ShouyeInfo;
import com.example.movie.R;
import com.example.movie.Utils.ImageUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于首页viewpager的轮播适配器
 * Created by 潇舰 on 2016/7/10.
 */
public class HomeViewPagerAdapter extends PagerAdapter {
    private Context context;
    ImageUtil imageUtil;
    private ArrayList<View> list;
private List<ShouyeInfo.SlideBean> list_slide;
    public HomeViewPagerAdapter(Context context, ArrayList<View> list,List<ShouyeInfo.SlideBean> list_slide) {
        this.context = context;
        this.list = list;
        this.list_slide=list_slide;
        imageUtil = ImageUtil.getInstance();
        imageUtil.Imagecache();
    }


    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public Object instantiateItem(ViewGroup container, final int position) {
        if (list.get(position % list.size()).getParent() != null) {
            ((ViewPager) (list.get(position % list.size())).getParent())
                    .removeView(list.get(position % list.size()));
        }
            container.addView(list.get(position % list.size()), 0);
        View view=list.get(position%list.size());
        ImageView imageView= (ImageView) view.findViewById(R.id.imageview_viewpager);
        ImageLoader.getInstance().displayImage(list_slide.get(position%list.size()).getD_pic(),imageView,imageUtil.getOptions());
        list.get(position % list.size()).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switch (position % list.size()) {
                    case 0:
                        context.startActivity(new Intent(context, DiffMoviesActivity.class));
                        break;
                    case 1:
                        context.startActivity(new Intent(context, MoviesPlayActivity.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, SearchActivity.class));
                        break;
                }
            }
        });
        return this.list.get(position % this.list.size());
    }

    public void destroyItem(ViewGroup container, int paramInt, Object paramObject) {
    }


}

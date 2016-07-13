package com.example.movie.Activity;

import android.util.Log;
import android.widget.ListView;

import com.example.movie.Adapter.CommonAdapter;
import com.example.movie.Adapter.ViewHolder;
import com.example.movie.R;
import com.example.movie.View.MyHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class DiffMoviesActivity extends BaseActivity {
    private MyHorizontalScrollView horizontalScrollView;
    private List<String> list;//装分类选项的集合
//    private String[] arr = {"全部", "动作", "冒险", "喜剧", "爱情", "战争"};

    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_diffmovies);
    }

    @Override
    public void afterinitView() {

    }

    @Override
    public void beforInitView() {
        setadapter();
    }

    @Override
    public void initView() {

    }

    public void setadapter() {
//        List<MyHorizontalScrollView> list1=new ArrayList<>();
//        ListView lv= (ListView) findViewById(R.id.listview_fenlei);
//        lv.setAdapter(new CommonAdapter<MyHorizontalScrollView>(this,list1,R.layout.item_fenlei) {
//            @Override
//            public void convert(ViewHolder viewHolder, MyHorizontalScrollView item) {
//            }
//        });

        String[] arr = {"全部", "动作", "冒险", "喜剧", "爱情", "战争"};
        list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        horizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.hscrollview);
        horizontalScrollView.initDatas(new CommonAdapter<String>(this, list, R.layout.item_fenlei_son) {
            @Override
            public void convert(ViewHolder viewHolder, String item) {
                viewHolder.setText(R.id.text_fenlei_son, item+"");
            }
        });

    }

}

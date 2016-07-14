package com.example.movie.Activity;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.movie.Adapter.CommonAdapter;
import com.example.movie.Adapter.ViewHolder;
import com.example.movie.R;
import com.example.movie.View.MyHorizontalScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class DiffMoviesActivity extends BaseActivity {
    private MyHorizontalScrollView horizontalScrollView;
    private ListView listview_fenlei;
    private List<List<String>> list;//装分类选项的集合
    private TextView textview;
    private TextView currentText;

    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_diffmovies);
    }

    @Override
    public void afterinitView() {
        setadapter();

    }

    @Override
    public void beforInitView() {
        ChangeTitle(1, "分类");

    }

    @Override
    public void initView() {
        addData();
        listview_fenlei = (ListView) findViewById(R.id.listview_fenlei);
    }



    public void addData() {
        list = new ArrayList<>();
        list.add(new ArrayList<>(Arrays.asList("内地","港台","欧美","日韩","其他")));
        list.add(new ArrayList<>(Arrays.asList("偶像", "喜剧", "爱情", "都市", "古装", "武侠", "历史", "警匪", "家庭", "神话", "战争")));
        list.add(new ArrayList<>(Arrays.asList("2016","2015","2014","2013","2012","2011","2010","2009","2008","2007","2006","2005")));
    }


private MyHorizontalScrollView currentscrollview1,currentscrollview2,currentscrollview3;
    public void setadapter() {
        listview_fenlei.setAdapter(new CommonAdapter<List<String>>(this, list, R.layout.item_fenlei) {
            @Override
            public void convert(final ViewHolder viewHolder, List<String> item) {
                viewHolder.setText(R.id.textview_fenlei_type, "全部");
                horizontalScrollView = viewHolder.getView(R.id.hscrollview);
                textview = viewHolder.getView(R.id.textview_fenlei_type);
                textview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                horizontalScrollView.initDatas(new CommonAdapter<String>(DiffMoviesActivity.this, item, R.layout.item_fenlei_son) {
                    @Override
                    public void convert(final ViewHolder viewHolder, String item) {
                        viewHolder.setText(R.id.text_fenlei_son, item + "");
                    }
                });
                horizontalScrollView.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener() {


                    @Override
                    public void onClick(View view, int position) {
                        Log.e("msg", "position=" + position);

                        view.setBackgroundResource(R.drawable.fenlei);//点击改变背景边框
                        TextView text = (TextView) view.findViewById(R.id.text_fenlei_son);
                        text.setTextColor(0xffe23132); //点击改变文字颜色

//                        if (currentText != null) {
//                            currentText.setTextColor(0xff323232);
//                        }
//                        currentText = text;
                    }
                });
            }
        });
    }
    /**
     * 初始化点击
     */
    TextView text;
    public void init(ViewHolder viewHolder){
        text= viewHolder.getView(R.id.text_fenlei_son);
//        text= (TextView) view.findViewById(R.id.text_fenlei_son);
        text.setTextColor(0xff323232);
    }


}

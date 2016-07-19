package com.example.movie.Activity;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.movie.Adapter.CommonAdapter;
import com.example.movie.Adapter.ViewHolder;
import com.example.movie.R;
import com.example.movie.Bean.FenleiInfo;
import com.example.movie.Utils.HttpCallBack;
import com.example.movie.Utils.HttpUtil;
import com.example.movie.View.MyGridView;
import com.example.movie.View.MyHorizontalScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class DiffMoviesActivity extends BaseActivity {
    private MyHorizontalScrollView horizontalScrollView;
    private ListView listview_fenlei;
    private List<List<String>> list;//装分类选项的集合
    private TextView textview;


    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_diffmovies);
    }

    @Override
    public void afterinitView() {
        setadapter();
        setgridview();

    }

    @Override
    public void beforInitView() {
        ChangeTitle(1, "分类");

    }

    @Override
    public void initView() {
        addData();
        listview_fenlei = (ListView) findViewById(R.id.listview_fenlei);
        gridview = (MyGridView) findViewById(R.id.gridview_fenlei);
    }


    private MyGridView gridview;
    List<FenleiInfo> list_fenlei;//视频分类的集合

    /**
     * 分类界面的gridview加载方法
     */
    public void setgridview() {

        HttpUtil.getFenLei(this, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                list_fenlei = FenleiInfo.arrayFenleiInfoFromData(result);
                gridview.setAdapter(new CommonAdapter<FenleiInfo>(DiffMoviesActivity.this, list_fenlei, R.layout.item_gridview_fenlei) {
                    @Override
                    public void convert(ViewHolder viewHolder, FenleiInfo item) {
                        viewHolder.setImageBitmap(R.id.image_fenlei, item.getD_pic());
                        viewHolder.setText(R.id.textView_fenlei, item.getD_name());
                    }
                });

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }


    public void addData() {
        list = new ArrayList<>();
        list.add(new ArrayList<>(Arrays.asList("内地", "港台", "欧美", "日韩", "其他")));
        list.add(new ArrayList<>(Arrays.asList("偶像", "喜剧", "爱情", "都市", "古装", "武侠", "历史", "警匪", "家庭", "神话", "战争")));
        list.add(new ArrayList<>(Arrays.asList("2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005")));
    }


    public void setadapter() {
        listview_fenlei.setAdapter(new CommonAdapter<List<String>>(this, list, R.layout.item_fenlei) {
            @Override
            public void convert(final ViewHolder viewHolder, final List<String> item) {
                viewHolder.setText(R.id.textview_fenlei_type, "全部");
                horizontalScrollView = viewHolder.getView(R.id.hscrollview);
//                textview = viewHolder.getView(R.id.textview_fenlei_type);
//                textview.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
                horizontalScrollView.initDatas(new CommonAdapter<String>(DiffMoviesActivity.this, item, R.layout.item_fenlei_son) {
                    @Override
                    public void convert(final ViewHolder viewHolder, String item) {
                        viewHolder.setText(R.id.text_fenlei_son, item + "");

                        horizontalScrollView.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener() {
                            private TextView currentText;

                            @Override
                            public void onClick(View view, int position) {
                                Log.e("msg", "viewid=" + view);
                                view.setBackgroundResource(R.drawable.fenlei);//点击改变背景边框
                                TextView textview = (TextView) view.findViewById(R.id.text_fenlei_son);
                                textview.setTextColor(0xffe23132);//被选中变色
                                if (currentText == textview) {//如果textview被选中则不改变状态
                                } else {

                                    if (currentText != null) {
                                        currentText.setTextColor(0xff272636);//变黑
                                    }
                                    currentText = textview;
                                }
                            }
                        });


                    }
                });


            }
        });
    }


}

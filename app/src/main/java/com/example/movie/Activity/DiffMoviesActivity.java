package com.example.movie.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.Adapter.CommonAdapter;
import com.example.movie.Adapter.GridViewAdapter;
import com.example.movie.Adapter.ViewHolder;
import com.example.movie.Fragment.MainVideoFragment;
import com.example.movie.R;
import com.example.movie.Bean.FenleiInfo;
import com.example.movie.Utils.HttpCallBack;
import com.example.movie.Utils.HttpUtil;
import com.example.movie.View.MyGridView;
import com.example.movie.View.MyHorizontalScrollView;
import com.example.movie.View.MyListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class DiffMoviesActivity extends BaseActivity {
    private MyHorizontalScrollView horizontalScrollView;
    private PullToRefreshGridView gridview;
    private MyListView listview_fenlei;
    private List<List<String>> list;//装分类选项的集合
    private TextView textview;
    ImageLoader imageLoader;
    GridViewAdapter adapter;

    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_diffmovies);
        adapter = new GridViewAdapter(this);

    }

    @Override
    public void afterinitView() {
        addData();
        setadapter();
        setgridview();
        imageLoader = ImageLoader.getInstance();
        gridview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);//仅支持上拉
        gridview.setAdapter(adapter);
        gridview.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, true));
        gridview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<GridView>() {
            @Override
            public void onRefresh(PullToRefreshBase<GridView> refreshView) {
                index++;
//                setgridview();
                if (id == 1) {
                    setItem(type1[position]);
                }
                if (id == 2) {
                    setItem(type2[position]);
                }
                gridview.onRefreshComplete();
            }
        });
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                int i = Integer.parseInt(All_list.get(position).getD_id());
                intent.putExtra("id", i);
                intent.setClass(DiffMoviesActivity.this, MoviesPlayActivity.class);
                Log.e("msg", "list_fenlei.get(position).getD_id()=" + All_list.get(position).getD_id());
                startActivity(intent);
            }
        });


    }

    @Override
    public void beforInitView() {
        ChangeTitle(1, "分类");

    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        listview_fenlei = (MyListView) findViewById(R.id.listview_fenlei);
        gridview = (PullToRefreshGridView) findViewById(R.id.gridview_fenlei);
    }


    List<FenleiInfo> list_fenlei;//视频分类的集合


    int index = 1;//页码
    int id;//判断是电影、电视剧、综艺、动漫
    int[] type1 = {5, 6, 7, 8, 9, 10, 11, 20, 21, 22, 24, 25};//电影
    int[] type2 = {12, 13, 14, 15, 18};//电视剧
    List<FenleiInfo> All_list = new ArrayList<>();

    /**
     * 分类界面的gridview加载方法
     */
    public void setgridview() {
        HttpUtil.getFenLei1(DiffMoviesActivity.this, id, index, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                list_fenlei = FenleiInfo.arrayFenleiInfoFromData(result);
                All_list.addAll(list_fenlei);
                adapter.Update(All_list);
//                gridview.setAdapter(new CommonAdapter<FenleiInfo>(DiffMoviesActivity.this, All_list, R.layout.item_gridview_fenlei) {
//                    @Override
//                    public void convert(ViewHolder viewHolder, FenleiInfo item) {
//                        viewHolder.setImageBitmap(R.id.image_fenlei, item.getD_pic());
//                        viewHolder.setText(R.id.textView_fenlei, item.getD_name());
//                    }
//
//
//                });

            }

            @Override
            public void onFailure(String error) {

            }

        });
    }


    public void addData() {
        list = new ArrayList<>();
//        list.add(new ArrayList<>(Arrays.asList("内地", "港台", "欧美", "日韩", "其他")));
//        list.add(new ArrayList<>(Arrays.asList("2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005")));
        if (id == 1) {
            list.add(new ArrayList<>(Arrays.asList("动作", "喜剧", "爱情", "科幻", "恐怖", "剧情", "伦理", "惊悚", "武侠", "古装", "悬疑", "谍战")));
        } else if (id == 2) {
            list.add(new ArrayList<>(Arrays.asList("国产剧", "港台剧", "日韩剧", "欧美剧", "其他")));
        }


    }

    int position;

    public void getposition(int position) {
        this.position = position;
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
                                getposition(position);
                                index = 1;
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
                                list_fenlei.clear();
                                All_list.clear();
                                if (id == 1) {
                                    setItem(type1[position]);
                                }
                                if (id == 2) {
                                    setItem(type2[position]);
                                }
                            }
                        });


                    }
                });


            }
        });
    }

//    List<FenleiInfo> All_list_fenlei = new ArrayList<>();

    public void setItem(int type) {
        HttpUtil.getFenLei2(DiffMoviesActivity.this, index, type, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                list_fenlei = FenleiInfo.arrayFenleiInfoFromData(result);
                All_list.addAll(list_fenlei);
                adapter.Update(All_list);
                if (list_fenlei.isEmpty()) {
                    Toast.makeText(DiffMoviesActivity.this, "暂时没有该类资源", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String error) {

            }
        });

    }


}

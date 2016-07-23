package com.example.movie.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.movie.Activity.DiffMoviesActivity;
import com.example.movie.Activity.MoviesPlayActivity;
import com.example.movie.Activity.SearchActivity;
import com.example.movie.Adapter.CommonAdapter;
import com.example.movie.Adapter.GridViewAdapter;
import com.example.movie.Adapter.HomeViewPagerAdapter;
import com.example.movie.Adapter.ViewHolder;
import com.example.movie.Bean.AllSaveData;
import com.example.movie.Bean.ShouyeInfo;
import com.example.movie.R;
import com.example.movie.Utils.DialogUtils;
import com.example.movie.Utils.HttpCallBack;
import com.example.movie.Utils.HttpUtil;
import com.example.movie.View.MyGridView;
import com.example.movie.View.MyScrollview;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class MainVideoFragment extends Fragment implements View.OnClickListener {
    private MyGridView gd;//首页gridview
    private ArrayList<String> gd_list;//电影图片的集合
    private GridViewAdapter gdapter;
    private Intent intent;
    private boolean isStop = false;//开启线程的标识

    private LinearLayout sousuo;
    private LinearLayout fenlei;

    private MyScrollview sv;
    private View view;
    private HomeViewPagerAdapter vp_adapter;
    private ViewPager vp_viewPager;
    private ImageView[] indicator_imgs = new ImageView[5];
    private ArrayList<View> list;//装轮播图片的集合

    List<ShouyeInfo.SlideBean> list_slide;

    Thread thread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("msg", "创建之前加载数据info是否为空" + (info == null ? "    为空" : "     不为空"));//提前执行了
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        view = paramLayoutInflater.inflate(R.layout.mainvideofragment_layout, null);
        initview();
        info=AllSaveData.getInstance().info;
        gd.setSelector(new ColorDrawable(0));//gridview子项选中无背景颜色
        sv.smoothScrollTo(0, 0);
        inittips();
        init();
//       getData();
        return view;
    }
//==woxiankanakn你服务器 返回的问题

    /**
     * 初始化所有空控件
     */
    public void initview() {
        gd = (MyGridView) view.findViewById(R.id.gridview_Home);
        vp_viewPager = (ViewPager) view.findViewById(R.id.vp_viewpager);
        sv = (MyScrollview) view.findViewById(R.id.scrollView_1);
        sousuo = (LinearLayout) view.findViewById(R.id.sousuo);
        fenlei = (LinearLayout) view.findViewById(R.id.fenlei);
        sousuo.setOnClickListener(this);
        fenlei.setOnClickListener(this);
    }

    /**
     * 初始化framgent的显示内容
     */
    private void init() {
        switch (type) {
            case 0:
                Log.i("msg","1执行11");
                setGd(AllSaveData.getInstance().info.getVods1());
                frgment();
                break;
            case 1:
                Log.i("msg", "执行22");
                if (info != null) {
//                    setGd(info.getVods2());
                } else {
                    Log.i("msg", "info现在为空不加载");
                }
                frgment();
                break;
            case 2:
//                setGd(info.getVods3());
                frgment();
                break;
            case 3:
//                setGd(info.getVods4());
                frgment();
                break;

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("msg", "就绪");
        isStop = false;
    }

    //    private void frgment(List<String> listtext) {
    private void frgment() {
//        addItemViewPager();
        Log.i("msg", "执行线程0000000000000000");
        thread = new Thread(new Runnable() {
            public void run() {
                while (!isStop) {
                    SystemClock.sleep(3000);
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                vp_viewPager.setCurrentItem(1 + vp_viewPager.getCurrentItem());
                            }
                        });
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    int position;

    public void setItem(int position) {
        this.position = position;
    }


    int type;

    public MainVideoFragment setFragment(int type) {
        this.type = type;
        return this;
    }

    public void inittips() {
        ImageView imgView;
        View v = view.findViewById(R.id.tips);// 线性水平布局，负责动态调整导航图标

        for (int i = 0; i < 5; i++) {
            imgView = new ImageView(getActivity());
            LinearLayout.LayoutParams params_linear = new LinearLayout.LayoutParams(10, 10);
            params_linear.setMargins(7, 10, 7, 10);
            imgView.setLayoutParams(params_linear);
            indicator_imgs[i] = imgView;

            if (i == 0) { // 初始化第一个为选中状态

                indicator_imgs[i].setBackgroundResource(R.drawable.yuan_1);
            } else {
                indicator_imgs[i].setBackgroundResource(R.drawable.yuan);
            }
            ((ViewGroup) v).addView(indicator_imgs[i]);
        }
    }

    /**
     * 获取首页数据
     */
    private void setGd(List<?> list_shouye) {
        list_slide = AllSaveData.getInstance().info.getSlide();
        list = new ArrayList();
        for (int i = 0; i < list_slide.size(); i++) {
            View localView = LayoutInflater.from(getActivity()).inflate(R.layout.item_viewpager, null);
            list.add(localView);
        }
        vp_adapter = new HomeViewPagerAdapter(getActivity(), list, list_slide);
        vp_viewPager.setAdapter(vp_adapter);
        vp_viewPager.setCurrentItem(100 * list.size());
        vp_viewPager.setOnPageChangeListener(new MyListener());
        if (type == 0) {//电影数据
            gd.setAdapter(new CommonAdapter<ShouyeInfo.Vods1Bean>(getActivity(),  AllSaveData.getInstance().info.getVods1(), R.layout.item_gridview_home) {
                @Override
                public void convert(ViewHolder viewHolder, ShouyeInfo.Vods1Bean item) {

                    viewHolder.setImageBitmap(R.id.imageView_shouye, item.getD_pic());
                    viewHolder.setText(R.id.textView_shouye_name, item.getD_name());

                }
            });
        } else if (type == 1) {//电视剧数据
            gd.setAdapter(new CommonAdapter<ShouyeInfo.Vods2Bean>(getActivity(),  AllSaveData.getInstance().info.getVods2(), R.layout.item_gridview_home) {
                @Override
                public void convert(ViewHolder viewHolder, ShouyeInfo.Vods2Bean item) {

                        viewHolder.setImageBitmap(R.id.imageView_shouye, item.getD_pic());
                        viewHolder.setText(R.id.textView_shouye_name, item.getD_name());

                }
            });
        }else if (type == 2) {//综艺数据
            gd.setAdapter(new CommonAdapter<ShouyeInfo.Vods3Bean>(getActivity(),  AllSaveData.getInstance().info.getVods3(), R.layout.item_gridview_home) {
                @Override
                public void convert(ViewHolder viewHolder, ShouyeInfo.Vods3Bean item) {

                    viewHolder.setImageBitmap(R.id.imageView_shouye, item.getD_pic());
                    viewHolder.setText(R.id.textView_shouye_name, item.getD_name());

                }
            });
        }else if (type == 3) {//动漫数据
            gd.setAdapter(new CommonAdapter<ShouyeInfo.Vods4Bean>(getActivity(),  AllSaveData.getInstance().info.getVods4(), R.layout.item_gridview_home) {
                @Override
                public void convert(ViewHolder viewHolder, ShouyeInfo.Vods4Bean item) {

                    viewHolder.setImageBitmap(R.id.imageView_shouye, item.getD_pic());
                    viewHolder.setText(R.id.textView_shouye_name, item.getD_name());

                }
            });
        }
        gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MoviesPlayActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * 根据不同的分类fragment进入不同的分类activity
     *
     * @param
     */
    public void Classification() {
        intent = new Intent();
        intent.setClass(getActivity(), DiffMoviesActivity.class);
        startActivity(intent);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sousuo:
                intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.fenlei:
                intent = new Intent(getActivity(), DiffMoviesActivity.class);
                startActivity(intent);
                break;
        }

    }

    ShouyeInfo info; //数据源

    //    public List<?> getData(int datatype) {





    private class MyListener implements ViewPager.OnPageChangeListener {
        private MyListener() {
        }

        public void onPageScrollStateChanged(int state) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageSelected(int position) {// 改变所有导航的背景图片为：未选中
            for (int i = 0; i < indicator_imgs.length; i++) {
                indicator_imgs[i].setBackgroundResource(R.drawable.yuan);
            }
            // 改变当前背景图片为：选中
            indicator_imgs[(position % list.size())].setBackgroundResource(R.drawable.yuan_1);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("msg", "销毁");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("msg", "停止");
    }

    @Override
    public void onPause() {
        super.onPause();
        isStop = true;


    }

    public void getData() {
        //这个写在首页这个Fragement
//        DialogUtils.createLoadingDialog(getActivity(), "加载数据").show();//这里可以加文字 可不加
        HttpUtil.getShouye(getActivity(), new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
//                DialogUtils.closeLoadingDialog();
                Gson gson = new Gson();
                AllSaveData.getInstance().info = gson.fromJson(result, ShouyeInfo.class);
                Log.i("msg", "保存数据成功？" + (AllSaveData.getInstance().info != null));
                if (AllSaveData.getInstance().info != null) {
//                    DialogUtils.closeLoadingDialog();
                    init();
                    Log.i("msg", "数据读取成功");
                } else {
                    Toast.makeText(getActivity(),"加载失败",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(String error) {
//                DialogUtils.closeLoadingDialog();
            }
        });
    }



}

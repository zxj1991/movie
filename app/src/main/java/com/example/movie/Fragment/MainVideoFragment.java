package com.example.movie.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import com.example.movie.Activity.DiffMoviesActivity;
import com.example.movie.Activity.MoviesPlayActivity;
import com.example.movie.Activity.SearchActivity;
import com.example.movie.Adapter.CommonAdapter;
import com.example.movie.Adapter.GridViewAdapter;
import com.example.movie.Adapter.HomeViewPagerAdapter;
import com.example.movie.Adapter.ViewHolder;
import com.example.movie.Bean.ShouyeInfo;
import com.example.movie.R;
import com.example.movie.Bean.FenleiInfo;
import com.example.movie.Utils.HttpCallBack;
import com.example.movie.Utils.HttpUtil;
import com.example.movie.View.MyGridView;
import com.example.movie.View.MyScrollview;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

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
    private int[] image = {R.drawable.item_1_1, R.drawable.item_2_2, R.drawable.item_3_3};
    private ImageView[] indicator_imgs = new ImageView[3];
    private ArrayList<View> list;//装轮播图片的集合

    List<ShouyeInfo.SlideBean> list_slide;

    Thread thread;

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        view = paramLayoutInflater.inflate(R.layout.mainvideofragment_layout, null);
        initview();
        gd.setSelector(new ColorDrawable(0));//gridview子项选中无背景颜色
        sv.smoothScrollTo(0, 0);
        inittips();
        init();

        return view;
    }


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
                getData();
                setGd();
                frgment();
                break;
            case 1:
                frgment();
                break;
            case 2:
                frgment();
                break;
            case 3:
                frgment();
                break;
            case 4:
                frgment();
                break;
            case 5:
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

    public void treaddstar(int position) {

    }

    /**
     * 创建多个item （每一条viewPager都是一个item）
     * 从服务器获取完数据（如文章标题、url地址） 后，再设置适配器
     */
    public void addItemViewPager() {
        list = new ArrayList();
        for (int i = 0; i < image.length; i++) {
            View localView = LayoutInflater.from(getActivity()).inflate(R.layout.item_viewpager, null);
            ((ImageView) localView.findViewById(R.id.imageview_viewpager)).setImageResource(image[i]);
            list.add(localView);
        }
    }


    //    private void frgment(List<String> listtext) {
    private void frgment() {
        addItemViewPager();
        Log.i("msg", "执行线程0000000000000000");
        thread = new Thread(new Runnable() {
            public void run() {
                while (!isStop) {
                    SystemClock.sleep(3000);
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                vp_viewPager.setCurrentItem(1 + vp_viewPager.getCurrentItem());
                                Log.i("msg", "viewpager开始滑动");
                            }
                        });
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        vp_adapter = new HomeViewPagerAdapter(getActivity(), list);
        vp_viewPager.setAdapter(vp_adapter);
        vp_viewPager.setCurrentItem(100 * list.size());
        vp_viewPager.setOnPageChangeListener(new MyListener());

//        gd_list = new ArrayList();
//        gd_list.add("http://i-7.vcimg.com/97b0cd0fa108cf725b3105ea19dee91f433653/origin.jpg");
//        gd_list.add("http://imgsrc.baidu.com/baike/pic/item/adaf2edda3cc7cd93892c59a3901213fb80e9132.jpg");
//        gd_list.add("http://i-7.vcimg.com/97b0cd0fa108cf725b3105ea19dee91f433653/origin.jpg");
//        gd_list.add("http://i-7.vcimg.com/97b0cd0fa108cf725b3105ea19dee91f433653/origin.jpg");
//        gd_list.add("http://i-7.vcimg.com/97b0cd0fa108cf725b3105ea19dee91f433653/origin.jpg");
//        gd_list.add("http://i-7.vcimg.com/97b0cd0fa108cf725b3105ea19dee91f433653/origin.jpg");
//        gd_list.add("http://i-7.vcimg.com/97b0cd0fa108cf725b3105ea19dee91f433653/origin.jpg");
//        gd_list.add("http://i-7.vcimg.com/97b0cd0fa108cf725b3105ea19dee91f433653/origin.jpg");
//        gd_list.add("http://i-7.vcimg.com/97b0cd0fa108cf725b3105ea19dee91f433653/origin.jpg");
//        gd_list.add("http://i-7.vcimg.com/97b0cd0fa108cf725b3105ea19dee91f433653/origin.jpg");
//        gd_list.add("http://i-7.vcimg.com/97b0cd0fa108cf725b3105ea19dee91f433653/origin.jpg");
//        gd_list.add("http://imgsrc.baidu.com/baike/pic/item/adaf2edda3cc7cd93892c59a3901213fb80e9132.jpg");
//        gd_list.add("http://i-7.vcimg.com/97b0cd0fa108cf725b3105ea19dee91f433653/origin.jpg");
//        gd_list.add("http://imgsrc.baidu.com/baike/pic/item/adaf2edda3cc7cd93892c59a3901213fb80e9132.jpg");
//        gd_list.add("http://i-7.vcimg.com/97b0cd0fa108cf725b3105ea19dee91f433653/origin.jpg");
//        gd_list.add("http://imgsrc.baidu.com/baike/pic/item/adaf2edda3cc7cd93892c59a3901213fb80e9132.jpg");
//        gd_list.add("http://i-7.vcimg.com/97b0cd0fa108cf725b3105ea19dee91f433653/origin.jpg");
//        gd_list.add("http://imgsrc.baidu.com/baike/pic/item/adaf2edda3cc7cd93892c59a3901213fb80e9132.jpg");
//        gdapter = new GridViewAdapter(getActivity());
//        gdapter.Update(gd_list);
//        gd.setAdapter(gdapter);
//        ImageUtil.getInstance().Imagecache();
//        gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), MoviesPlayActivity.class);
//                startActivity(intent);
//            }
//        });
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

        for (int i = 0; i < image.length; i++) {
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
//    List<FenleiInfo>list_fenlei;//视频分类的集合
    private void setGd() {
        HttpUtil.getShouye(getActivity(), new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ShouyeInfo info = gson.fromJson(result, ShouyeInfo.class);
                List<ShouyeInfo.Vods1Bean> list_fenlei = info.getVods1();


                gd.setAdapter(new CommonAdapter<ShouyeInfo.Vods1Bean>(getActivity(), list_fenlei, R.layout.item_gridview_home) {
                    @Override
                    public void convert(ViewHolder viewHolder, ShouyeInfo.Vods1Bean item) {
                        viewHolder.setImageBitmap(R.id.imageView_shouye, item.getD_pic());
                        viewHolder.setText(R.id.textView_shouye_name, item.getD_name());
                    }
                });
                gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), MoviesPlayActivity.class);
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onFailure(String error) {

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

    //    public List<?> getData(int datatype) {
    public List<?> getData() {

        return new ArrayList<>();
    }


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

}

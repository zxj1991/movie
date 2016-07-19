package com.example.movie.Activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.movie.Adapter.CommonAdapter;
import com.example.movie.Adapter.ViewHolder;
import com.example.movie.R;
import com.example.movie.Utils.HttpCallBack;
import com.example.movie.Utils.HttpUtil;
import com.example.movie.View.MediaController;
import com.example.movie.View.MyHorizontalScrollView;
import com.example.movie.View.VideoView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 潇舰 on 2016/7/10.
 */
public class MoviesPlayActivity extends BaseActivity implements MediaController.onClickIsFullScreenListener {
    private MediaController mController;
    private RelativeLayout rlDD;
    private VideoView viv;
    private MyHorizontalScrollView horizontalScrollView;
    private int[] arr = new int[17];
    List<Integer> list = new ArrayList<>();
    HttpCallBack httpCallBack;

    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_moviesplay);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpUtil.getbaidu(MoviesPlayActivity.this, httpCallBack);
//
//            }
//        }).start();
    }

    @Override
    public void afterinitView() {
        setadapter();
        setTuijianadapter();

    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        addData();
        horizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.id_horizontalScrollView);


        viv = (VideoView) findViewById(R.id.videoView);
        rlDD = (RelativeLayout) findViewById(R.id.rl_dd);
        mController = new MediaController(this);
        mController.setClickIsFullScreenListener(this);
        viv.setMediaController(this.mController);
        viv.setVideoURI(Uri.parse("android.resource://" + getPackageName()
                + "/" + R.raw.apple));
        viv.requestFocus();
        viv.start();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.e("info", "横屏");
            rlDD.setVisibility(View.GONE);
        } else {
            Log.e("info", "竖屏");
            rlDD.setVisibility(View.VISIBLE);
        }
        super.onConfigurationChanged(newConfig);
        viv.refreshDrawableState();
    }

    public boolean onTouchEvent(MotionEvent Event) {
        return super.onTouchEvent(Event);
    }

    public void setOnClickIsFullScreen() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {//设置RelativeLayout的全屏模式
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


/**
 *     模仿获取电视集数
 */
    public void addData() {
        for (int i = 0; i < arr.length; i++) {
            list.add(i+1);
        }
        Log.e("msg",arr.length+"");
    }

    /**
     * 电视集数适配
     */

    public void setadapter(){
        horizontalScrollView.initDatas(new CommonAdapter<Integer>(this, list, R.layout.item_jishu) {
            @Override
            public void convert(ViewHolder viewHolder, Integer item) {
                viewHolder.setText(R.id.text_ceshi, item + "");
            }
        });
    }

    /**
     * 推荐适配
     */
public void setTuijianadapter(){
    final List<String> list=new ArrayList<>();
    for (int i=0;i<6;i++){
        list.add("http://pic5.nipic.com/20100129/2714851_224213001047_2.jpg");
        list.add("http://www.232947.cc/imgall/nfwwoltcmvxgi2lcmfxs4y3pnu/xiuxian/20109/8/201098181350331.JPG");
    }
    MyHorizontalScrollView horizontalScrollView= (MyHorizontalScrollView) findViewById(R.id.horizontalScrollView_tuijian);
    horizontalScrollView.initDatas(new CommonAdapter<String>(this,list,R.layout.item_gridview_tuijian) {
        @Override
        public void convert(ViewHolder viewHolder, String item) {
            viewHolder.setImageBitmap(R.id.Imageview_tuijian,item);
        }
    });
}


}

package com.example.movie.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.Adapter.CommonAdapter;
import com.example.movie.Adapter.ViewHolder;
import com.example.movie.Bean.BoFangInfo;
import com.example.movie.DB.DBManager;
import com.example.movie.R;
import com.example.movie.Utils.HttpCallBack;
import com.example.movie.Utils.HttpUtil;
import com.example.movie.View.MediaController;
import com.example.movie.View.MyHorizontalScrollView;
import com.example.movie.View.VideoView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * Created by 潇舰 on 2016/7/10.
 */
public class MoviesPlayActivity extends BaseActivity implements MediaController.onClickIsFullScreenListener, View.OnClickListener {
    private MediaController mController;
    private RelativeLayout rlDD;
    private VideoView viv;
    private TextView tv_name, tv_zhuyan, tv_daoyan, tv_nianfen, tv_pingfen, tv_leixing, tv_jianjie;
    private ImageView image_collect, image_share;
    private DBManager dbManager;
    private MyHorizontalScrollView horizontalScrollView;
    private int[] arr = new int[17];
    List<Integer> list = new ArrayList<>();
    private String path;
    private String score;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                path = info.getD_playurl();
                tv_name.setText(info.getD_name());
                tv_nianfen.setText(info.getD_year());
                tv_pingfen.setText(info.getD_score());
                tv_jianjie.setText(info.getD_content());
                tv_zhuyan.setText(info.getD_starring());
                tv_daoyan.setText(info.getD_directed());
                tv_leixing.setText(info.getT_name());
                vidoePlay();
            }
        }
    };

    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_moviesplay);
    }

    @Override
    public void afterinitView() {
        setadapter();
        setTuijianadapter();
        getData();

    }

    @Override
    public void beforInitView() {

    }

    int id;

    @Override
    public void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        addData();
        horizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.id_horizontalScrollView);
        viv = (VideoView) findViewById(R.id.videoView);
        rlDD = (RelativeLayout) findViewById(R.id.rl_dd);
        tv_name = (TextView) findViewById(R.id.textView_name);
        tv_zhuyan = (TextView) findViewById(R.id.textView_zhuyan_details);
        tv_daoyan = (TextView) findViewById(R.id.textView_daoyan_details);
        tv_nianfen = (TextView) findViewById(R.id.textView_nianfen_details);
        tv_pingfen = (TextView) findViewById(R.id.textView_pingfen_details);
        tv_leixing = (TextView) findViewById(R.id.textView_leixing_details);
        tv_jianjie = (TextView) findViewById(R.id.textView_jianjie);
        image_collect = (ImageView) findViewById(R.id.imageView_collect);
        image_share = (ImageView) findViewById(R.id.imageView_share);
        mController = new MediaController(this);
        mController.setClickIsFullScreenListener(this);
        image_collect.setOnClickListener(this);
        image_share.setOnClickListener(this);

    }

    public void vidoePlay() {
        viv.setMediaController(this.mController);
//        viv.setVideoURI(Uri.parse("android.resource://" + getPackageName()
//                + "/" + R.raw.apple));
//        viv.setVideoURI(Uri.parse("http://baobab.wdjcdn.com/145076769089714.mp4"));
        viv.setVideoURI(Uri.parse(path));
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
     * 模仿获取电视集数
     */
    public void addData() {
        for (int i = 0; i < arr.length; i++) {
            list.add(i + 1);
        }
        Log.e("msg", arr.length + "");
    }

    /**
     * 电视集数适配
     */

    public void setadapter() {
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
    public void setTuijianadapter() {
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add("http://pic5.nipic.com/20100129/2714851_224213001047_2.jpg");
            list.add("http://www.232947.cc/imgall/nfwwoltcmvxgi2lcmfxs4y3pnu/xiuxian/20109/8/201098181350331.JPG");
        }
        MyHorizontalScrollView horizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.horizontalScrollView_tuijian);
        horizontalScrollView.initDatas(new CommonAdapter<String>(this, list, R.layout.item_gridview_tuijian) {
            @Override
            public void convert(ViewHolder viewHolder, String item) {
                viewHolder.setImageBitmap(R.id.Imageview_tuijian, item);
            }
        });
    }

    BoFangInfo info;

    public void getData() {
        HttpUtil.getBoFang(this, id, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("msg", "result=  " + result);
                Gson gson = new Gson();
                info = gson.fromJson(result, BoFangInfo.class);
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    /**
     * 收藏方法
     */
    private List<BoFangInfo> list_collect;

    public void Collect() {
        dbManager = DBManager.getInstance(this);
        list_collect = dbManager.look_Data();

        if (list_collect.isEmpty() == true) {
            dbManager.add_Data(info);
            Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < list_collect.size(); i++) {
            Log.e("msg", "list_collect.get(i).getD_name()=  " + list_collect.get(i).getD_name());
            Log.e("msg", "info.getD_name()=  " + info.getD_name());
            if (i == list_collect.size() - 1 && !list_collect.get(i).getD_name().equals(info.getD_name())) {
                dbManager.add_Data(info);
                Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                return;
            }
            if (list_collect.get(i).getD_name().equals(info.getD_name())) {
                Toast.makeText(this, "此条目已被收藏", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    /**
     * 分享
     */
    private void ShowShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_collect:
                Collect();
                break;
            case R.id.imageView_share:
                ShowShare();
                break;
        }
    }
}

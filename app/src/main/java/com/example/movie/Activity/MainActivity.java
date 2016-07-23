package com.example.movie.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.movie.Bean.AllSaveData;
import com.example.movie.Bean.ShouyeInfo;
import com.example.movie.Fragment.HomeFragment;
import com.example.movie.Fragment.MeFragment;
import com.example.movie.Fragment.VideoShareFragment;
import com.example.movie.R;
import com.example.movie.Utils.HttpCallBack;
import com.example.movie.Utils.HttpUtil;
import com.google.gson.Gson;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private ImageView img_me;
    private ImageView img_share;
    private ImageView img_video;
    private LinearLayout layout_me;
    private LinearLayout layout_share;
    private LinearLayout layout_video;
    private MeFragment meFragment;
    private TextView text_me;
    private TextView text_share;
    private TextView text_video;
    private VideoShareFragment videoShareFragment;

    private void hidefragment(FragmentTransaction paramFragmentTransaction) {
        if (this.homeFragment != null)
            paramFragmentTransaction.hide(this.homeFragment);
        if (this.videoShareFragment != null)
            paramFragmentTransaction.hide(this.videoShareFragment);
        if (this.meFragment != null)
            paramFragmentTransaction.hide(this.meFragment);
    }

    private void initSelection() {
        img_video.setImageResource(R.drawable.shouye);
        img_share.setImageResource(R.drawable.fenx);
        img_me.setImageResource(R.drawable.wo);
        text_video.setTextColor(0xff272636);
        text_share.setTextColor(0xff272636);
        text_me.setTextColor(0xff272636);
    }

    private void setTableSelection(int i) {
        initSelection();
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        hidefragment(fragmentTransaction);
        switch (i) {
            case 0:
                img_video.setImageResource(R.drawable.shouye_1);
                text_video.setTextColor(0xff992222);
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.main_framelayout, homeFragment);
                } else {
                    fragmentTransaction.show(this.homeFragment);

                }
                break;

            case 1:
                img_share.setImageResource(R.drawable.fenxh_1);
                text_share.setTextColor(0xff992222);
                if (videoShareFragment == null) {
                    videoShareFragment = new VideoShareFragment();
                    fragmentTransaction.add(R.id.main_framelayout, videoShareFragment);
                } else {
                    fragmentTransaction.show(videoShareFragment);
                }
                break;
            case 2:
                img_me.setImageResource(R.drawable.wo_1);
                text_me.setTextColor(0xff992222);
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    fragmentTransaction.add(R.id.main_framelayout, meFragment);
                } else {
                    fragmentTransaction.show(meFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    public void afterinitView() {
        setTableSelection(0);
    }

    public void beforInitView() {
    }

    public void initView() {
        fragmentManager = getFragmentManager();
        layout_video = (LinearLayout) findViewById(R.id.layout_video);
        layout_share = (LinearLayout) findViewById(R.id.layout_share);
        layout_me = (LinearLayout) findViewById(R.id.layout_me);
        img_video = (ImageView) findViewById(R.id.imageView_video);
        img_share = (ImageView) findViewById(R.id.imageView_share);
        img_me = (ImageView) findViewById(R.id.imageView_me);
        text_video = (TextView) findViewById(R.id.textView_video);
        text_share = (TextView) findViewById(R.id.textView_share);
        text_me = (TextView) findViewById(R.id.textView_me);
        layout_video.setOnClickListener(this);
        layout_share.setOnClickListener(this);
        layout_me.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.layout_video:
                setTableSelection(0);
                break;
            case R.id.layout_share:
                setTableSelection(1);
                break;
            case R.id.layout_me:
                setTableSelection(2);
                break;
        }
    }

    public void setcontentView() {
        setContentView(R.layout.activity_main);
    }

}

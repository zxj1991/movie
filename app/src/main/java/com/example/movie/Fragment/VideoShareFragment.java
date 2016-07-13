package com.example.movie.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.movie.R;
import com.example.movie.Utils.HttpRequestUtil;
import com.example.movie.Utils.HttpUtil;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class VideoShareFragment extends Fragment implements View.OnClickListener {
    private FollowFragment followFragment;
    private FragmentManager fragmentManager;
    private LinearLayout layout_left;
    private LinearLayout layout_right;
    private NewsFragment newsFragment;
    private TextView text_left;
    private TextView text_right;
    private View view;

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        view = paramLayoutInflater.inflate(R.layout.videosharefragment_layout, null);
        layout_left = ((LinearLayout) view.findViewById(R.id.share_left));
        layout_right = ((LinearLayout) view.findViewById(R.id.share_right));
        text_left = ((TextView) view.findViewById(R.id.text_left));
        text_right = ((TextView) view.findViewById(R.id.text_right));
        fragmentManager = getFragmentManager();
        layout_left.setOnClickListener(this);
        layout_right.setOnClickListener(this);
        setTableSelection(0);
        return view;
    }

    private void hidefragment(FragmentTransaction fragmentTransaction) {
        if (followFragment != null)
            fragmentTransaction.hide(followFragment);
        if (newsFragment != null)
            fragmentTransaction.hide(newsFragment);
//
//
//        HttpUtil.getHouse(this, str1, str2, str3, new HttpRequestUtil.HttpCallBack() {
//            @Override
//            public void onSuccess(String result) {
//
//            }
//
//            @Override
//            public void onFailure(String error) {
//
//            }
//        });
    }

    private void initselector() {
        layout_left.setBackgroundResource(R.drawable.nosharetopl);
        layout_right.setBackgroundResource(R.drawable.nosharetopr);
        text_left.setTextColor(0xff272636);
        text_right.setTextColor(0xff272636);
    }

    private void setTableSelection(int i) {
        initselector();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hidefragment(fragmentTransaction);
        switch (i) {
            case 0:
                layout_left.setBackgroundResource(R.drawable.sharetopl);
                text_left.setTextColor(0xffffffff);
                if (followFragment == null) {
                    followFragment = new FollowFragment();
                    fragmentTransaction.add(R.id.share_fragment, followFragment);
                } else {
                    fragmentTransaction.show(followFragment);
                }
                break;
            case 1:
                layout_right.setBackgroundResource(R.drawable.sharetopr);
                text_right.setTextColor(0xffffffff);
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    fragmentTransaction.add(R.id.share_fragment, newsFragment);
                } else {
                    fragmentTransaction.show(newsFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_left:
                setTableSelection(0);
                break;
            case R.id.share_right:
                setTableSelection(1);
                break;
        }
    }


}

package com.example.movie.Activity;

import com.example.movie.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class LoveShareActivity extends BaseActivity{
    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_love_share);
    }

    @Override
    public void afterinitView() {

    }

    @Override
    public void beforInitView() {
        ChangeTitle(1,"我爱分享");
    }

    @Override
    public void initView() {

    }




}

package com.example.movie.Activity;

import com.example.movie.R;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class ShareRecordActivity extends BaseActivity{
    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_share_record);
    }

    @Override
    public void afterinitView() {

    }

    @Override
    public void beforInitView() {
        ChangeTitle(1,"分享记录");
    }

    @Override
    public void initView() {

    }
}

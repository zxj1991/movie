package com.example.movie.Activity;

import com.example.movie.R;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class FoundActivity extends BaseActivity {
    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_found);
    }

    @Override
    public void afterinitView() {

    }

    @Override
    public void beforInitView() {
        ChangeTitle(1,"找回密码");
    }

    @Override
    public void initView() {

    }
}

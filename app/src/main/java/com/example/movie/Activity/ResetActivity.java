package com.example.movie.Activity;

import com.example.movie.R;

/**
 * 没用
 * Created by 潇舰 on 2016/7/10.
 */
public class ResetActivity extends BaseActivity {
    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    public void afterinitView() {

    }

    @Override
    public void beforInitView() {
        ChangeTitle(1,"修改密码");
    }

    @Override
    public void initView() {

    }
}

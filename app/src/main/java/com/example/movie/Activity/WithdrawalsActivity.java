package com.example.movie.Activity;

import com.example.movie.R;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class WithdrawalsActivity extends BaseActivity {
    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_withdrawals);
    }

    @Override
    public void afterinitView() {

    }

    @Override
    public void beforInitView() {
ChangeTitle(1,"我要提现");
    }

    @Override
    public void initView() {

    }
}

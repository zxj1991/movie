package com.example.movie.Activity;

import android.view.View;
import android.widget.LinearLayout;

import com.example.movie.R;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class SetActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout layout_personal;
    private LinearLayout layout_reset;
    private LinearLayout layout_security;

    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_set);
    }

    @Override
    public void afterinitView() {

    }

    @Override
    public void beforInitView() {
        ChangeTitle(1,"设置");
    }

    @Override
    public void initView() {
        layout_personal= (LinearLayout) findViewById(R.id.linear_personal);
        layout_reset= (LinearLayout) findViewById(R.id.linear_reset);
        layout_security= (LinearLayout) findViewById(R.id.linear_security);
        layout_personal.setOnClickListener(this);
        layout_reset.setOnClickListener(this);
        layout_security.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_personal:
                StartActivity(PersonalActivity.class);
                break;
            case R.id.linear_reset:
                StartActivity(FoundActivity.class);
            break;
            case R.id.linear_security:
                StartActivity(SecurityActivity.class);
            break;
        }
    }



}

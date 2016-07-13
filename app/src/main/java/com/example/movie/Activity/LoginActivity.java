package com.example.movie.Activity;

import android.view.View;
import android.widget.TextView;

import com.example.movie.R;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{
   private TextView text_forget,text_register;


    @Override
    public void setcontentView() {
setContentView(R.layout.activity_login);
    }

    @Override
    public void afterinitView() {

    }

    @Override
    public void beforInitView() {
        ChangeTitle(2,"登录");
    }

    @Override
    public void initView() {
        text_forget= (TextView) findViewById(R.id.textView_forget);
        text_register= (TextView) findViewById(R.id.textView_register);
        text_forget.setOnClickListener(this);
        text_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView_forget:
                StartActivity(FoundActivity.class);
            break;

            case R.id.textView_register:
                StartActivity(RegisterActivity.class);
                break;
        }
    }
}

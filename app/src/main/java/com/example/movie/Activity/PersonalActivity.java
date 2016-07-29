package com.example.movie.Activity;

import android.util.Log;
import android.widget.EditText;

import com.example.movie.Bean.User;
import com.example.movie.R;
import com.example.movie.Utils.HttpCallBack;
import com.example.movie.Utils.HttpUtil;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class PersonalActivity extends BaseActivity {
    private EditText edit_nicheng, edit_shengri, edit_qianming;
    private User user;

    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_personal);
    }

    @Override
    public void afterinitView() {
//        getData();
    }

    @Override
    public void beforInitView() {
        ChangeTitle(1, "个人资料");
    }

    @Override
    public void initView() {
        user = User.getInstance();
        edit_nicheng = (EditText) findViewById(R.id.edit_nicheng);
        edit_shengri = (EditText) findViewById(R.id.edit_shengri);
        edit_qianming = (EditText) findViewById(R.id.edit_qianming);
        edit_nicheng.setText(user.username);
        edit_shengri.setText(user.usersex);
        edit_qianming.setText(user.usersignature);
        Log.e("msg", "edit_qianming:"+String.valueOf(edit_qianming.getText()));

    }


    /**
     * 获取个人信息
     */
    public void getData() {
        Log.e("msg","个人："+user.userphone);
        HttpUtil.getPersonal(this, user.userphone, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                saveLoginData(result);
                Log.e("msg", "查看个人资料：" + result);

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }


    /**
     * 编辑个人信息
     */
    public void setDtat() {
        HttpUtil.getEditpersonal(this, user.userphone, String.valueOf(edit_nicheng.getText()), new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                getData();
                Log.e("msg", "编辑个人资料：" + result);

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("msg", "暂停");
        setDtat();

    }
}

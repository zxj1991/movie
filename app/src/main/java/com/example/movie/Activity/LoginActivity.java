package com.example.movie.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.Bean.User;
import com.example.movie.R;
import com.example.movie.Utils.HttpCallBack;
import com.example.movie.Utils.HttpUtil;
import com.example.movie.Utils.JSONParser;

import java.util.List;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView text_forget, text_register;
    private EditText edit_phone, edit_password;
    private LinearLayout bt_login;

    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void afterinitView() {

    }

    @Override
    public void beforInitView() {
        ChangeTitle(2, "登录");

    }

    @Override
    public void initView() {
        text_forget = (TextView) findViewById(R.id.textView_forget);
        text_register = (TextView) findViewById(R.id.textView_register);
        edit_phone = (EditText) findViewById(R.id.edit_login_phone);
        edit_password = (EditText) findViewById(R.id.edit_login_password);
        bt_login = (LinearLayout) findViewById(R.id.button_login);
        text_forget.setOnClickListener(this);
        text_register.setOnClickListener(this);
        bt_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_forget:
                StartActivity(FoundActivity.class);
                break;

            case R.id.textView_register:
//                StartActivity(RegisterActivity.class);
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), 1);
                break;
            case R.id.button_login:
                Log.e("msg", "登录");
                getData(String.valueOf(edit_phone.getText()), String.valueOf(edit_password.getText()));

                break;
        }
    }

    public void getData(final String phone, String password) {
        HttpUtil.getLogin(this, phone, password, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("msg", result);
                if (result.substring(1, result.length() - 1).equals("success")) {
                    Toast.makeText(mAactivity, "登录成功", Toast.LENGTH_SHORT).show();
//                    user.isLogin = true;
//                    user.userphone=phone;
                    getLoginSu();
                    Intent intent = new Intent();
                    intent.putExtra("pic", R.drawable.boyhead);
                    intent.putExtra("phone", String.valueOf(edit_phone.getText()));
                    LoginActivity.this.setResult(RESULT_OK, intent);
                    finish();

                } else {
                    showToast("密码或手机号码不符，请重新登录");
                }
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    /**
     * 得到注册的手机号码
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            edit_phone.setText(data.getExtras().getString("phone"));
            edit_password.setText(data.getExtras().getString("password"));
        }

    }

    /**
     * 登录成功获取用户信息
     */
    public void getLoginSu() {
        HttpUtil.getPersonal(this, String.valueOf(edit_phone.getText()), new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("msg", "登录成功返回值=" + result);
                saveLoginData(result);
                Log.e("msg","登录："+JSONParser.getStringFromJsonString("z_tel",result));
                user.isLogin = true;

            }

            @Override
            public void onFailure(String error) {
            }
        });
    }

}

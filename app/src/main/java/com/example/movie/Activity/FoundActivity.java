package com.example.movie.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.R;
import com.example.movie.Utils.HttpCallBack;
import com.example.movie.Utils.HttpUtil;
import com.example.movie.Utils.Regular;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class FoundActivity extends BaseActivity {

    private EditText edit_phone1, edit_code1, edit_password1;
    private TextView text_getcode1;
    private LinearLayout bt_sure;

    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_found);
    }

    @Override
    public void afterinitView() {
        getCode();
        Log.e("msg","123");
    }

    @Override
    public void beforInitView() {
        ChangeTitle(1, "修改密码");
    }

    @Override
    public void initView() {
        edit_phone1 = (EditText) findViewById(R.id.edit_found_phone);
        edit_code1 = (EditText) findViewById(R.id.edit_found_code);
        edit_password1 = (EditText) findViewById(R.id.edit_found_password);
        text_getcode1 = (TextView) findViewById(R.id.textView_found_getcode);
        bt_sure = (LinearLayout) findViewById(R.id.button_found_sure);
    }

    private String phone, code, password;

    /**
     * 验证
     */
    public void getCode() {
        Log.e("msg","操蛋0");
        text_getcode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("msg","操蛋1");
                phone = String.valueOf(edit_phone1.getText());
                Log.e("msg",phone);
                if (Regular.isMobile(phone)) {
                    getData(0);
                    Toast.makeText(FoundActivity.this, "请等待......", Toast.LENGTH_SHORT).show();
                    Register();

                } else {
                    Toast.makeText(FoundActivity.this, "手机号码不符合规则", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 确定
     */
    public void Register() {

        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("msg","操蛋2");
                code = String.valueOf(edit_code1.getText());
                password = String.valueOf(edit_password1.getText());
                if (code != null && password != null) {
                    getData(1);

//                    Log.e("msg", "注册");
                } else {
                    Toast.makeText(FoundActivity.this, "请完善所有数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 请求数据
     */
    public void getData(int type) {
        if (type == 0) {//验证
            HttpUtil.getForget_0(this, phone, type, new HttpCallBack() {
                @Override
                public void onSuccess(String result) {
                    Log.e("msg","验证时返回的数据="+result);
                }

                @Override
                public void onFailure(String error) {

                }
            });
        }
        if (type == 1) {//注册 success
            HttpUtil.getForget(this, phone, code, password, type, new HttpCallBack() {
                @Override
                public void onSuccess(String result) {//注册成功后，返回登录界面
//                    Log.e("msg", result.substring(1, result.length()));
//                    if (result.substring(1, result.length() - 1).equals("success")) {
////                            Log.e("zxj", "注册成功，返回登录界面");
//                        Intent intent = new Intent();
//                        intent.putExtra("phone", phone);
//                        intent.putExtra("password", password);
//                        FoundActivity.this.setResult(RESULT_OK, intent);
//                        Toast.makeText(FoundActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }

//                        Log.e("msg", "注册时返回的数据="+result);
                }

                @Override
                public void onFailure(String error) {

                }
            });

        }

    }
}

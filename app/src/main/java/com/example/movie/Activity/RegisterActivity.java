package com.example.movie.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.R;
import com.example.movie.Utils.HttpCallBack;
import com.example.movie.Utils.HttpUtil;
import com.example.movie.Utils.JSONParser;
import com.example.movie.Utils.Regular;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class RegisterActivity extends BaseActivity {
    private EditText edit_phone, edit_code, edit_password;
    private TextView text_getcode;
    LinearLayout bt_register;

    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    public void afterinitView() {
        getCode();
        Register();//
    }

    @Override
    public void beforInitView() {
        ChangeTitle(1, "注册");
    }

    @Override
    public void initView() {
        edit_phone = (EditText) findViewById(R.id.edit_register_phone);
        edit_code = (EditText) findViewById(R.id.edit_register_code);
        edit_password = (EditText) findViewById(R.id.edit_register_password);
        text_getcode = (TextView) findViewById(R.id.textView_getcode);
        bt_register= (LinearLayout) findViewById(R.id.button_register);
    }


    /**
     * 验证
     */
public void getCode(){
    text_getcode.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            phone=String.valueOf(edit_phone.getText());

            if (Regular.isMobile(phone)){
                getData(0);
                Toast.makeText(RegisterActivity.this,"请等待......",Toast.LENGTH_SHORT).show();
                Register();
            }else{
                Toast.makeText(RegisterActivity.this,"手机号码不符合规则",Toast.LENGTH_SHORT).show();
            }
        }
    });
}
    private String phone, code, password;

    /**
     * 注册
     */
    public void Register(){
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code= String.valueOf(edit_code.getText());
                password= String.valueOf(edit_password.getText());
                if (code!=null&&password!=null){
                    getData(1);
                } else{
                    Toast.makeText(RegisterActivity.this,"请完善所有数据",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    /**
     * 请求数据
     */
    public void getData(int type) {
        if(type==0) {//验证
            HttpUtil.getRegister_0(this, phone, type,new HttpCallBack() {
                @Override
                public void onSuccess(String result) {
//                    Log.e("msg","验证时返回的数据="+result);
                }

                @Override
                public void onFailure(String error) {

                }
            });
        }
        if (type==1) {//注册 success
                HttpUtil.getRegister(this, phone, code, password, type, new HttpCallBack() {
                    @Override
                    public void onSuccess(String result) {//注册成功后，返回登录界面
                        Log.e("msg",result.substring(1,result.length())+"111111");
//                        Log.e("msg",JSONParser.getStringFromJsonString("gll", result)+"2222222");
                        if (result.substring(1,result.length()-1).equals("success")){
//                            Log.e("zxj", "注册成功，返回登录界面");
                            Intent intent=new Intent();
                            intent.putExtra("phone", phone);
                            intent.putExtra("password", password);
                            RegisterActivity.this.setResult(RESULT_OK, intent);
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            finish();
//

                        }else if(result.substring(2,5).equals("gll")){//若用户已注册
                            Toast.makeText(RegisterActivity.this,"用户已存在",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });

        }

    }

}

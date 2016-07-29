package com.example.movie.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.Bean.User;
import com.example.movie.R;
import com.example.movie.Utils.ActivityTaskManeger;
import com.example.movie.Utils.JSONParser;
import com.google.gson.Gson;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public abstract class BaseActivity extends Activity {
    public User user = User.getInstance();
    public static Activity mAactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentView();
        beforInitView();
        initView();
        afterinitView();
        mAactivity = this;
        ActivityTaskManeger.getInstance().addActivity(this);

    }

    Toast toast;

    public void showToast(String str) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

    public abstract void setcontentView();

    public abstract void afterinitView();

    public abstract void beforInitView();

    public abstract void initView();


    public void ChangeTitle(int i, String string) {
        TextView text_title = (TextView) findViewById(R.id.apptitle);
        TextView text_set = (TextView) findViewById(R.id.textView_set);
        TextView text_register = (TextView) findViewById(R.id.textView_register);
        ImageView image_back = (ImageView) findViewById(R.id.back);
        text_title.setText(string);
        if (i == 1) {
            text_set.setVisibility(View.INVISIBLE);
            image_back.setVisibility(View.VISIBLE);
        }
        if (i == 2) {
            text_set.setVisibility(View.INVISIBLE);
            text_register.setVisibility(View.VISIBLE);
            image_back.setVisibility(View.VISIBLE);
        }
        image_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void StartActivity(Class<?> paramClass) {
        startActivity(new Intent(this, paramClass));
    }

    public void saveLoginData(String result) {

        Log.i("result", result);
        user.userphone = JSONParser.getStringFromJsonString("z_tel", result);
        Log.e("msg", "user.userphone" + user.userphone);
        user.username = JSONParser.getStringFromJsonString("z_name", result);
        user.usersex = JSONParser.getStringFromJsonString("z_sex", result);
        user.userhead=JSONParser.getStringFromJsonString("z_pic",result);
        user.userbirthday=JSONParser.getStringFromJsonString("z_birthday",result);
        user.usersignature=JSONParser.getStringFromJsonString("z_signature",result);
    }


}

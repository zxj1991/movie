package com.example.movie.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movie.R;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentView();
        beforInitView();
        initView();
        afterinitView();

    }

    public abstract void setcontentView();

    public abstract void afterinitView();

    public abstract void beforInitView();

    public abstract void initView();


    public void ChangeTitle(int i, String string) {
        TextView text_title = (TextView) findViewById(R.id.apptitle);
        TextView text_set= (TextView) findViewById(R.id.textView_set);
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

    public void StartActivity(Class<?> paramClass)
    {
        startActivity(new Intent(this, paramClass));
    }

}

package com.example.movie.Activity;

import android.view.View;
import android.widget.TextView;

import com.example.movie.R;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class SearchActivity extends BaseActivity {
    private TextView textView;

    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_search);
    }

    @Override
    public void afterinitView() {
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        textView = (TextView) findViewById(R.id.search_exit);
    }
}

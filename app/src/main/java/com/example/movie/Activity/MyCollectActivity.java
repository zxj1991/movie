package com.example.movie.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.movie.Adapter.CommonAdapter;
import com.example.movie.Adapter.ViewHolder;
import com.example.movie.Bean.BoFangInfo;
import com.example.movie.DB.DBManager;
import com.example.movie.R;

import java.util.List;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class MyCollectActivity extends BaseActivity {
    private ListView listView;
    Button bt;

    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_my_collect);
    }

    List<BoFangInfo> list;

    @Override
    public void afterinitView() {
        list = DBManager.getInstance(this).look_Data();
        setListView();
    }

    @Override
    public void beforInitView() {
        ChangeTitle(1, "我的收藏");
    }

    @Override
    public void initView() {
        listView = (ListView) findViewById(R.id.listview_collect);


    }

    /**
     * 适配、点击
     */
    public void setListView() {
        listView.setAdapter(new CommonAdapter<BoFangInfo>(MyCollectActivity.this, list, R.layout.item_search) {
            @Override
            public void convert(final ViewHolder viewHolder, BoFangInfo item) {
                viewHolder.setImageBitmap(R.id.imageView_search, item.getD_pic());
                viewHolder.setText(R.id.textView_search_name, item.getD_name());
                viewHolder.setText(R.id.textView_search_actor, item.getD_starring());
                viewHolder.setText(R.id.textView_search_year, item.getD_year());
                bt = viewHolder.getView(R.id.button_bofang);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        int i = Integer.parseInt(list.get(viewHolder.getPosition()).getD_id());
                        Log.e("msg", i + "");
                        intent.putExtra("id", i);
                        intent.setClass(MyCollectActivity.this, MoviesPlayActivity.class);
                        startActivity(intent);
                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent();
                        int i = Integer.parseInt(list.get(position).getD_id());
                        Log.e("msg", i + "");
                        intent.putExtra("id", i);
                        intent.setClass(MyCollectActivity.this, MoviesPlayActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });
    }
}

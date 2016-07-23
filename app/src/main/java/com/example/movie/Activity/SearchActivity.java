package com.example.movie.Activity;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.Adapter.CommonAdapter;
import com.example.movie.Adapter.ViewHolder;
import com.example.movie.Bean.FenleiInfo;
import com.example.movie.Bean.SearchInfo;
import com.example.movie.R;
import com.example.movie.Utils.HttpCallBack;
import com.example.movie.Utils.HttpUtil;
import com.example.movie.Utils.JSONParser;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class SearchActivity extends BaseActivity {
    private TextView textView, textView_name, textView_actor, textView_year, textView_leixing;
    private ImageView imageView_search;
    private EditText editText_search;
    private ListView listView;

    @Override
    public void setcontentView() {
        setContentView(R.layout.activity_search);
    }

    @Override
    public void afterinitView() {
        getEditText();
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
        editText_search = (EditText) findViewById(R.id.editText_search);
        textView_name = (TextView) findViewById(R.id.textView_search_name);
        textView_actor = (TextView) findViewById(R.id.textView_search_actor);
        textView_year = (TextView) findViewById(R.id.textView_search_year);
        textView_leixing = (TextView) findViewById(R.id.textView_search_leixing);
        imageView_search = (ImageView) findViewById(R.id.imageView_search);
        listView = (ListView) findViewById(R.id.listview_search);
    }


    public void getData() {
        HttpUtil.getsearch(this, str, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("zxj123", result);
                if (!result.substring(2,7).equals("false")) {
                    Log.e("msg", "");
                    List<SearchInfo> info = SearchInfo.arraySearchInfoFromData(result);
                    listView.setAdapter(new CommonAdapter<SearchInfo>(SearchActivity.this, info, R.layout.item_search) {
                        @Override
                        public void convert(ViewHolder viewHolder, SearchInfo item) {
                            viewHolder.setImageBitmap(R.id.imageView_search, item.getD_pic());
                            viewHolder.setText(R.id.textView_search_name, item.getD_name());
                            viewHolder.setText(R.id.textView_search_actor, item.getD_starring());
                            viewHolder.setText(R.id.textView_search_year, item.getD_year());


                        }
                    });
                } else {
                    Log.e("msg", "弹出土司");
                    Toast.makeText(SearchActivity.this, "没有搜索到内容", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    String str;

    public void getEditText() {

        editText_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    str = String.valueOf(editText_search.getText());
                    Log.e("zxj", str);
                    getData();
                }

                return false;
            }
        });
    }

}

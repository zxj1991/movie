package com.example.movie.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.movie.Activity.BaseActivity;
import com.example.movie.Activity.HelpActivity;
import com.example.movie.Activity.LoginActivity;
import com.example.movie.Activity.LoveShareActivity;
import com.example.movie.Activity.MyCollectActivity;
import com.example.movie.Activity.PersonalActivity;
import com.example.movie.Activity.PresentRecordActivity;
import com.example.movie.Activity.SetActivity;
import com.example.movie.Activity.ShareRecordActivity;
import com.example.movie.Activity.WithdrawalsActivity;
import com.example.movie.Bean.User;
import com.example.movie.R;
import com.example.movie.Utils.HttpCallBack;
import com.example.movie.Utils.HttpUtil;
import com.example.movie.Utils.JSONParser;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    private ImageView image_caidan;
    private Intent intent;
    private LinearLayout layout_help;
    private RelativeLayout layout_login;
    private LinearLayout layout_loveshare;
    private LinearLayout layout_mycollect;
    private LinearLayout layout_presentrecord;
    private LinearLayout layout_sharerecord;
    private LinearLayout layout_withdrawals;
    private LinearLayout layout_zhanghu;
    private TextView text_set;
    private TextView title;
    private TextView text_tishi, text_balance, text_withdrawal;
    private View view;
    private ImageView image_head;
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        view = paramLayoutInflater.inflate(R.layout.mefragment_layout, null);
        user = User.getInstance();
        init();
        Title();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    void init() {
        text_set = ((TextView) view.findViewById(R.id.textView_set));
        text_tishi = (TextView) view.findViewById(R.id.text_tishi);
        text_balance = (TextView) view.findViewById(R.id.text_balance);
        text_withdrawal = (TextView) view.findViewById(R.id.text_withdrawal);
        layout_zhanghu = (LinearLayout) view.findViewById(R.id.linear_zhanghu);
        layout_login = (RelativeLayout) view.findViewById(R.id.linear_login);
        layout_loveshare = ((LinearLayout) view.findViewById(R.id.linear_love_share));
        layout_mycollect = ((LinearLayout) view.findViewById(R.id.linear_my_collect));
        layout_help = ((LinearLayout) view.findViewById(R.id.linear_help));
        layout_withdrawals = ((LinearLayout) view.findViewById(R.id.linear_withdrawals));
        layout_presentrecord = ((LinearLayout) view.findViewById(R.id.linear_present_record));
        layout_sharerecord = ((LinearLayout) view.findViewById(R.id.linear_share_record));
        image_head = (ImageView) view.findViewById(R.id.person_icon);
        image_head.setOnClickListener(this);
        text_set.setOnClickListener(this);
        layout_login.setOnClickListener(this);
        layout_loveshare.setOnClickListener(this);
        layout_mycollect.setOnClickListener(this);
        layout_help.setOnClickListener(this);
        layout_withdrawals.setOnClickListener(this);
        layout_presentrecord.setOnClickListener(this);
        layout_sharerecord.setOnClickListener(this);
    }


    public void Title() {
        title = (TextView) view.findViewById(R.id.apptitle);
        title.setText("个人中心");
        text_set.setVisibility(View.VISIBLE);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_set:
                intent = new Intent(getActivity(), SetActivity.class);
                startActivity(intent);
                break;
            case R.id.person_icon:
                if (!user.isLogin) {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1);
                } else {
                    Intent intent = new Intent(getActivity(), PersonalActivity.class);
                    startActivity(intent);
//                    intent.putExtra("phone", phone);
//                    intent.setClass(getActivity(), PersonalActivity.class);
//                    startActivityForResult(intent, 1);

                }

                break;
            case R.id.linear_love_share:
                intent = new Intent(getActivity(), LoveShareActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_my_collect:
                intent = new Intent(getActivity(), MyCollectActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_help:
                intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_withdrawals:
                intent = new Intent(getActivity(), WithdrawalsActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_present_record:
                intent = new Intent(getActivity(), PresentRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_share_record:
                intent = new Intent(getActivity(), ShareRecordActivity.class);
                startActivity(intent);
                break;
        }
    }


    /**
     * 获取用户信息
     */
    public void getData() {
        String phone=User.getInstance().userphone;
        Log.e("msg","phone="+phone);
        HttpUtil.getPersonal(getActivity(), phone, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("msg","个人中心获取资料="+result);

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }


    String phone;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            phone = data.getExtras().getString("phone");
            image_head.setImageResource(data.getExtras().getInt("pic"));

            text_tishi.setVisibility(View.GONE);
            layout_zhanghu.setVisibility(View.VISIBLE);

        }
    }
}

package com.example.movie.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.movie.Activity.HelpActivity;
import com.example.movie.Activity.LoginActivity;
import com.example.movie.Activity.LoveShareActivity;
import com.example.movie.Activity.MyCollectActivity;
import com.example.movie.Activity.PresentRecordActivity;
import com.example.movie.Activity.SetActivity;
import com.example.movie.Activity.ShareRecordActivity;
import com.example.movie.Activity.WithdrawalsActivity;
import com.example.movie.R;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    private ImageView image_caidan;
    private Intent intent;
    private LinearLayout layout_help;
    private LinearLayout layout_login;
    private LinearLayout layout_loveshare;
    private LinearLayout layout_mycollect;
    private LinearLayout layout_presentrecord;
    private LinearLayout layout_sharerecord;
    private LinearLayout layout_withdrawals;
    private TextView text_set;
    private TextView title;
    private View view;

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        this.view = paramLayoutInflater.inflate(R.layout.mefragment_layout, null);
        this.text_set = ((TextView)this.view.findViewById(R.id.textView_set));
        this.layout_login = (LinearLayout) view.findViewById(R.id.linear_login);
        this.layout_loveshare = ((LinearLayout)this.view.findViewById(R.id.linear_love_share));
        this.layout_mycollect = ((LinearLayout)this.view.findViewById(R.id.linear_my_collect));
        this.layout_help = ((LinearLayout)this.view.findViewById(R.id.linear_help));
        this.layout_withdrawals = ((LinearLayout)this.view.findViewById(R.id.linear_withdrawals));
        this.layout_presentrecord = ((LinearLayout)this.view.findViewById(R.id.linear_present_record));
        this.layout_sharerecord = ((LinearLayout)this.view.findViewById(R.id.linear_share_record));
        this.text_set.setOnClickListener(this);
        this.layout_login.setOnClickListener(this);
        this.layout_loveshare.setOnClickListener(this);
        this.layout_mycollect.setOnClickListener(this);
        this.layout_help.setOnClickListener(this);
        this.layout_withdrawals.setOnClickListener(this);
        this.layout_presentrecord.setOnClickListener(this);
        this.layout_sharerecord.setOnClickListener(this);
        Title();
        return this.view;
    }


    public void Title()
    {
        this.title = (TextView)this.view.findViewById(R.id.apptitle);
        this.title.setText("个人中心");
        this.text_set.setVisibility(View.VISIBLE);
//        this.image_caidan.setVisibility(View.INVISIBLE);
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.textView_set:
                this.intent = new Intent(getActivity(), SetActivity.class);
                startActivity(this.intent);
                break;
            case R.id.linear_login:
                this.intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(this.intent);
                break;
            case R.id.linear_love_share:
                this.intent = new Intent(getActivity(), LoveShareActivity.class);
                startActivity(this.intent);
                break;
            case R.id.linear_my_collect:
                this.intent = new Intent(getActivity(), MyCollectActivity.class);
                startActivity(this.intent);
                break;
            case R.id.linear_help:
                this.intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(this.intent);
                break;
            case R.id.linear_withdrawals:
                this.intent = new Intent(getActivity(), WithdrawalsActivity.class);
                startActivity(this.intent);
                break;
            case R.id.linear_present_record:
                this.intent = new Intent(getActivity(), PresentRecordActivity.class);
                startActivity(this.intent);
                break;
            case R.id.linear_share_record:
                this.intent = new Intent(getActivity(), ShareRecordActivity.class);
                startActivity(this.intent);
                break;
        }
    }



}

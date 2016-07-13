package com.example.movie.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movie.R;

/**
 * Created by 潇舰 on 2016/7/10.
 */
public class FollowFragment extends Fragment {
    private View view;

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.view = paramLayoutInflater.inflate(R.layout.followfragment_layout, paramViewGroup, false);
        return this.view;
    }
}
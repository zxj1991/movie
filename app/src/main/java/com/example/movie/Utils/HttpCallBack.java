package com.example.movie.Utils;

/**
 * 网络请求返回结果 回调接口
 * Created by Administrator on 2016/7/18 0018.
 */
public interface HttpCallBack {
    public void onSuccess(String result);

    public void onFailure(String error);
}

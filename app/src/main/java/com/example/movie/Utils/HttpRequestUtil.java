package com.example.movie.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.io.File;
import java.util.Map;

/**
 * 网络请求工具类
 * Created by Administrator on 2016/7/11 0011.
 */
public class HttpRequestUtil {
    public static final String URL="";
    private static HttpUtils http;
    static {
        http=new HttpUtils();
    }

    /**
     * 发送请求
     *
     * @param method
     *            请求类型 Post或Get
     * @param url
     *            请求地址
     * @param map
     *            请求参数
     * @param mHttpCallBack
     *            回调接口
     */
    public static void send(final Context context,HttpMethod method,String url,Map<String,Object> map,
                            final HttpCallBack mHttpCallBack){
//        url = ConstantParser.HTTP_URI + url;
//        Log.i("url", url);

        http.configTimeout(60 * 1000);
        http.configRequestThreadPoolSize(10);
        http.send(method, url, parserMap(method, map),
                new RequestCallBack<String>() {
                    @Override
                    public void onLoading(long total, long current,
                                          boolean isUploading) {
                        if (isUploading) {
                            System.out.println("upload: " + current + "/"
                                    + total);
                        } else {
                            System.out.println("reply: " + current + "/"
                                    + total);
                        }
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        mHttpCallBack.onSuccess(responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        mHttpCallBack.onFailure(msg);
                        error.printStackTrace();
                        Log.e("error", "msg=" + msg);
                        Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
                    }

                });
    }

    public static void send1(final Context context, HttpMethod method,
                             String url, Map<String, Object> map,
                             final HttpCallBack mHttpCallBack) {
        url = "http://sjskrbb.com/Application/pay/PaySubmit.php";
        Log.i("url", url);

        http.configTimeout(60 * 1000);
        http.configRequestThreadPoolSize(10);
        http.send(method, url, parserMap(method, map),
                new RequestCallBack<String>() {
                    @Override
                    public void onLoading(long total, long current,
                                          boolean isUploading) {
                        if (isUploading) {
                            System.out.println("upload: " + current + "/"
                                    + total);
                        } else {
                            System.out.println("reply: " + current + "/"
                                    + total);
                        }
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        mHttpCallBack.onSuccess(responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        mHttpCallBack.onFailure(msg);
                        error.printStackTrace();
                        Log.e("error", "msg=" + msg);
                        Toast.makeText(context, "请求失败", Toast.LENGTH_SHORT).show();
                    }

                });

    }

    /**
     * 解析map集合 并封装到RequestParams对象
     *
     * @param map
     * @return
     */
    private static RequestParams parserMap(HttpMethod method , Map<String, Object> map) {
        if (map == null || map.size() <= 0) {
            return null;
        }
        // 设置请求参数的编码
        RequestParams params = new RequestParams("UTF-8");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
//			System.out.println("key= " + entry.getKey() + " and value= "
//					+ entry.getValue());
            Log.i("params","key= " + entry.getKey() + " and value= "
                    + entry.getValue());
            if (TextUtils.equals(ConstantParser.ACCESS_TOKEN, entry.getKey())) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue()
                        + "");
            } else if (entry.getValue() instanceof File) {
                params.addBodyParameter(entry.getKey(),
                        (File) entry.getValue(), "image/jpeg");
            } else if (method == HttpMethod.POST) {
                params.addBodyParameter(entry.getKey(), entry.getValue() + "");
            } else if (method == HttpMethod.GET) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue()
                        + "");
            }
        }
        return params;
    }



    /**
     * 网络请求返回结果，回调接口
     */
    public interface HttpCallBack{
        public void onSuccess(String result);
        public void onFailure(String error);

    }
}

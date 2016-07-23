package com.example.movie.Utils;

import android.content.Context;


import com.lidroid.xutils.http.client.HttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求方法类
 * Created by Administrator on 2016/7/11 0011.
 */
public class HttpUtil {
//    public static void getHouse(Context context,
//                                String str1,//传入参数1
//                                String str2,//传入参数2
//                                String str3,//传入参数3
//                                HttpRequestUtil.HttpCallBack callBack) {//接口回调返回值
//        Map<String, Object> map = new HashMap<String, Object>();//map把参数打包起来一起上传
//        map.put("参数1", str1);//请求参数名 + 传入的参数
//        map.put("参数1", str2);
//        map.put("参数1", str3);
//        HttpRequestUtil.send(context,
//                HttpRequest.HttpMethod.POST,//xUtils传参方式 GET,POST,PUT,HEAD,MOVE,COPY,DELETE,OPTIONS,TRACE,CONNECT
//                ConstantParser.LOGIN,//请求地址 除开地址前半部分服务器地址 直接拼接除开服务器后面的地址
//                /** 例如
//                 * public static final String HTTP_HANDER = "http://sjskrbb.com/index.php/"  前半部分服务器地址
//                 * 后面拼接
//                 *  登录:
//                 * public static final String LOGIN = "login/index";
//                 */
//                map,//传入参数
//                callBack);//接口接受数据
//    }

    /**
     * 获取分类界面数据
     *
     * @param context
     * @param callBack
     */
    public static void getFenLei(Context context, HttpCallBack callBack) {
        Map<String, Object> map = new HashMap<>();
        map.put("null", null);
        HttpRequestUtil.send(context, HttpRequest.HttpMethod.POST, ConstantParser.LieBiao1, map, callBack);

    }

    /**
     * 获取首页界面数据
     */
    public static void getShouye(Context context, HttpCallBack callBackack) {

        Map<String, Object> map = new HashMap<>();
        map.put("null", null);
        HttpRequestUtil.send(context, HttpRequest.HttpMethod.POST, ConstantParser.Shouye, map, callBackack);

    }

    /**
     * 搜索
     */
    public static void getsearch(Context context, String str1, HttpCallBack callBackack) {
        Map<String, Object> map = new HashMap<>();
        map.put("movies_name", str1);
        HttpRequestUtil.send(context, HttpRequest.HttpMethod.POST, ConstantParser.Search, map, callBackack);
    }

    /**
     * 登录
     */
    public static void getLogin(Context context, String phone, String password, HttpCallBack callBack) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", password);
        HttpRequestUtil.send(context, HttpRequest.HttpMethod.POST, ConstantParser.LOGIN, map, callBack);
    }

    /**
     * 验证
     */
    public static void getRegister_0(Context context, String phone,int type,HttpCallBack callBack) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("type",type);
        HttpRequestUtil.send(context, HttpRequest.HttpMethod.POST, ConstantParser.REGISTER, map, callBack);
    }

    /**
     * 注册
     */
    public static void getRegister(Context context, String phone, String code, String password, int type,HttpCallBack callBack) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("code", code);
        map.put("password", password);
        map.put("type",type);
        HttpRequestUtil.send(context, HttpRequest.HttpMethod.POST, ConstantParser.REGISTER, map, callBack);
    }

    /**
     * 忘记密码
     */
    public static void getForget(){

    }

}

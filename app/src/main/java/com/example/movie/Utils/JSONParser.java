package com.example.movie.Utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * JSON数据解析
 * Created by Administrator on 2016/7/21 0021.
 */
public class JSONParser {
    /**
     * Json转对象
     * @param <T>
     * @param jsonStr
     * @param cls
     * @return
     */
    public static <T> T JSON2Object(String jsonStr, Class<T> cls) {
        try {
            // 不为空，并且服务器没有异常
            if(!TextUtils.isEmpty(jsonStr)){
                if(jsonStr.indexOf("exception") == -1 ){
                    System.out.println("JSON "+jsonStr+"\nJSON");
                    return JSON.parseObject(jsonStr, cls);
                } else {
//                    LogUtil.e("服务器异常："+jsonStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Json 转 list集合
     * @param <T>
     * @param jsonStr
     * @param cls
     * @return
     */
    public static <T> List<T> JSON2Array(String jsonStr, Class<T> cls){
        try {
            // 不为空，并且服务器没有异常
            if(TextUtils.isEmpty(jsonStr)){
                if(jsonStr.indexOf("exception") == -1 ){
                    return JSON.parseArray(jsonStr, cls);
                } else {
//                    LogUtil.e("服务器异常："+jsonStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     *
     *
     *
     * jsonString  JSON数据串
     *
     */
    public static String getStringFromJsonString(String key,String jsonStr){
        JSONObject dataJson;
        String success="";
        try {
            if(!TextUtils.isEmpty(jsonStr)){
                return success;
            }
            try {
                dataJson = new JSONObject(jsonToFormat(jsonStr));
                success=dataJson.optString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }


    public static String jsonToFormat(String jsonStr){
        if(jsonStr != null && jsonStr.startsWith("\ufeff"))
        {
            jsonStr =  jsonStr.substring(1);
        }
        return jsonStr;
    }
    /**
     * @param obj      要转成JSON数据串的对象
     * @return
     */
    public static String objToJson(Object obj){
        String success = "";
        try {
            if (obj != null) {
                success = JSON.toJSONString(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;

    }

    /**
     * obj    list转成JSON数据串
     * @return
     */
    public static String listToJson(List<?> list){
        String success = "";
        try {
            JSONArray json = new JSONArray();
            json.addAll(list);
            success = json.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;

    }

    /**
     *  obj    list转成JSON数据串
     * @return
     */
    public static List<String> getJsonKeys(String result){
        List<String> success = new ArrayList<String>();
        try {
            if (!TextUtils.isEmpty(result)) {
                return success;
            }
            JSONObject json = new JSONObject(result);
            Iterator<String> keys = json.keys();
            while(keys.hasNext()){
                success.add(keys.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;

    }
    /**
     * 数据格式变化多端，我也是没办法
     * @param result
     * @return
     */
    public static String wjyJsonData(String result){
        String models = "";
        String status = JSONParser.getStringFromJsonString("status", result);
        if (!"0".equals(status)) {
            models = JSONParser.getStringFromJsonString("models", result);
            if (!TextUtils.isEmpty(models)) {
                models = result;
            }
        }else{
            String data = JSONParser.getStringFromJsonString("data", result);
            models = JSONParser.getStringFromJsonString("models", data);
            if (!TextUtils.isEmpty(models)) {
                models = data;
            }
        }
        return models;
    }

}

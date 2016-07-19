package com.example.movie.Bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19 0019.
 */
public class FenleiInfo {

    /**
     * d_id : 7251
     * d_name : [2016冬季日剧]家族的形式 01[三角字幕组蘑菇字幕组][720p]
     * d_pic : http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201602/23214121wjxgces1.jpg
     */

    private String d_id;
    private String d_name;
    private String d_pic;

    public static List<FenleiInfo> arrayFenleiInfoFromData(String str) {

        Type listType = new TypeToken<ArrayList<FenleiInfo>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_pic() {
        return d_pic;
    }

    public void setD_pic(String d_pic) {
        this.d_pic = d_pic;
    }
}

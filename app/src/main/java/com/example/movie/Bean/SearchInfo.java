package com.example.movie.Bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class SearchInfo{

    /**
     * d_id : 2579
     * d_name : 《中国好声音》丁丁青春演绎曲目，这位女孩唱地更唯美！爱要坦荡荡
     * d_pic : http://cdn.aixifan.com/dotnet/artemis/u/cms/www/201603/24094247v0lereji.jpg
     * d_starring :
     * d_directed :
     * d_remarks : 正片
     * d_area :
     * d_year : 2016
     * t_name : 综艺
     * d_score : 8.0
     * d_content : 火秀tv素人演唱会•时光机–阳子《爱要坦荡荡》《中国好声音》丁丁青春演绎曲目，这位女孩唱地更唯美！每周日晚七点双路实时直播：全网超清直播，4K级VR全景直播。2016，火秀tvK-POP idols亚洲海选全力开启！重金打造赴韩偶像男女天团，寻找热爱舞台的少男少女，下一颗最闪耀的新星，就是你！报名通道：一、关注微信公众号【火秀tv素人演唱会】，“底部菜单-我要参赛”，马上报名！二、 简介火秀tv素人演唱会•时光机–阳子《爱要坦荡荡》《中国好声音》丁丁青春演绎曲目，这位女孩唱地更唯美！每周日晚七点双路实时直播：全网超清直播，4K级VR全景直播。2016，火秀tvK-POP idols亚洲海选全力开启！重金打造赴韩偶像男女天团，寻找热爱舞台的少男少女，下一颗最闪耀的新星，就是你！报名通道：一、关注微信公众号【火秀tv素人演唱会】，“底部菜单-我要参赛”，马上报名！二、
     */
    private String d_id;
    private String d_name;
    private String d_pic;
    private String d_starring;
    private String d_directed;
    private String d_remarks;
    private String d_area;
    private String d_year;
    private String t_name;
    private String d_score;
    private String d_content;

    public static List<SearchInfo> arraySearchInfoFromData(String str) {
        Type listType = new TypeToken<ArrayList<SearchInfo>>() {
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

    public String getD_starring() {
        return d_starring;
    }

    public void setD_starring(String d_starring) {
        this.d_starring = d_starring;
    }

    public String getD_directed() {
        return d_directed;
    }

    public void setD_directed(String d_directed) {
        this.d_directed = d_directed;
    }

    public String getD_remarks() {
        return d_remarks;
    }

    public void setD_remarks(String d_remarks) {
        this.d_remarks = d_remarks;
    }

    public String getD_area() {
        return d_area;
    }

    public void setD_area(String d_area) {
        this.d_area = d_area;
    }

    public String getD_year() {
        return d_year;
    }

    public void setD_year(String d_year) {
        this.d_year = d_year;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getD_score() {
        return d_score;
    }

    public void setD_score(String d_score) {
        this.d_score = d_score;
    }

    public String getD_content() {
        return d_content;
    }

    public void setD_content(String d_content) {
        this.d_content = d_content;
    }
}

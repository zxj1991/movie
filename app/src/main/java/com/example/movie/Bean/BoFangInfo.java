package com.example.movie.Bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/26 0026.
 */
public class BoFangInfo {


    /**
     * d_name : 好像哪里不对09： 真男人另类奇葩要账指南
     * d_pic : http://r4.ykimg.com/05420101578D979E641DA43B75957756
     * d_starring :
     * d_directed :
     * d_remarks : 正片
     * d_area :
     * d_year : 0
     * t_name : 其他
     * d_score : 3.0
     * d_content : 为了生活，男人们各显其能去要账，非常有骨气。 简介为了生活，男人们各显其能去要账，非常有骨气。
     * d_playurl : 无米之炊$http://www.acfun.tv/v/ac2910618
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
    private String d_playurl;

    public BoFangInfo(String d_name,String d_id,String d_pic,String d_starring,String d_directed,String d_remarks,String d_area,String d_year,String t_name,String d_score,String d_content,String d_playurl) {
        this.d_playurl = d_playurl;
        this.d_content = d_content;
        this.d_score = d_score;
        this.t_name = t_name;
        this.d_area = d_area;
        this.d_year = d_year;
        this.d_remarks = d_remarks;
        this.d_directed = d_directed;
        this.d_starring = d_starring;
        this.d_pic = d_pic;
        this.d_id=d_id;
        this.d_name = d_name;
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

    public String getD_playurl() {
        return d_playurl;
    }

    public void setD_playurl(String d_playurl) {
        this.d_playurl = d_playurl;
    }
}

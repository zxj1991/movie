package com.example.movie.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/27 0027.
 */

/**
 * d_name : 电影名称
 * d_pic : 图片链接
 * d_starring :主演
 * d_directed :导演
 * d_remarks : 剧集
 * d_area :地区
 * d_year : 年份
 * t_name : 类型
 * d_score : 3.0 评分
 * d_content : 简介
 * d_playurl : 播放连接
 */
public class DBHelper extends SQLiteOpenHelper {
    public static String dbName = "movies.db";
    public static int version = 1;
    public static final String TABLE_NAME_INFO = "movies_info";
    public static final String INFO_ID = "_id";
    public static final String INFO_NAME = "movies_name";
    public static final String INFO_D_ID="movies_id";
    public static final String INFO_PIC = "movies_pic";
    public static final String INFO_STARRING = "movies_starring";
    public static final String INFO_DIRECTED = "movies_directed";
    public static final String INFO_REMARKS = "movies_remarks";
    public static final String INFO_AREA = "movies_area";
    public static final String INFO_YEAR = "movies_year";
    public static final String INFO_T_NAME = "movies_t_name";
    public static final String INFO_SCORE = "movies_score";
    public static final String INFO_CONTENT = "movies_content";
    public static final String INFO_PLAYURL = "movies_playurl";


    public DBHelper(Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//创表
        String sql_info_createtable = "CREATE TABLE " + TABLE_NAME_INFO + "(" + INFO_ID + " integer primary key autoincrement, "
                +INFO_NAME+" text, "+INFO_D_ID+" text, "+INFO_PIC+" text, "+INFO_STARRING+" text, "+INFO_DIRECTED+" text, "+INFO_REMARKS+" text, "
                +INFO_AREA+" text, "+INFO_YEAR+" text, "+INFO_T_NAME+" text, "+INFO_SCORE+" text, "+INFO_CONTENT+" text, "+INFO_PLAYURL+" text);";
        db.execSQL(sql_info_createtable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

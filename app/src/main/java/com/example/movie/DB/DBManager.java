package com.example.movie.DB;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.movie.Bean.BoFangInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class DBManager {
    private SQLiteDatabase db;
    private static DBManager dbManager;

    private DBManager(Context context) {
        DBHelper helper = new DBHelper(context);
        // 去访问数据库，如果数据库不存在则调用创建
        db = helper.getWritableDatabase();
    }

    public static DBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new DBManager(context);
        }
        return dbManager;
    }

    /**
     * 增
     */
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
    public void add_Data(BoFangInfo info) {
        String sql = "insert into " + DBHelper.TABLE_NAME_INFO + "(" + DBHelper.INFO_NAME + "," + DBHelper.INFO_D_ID + "," + DBHelper.INFO_PIC + "," + DBHelper.INFO_STARRING + ","
                + DBHelper.INFO_DIRECTED + "," + DBHelper.INFO_REMARKS + "," + DBHelper.INFO_AREA + "," + DBHelper.INFO_YEAR + ","
                + DBHelper.INFO_T_NAME + "," + DBHelper.INFO_SCORE + "," + DBHelper.INFO_CONTENT + "," + DBHelper.INFO_PLAYURL + ")values('"
                + info.getD_name() + "','" + info.getD_id() + "','" + info.getD_pic() + "','" + info.getD_starring() + "','" + info.getD_directed() + "','"
                + info.getD_remarks() + "','" + info.getD_area() + "','" + info.getD_year() + "','" + info.getT_name() + "','"
                + info.getD_score() + "','" + info.getD_content() + "','" + info.getD_playurl() + "');";
        db.execSQL(sql);
    }

    /**
     * 查
     */
    public List<BoFangInfo> look_Data() {
        String sql = "select*from " + DBHelper.TABLE_NAME_INFO;
        Cursor cursor = db.rawQuery(sql, null);
        List<BoFangInfo> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String movies_name = cursor.getString(cursor.getColumnIndex(DBHelper.INFO_NAME));
            String movies_id = cursor.getString(cursor.getColumnIndex(DBHelper.INFO_D_ID));
            String movies_pic = cursor.getString(cursor.getColumnIndex(DBHelper.INFO_PIC));
            String movies_starring = cursor.getString(cursor.getColumnIndex(DBHelper.INFO_STARRING));
            String movies_directed = cursor.getString(cursor.getColumnIndex(DBHelper.INFO_DIRECTED));
            String movies_remarks = cursor.getString(cursor.getColumnIndex(DBHelper.INFO_REMARKS));
            String movies_area = cursor.getString(cursor.getColumnIndex(DBHelper.INFO_AREA));
            String movies_year = cursor.getString(cursor.getColumnIndex(DBHelper.INFO_YEAR));
            String movies_t_name = cursor.getString(cursor.getColumnIndex(DBHelper.INFO_T_NAME));
            String movies_score = cursor.getString(cursor.getColumnIndex(DBHelper.INFO_SCORE));
            String movies_content = cursor.getString(cursor.getColumnIndex(DBHelper.INFO_CONTENT));
            String movies_playurl = cursor.getString(cursor.getColumnIndex(DBHelper.INFO_PLAYURL));
            list.add(new BoFangInfo(movies_name, movies_id, movies_pic, movies_starring, movies_directed, movies_remarks, movies_area, movies_year, movies_t_name,
                    movies_score, movies_content, movies_playurl));

        }


        cursor.close();
        return list;
    }


}

package com.example.movie.Bean;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class AllSaveData {
    private static AllSaveData data;
    private AllSaveData(){}
    public static AllSaveData getInstance(){
        if (data==null){
            data=new AllSaveData();
        }
            return data;
    }

    public ShouyeInfo info;



}

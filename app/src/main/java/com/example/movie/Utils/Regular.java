package com.example.movie.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断正则表达式的类
 * Created by Administrator on 2016/7/21 0021.
 */
public class Regular {
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^[1][3,4,5,7,8][0-9]{9}$";

    public static boolean isMobile(String phone){
        Pattern  pat = Pattern.compile(REGEX_MOBILE);
        Matcher mat = pat.matcher(phone);
        return mat.find();
    }
}

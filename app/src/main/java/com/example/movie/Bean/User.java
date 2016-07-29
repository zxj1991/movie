package com.example.movie.Bean;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class User {
    private static User user;

    private User() {

    }
    public static User getInstance() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public boolean isLogin = false;//默认未登录 登录后吧这个值改为true; 然后就可以判断是否登录
    public String userphone;//手机号
    public String username;//昵称
    public String usersex;//性别
    public String userhead;//头像
    public String userbirthday;//生日
    public String usersignature;//签名

}

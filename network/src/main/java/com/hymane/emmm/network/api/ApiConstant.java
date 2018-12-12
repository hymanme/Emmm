package com.hymane.emmm.network.api;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/11/14
 * Description: 全局接口配置
 */
public class ApiConstant {
    public static final int SUCCESS = 1000;

    public static final class Auth {
        public static final String REFRESH_TOKEN = "/auth/refreshToken";
        public static final String LOGIN = "/auth/login";
    }

    public static final class User {
        public static final String PROFILE = "/user/getUserProfile";
        public static final String LOGIN = "/user/login";
    }

    public static final class Movie {
        public static final String TOP250 = "https://api.douban.com/v2/movie/top250";
    }
}

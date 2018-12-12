package com.hymane.emmm.response.douban;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/12/12
 * Description:
 */
public class User {
    public String id;
    public String name;
    public String alt;
    public Avatar avatars;

    public static class Avatar{
        public String small;
        public String large;
        public String medium;
    }
}

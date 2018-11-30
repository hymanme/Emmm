package com.hymane.emmm.response;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/11/14
 * Description:
 */
public class UserResp extends BaseResp<UserResp.User> {
    public static class User {
        public String name;
        public int age;
        public String gender;
    }
}

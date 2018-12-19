package com.hymane.emmm.core;

import android.content.Context;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/11/13
 * Description: Emmm 初始化器
 */
public class Emmm {
    private static Context mContext;
    private static Config mBuilder;
    private static boolean initialized = false;

    /***
     * 初始化组件
     * @param context Application Context
     */
    public static void init(Context context) {
        init(context, new Config());
    }

    public static void init(Context context, Config builder) {
        if (!initialized) {
            if (context == null) {
                throw new NullPointerException("Context cannot be null value");
            }
            mContext = context;
            mBuilder = builder;
            initialized = true;
        }
    }

    public static Context getContext() {
        if (mContext == null) {
            throw new NullPointerException("Context is null value, did you forget to call init function?");
        }
        return mContext;
    }

    public static final class Config {
        private String baseUrl;

        public Config() {
            this.baseUrl = "";
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public Config baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }
    }

    public static String getBaseUrl() {
        if (mBuilder != null) {
            return mBuilder.baseUrl;
        } else {
            throw new NullPointerException("Cannot find config object, did you forget to init?");
        }
    }
}

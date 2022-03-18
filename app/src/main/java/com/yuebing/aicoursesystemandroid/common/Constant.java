package com.yuebing.aicoursesystemandroid.common;

import okhttp3.MediaType;

/**
 * 常量类
 */
public class Constant {
    /**
     * 查询是否已注册URL
     */
    public static final String IS_REGISTERED_URL="http://127.0.0.1/isRegistered";

    /**
     * 登陆URL
     */
    public static final String REGISTER_URL="http://127.0.0.1:8888/register";

    /**
     * 登陆URL
     */
    public static final String LOGIN_URL="http://127.0.0.1:8888/login";

    /**
     * BODY类型
     */
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

}

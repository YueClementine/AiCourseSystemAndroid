package com.yuebing.aicoursesystemandroid.common;

import okhttp3.MediaType;

/**
 * 常量类
 */
public class Constant {

    public static final String FORE_URL = "http://10.133.169.250:8888";
    /**
     * 查询是否已注册URL
     */
    public static final String IS_REGISTERED_URL = FORE_URL + "/isRegistered";

    /**
     * 登陆URL
     */
    public static final String REGISTER_URL = FORE_URL + "/register";

    /**
     * 登陆URL
     */
    public static final String LOGIN_URL = FORE_URL + "/login";

    /**
     * 创建课程
     */
    public static final String CREATE_COURSE = FORE_URL + "/createcourse";

    /**
     * getcoursebyuserid(task)
     */
    public static final String GET_COURSE_BY_USERID = FORE_URL + "/teachertask/getCourseByTeacherId?teacherid=";

    /**
     *
     */
    public static final String CREATE_TASK = FORE_URL + "/teachertask/createTask";

    /**
     * BODY类型
     */
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

}

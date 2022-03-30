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
     * 获取ppt
     */
    public static final String GET_PPT_BY_TOKEN = FORE_URL + "/getPptListByToken";

    /**
     *
     */
    public static final String CREATE_TASK = FORE_URL + "/teachertask/createTask";
    /**
     *
     */
    public static final String GET_TASK_BY_STUDENTID = FORE_URL + "/teachertask/getTaskByUserId?studentid=";

    public static final String CONFIRM_TASK_STATUS = FORE_URL + "/teachertask/setTaskStatus?userid=";

    /**
     * /teacherSearchVideoByToken
     */
    public static final String Teacher_Search_Video_By_Token = FORE_URL + "/teacherSearchVideoByToken";

    /**
     * 播放视频
     */
    public static final String Display_Video = FORE_URL + "/doc/video/";

    /**
     * 课件路径
     */
    public static final String File_Path = FORE_URL + "/doc/ppt/";

    /**
     * getexam
     */
    public static final String GET_EXAM_LIST = FORE_URL + "/getExamListByToken";

    /**
     * getquestion
     */
    public static final String GET_QUESTION_LIST = FORE_URL + "/getQuestionList?examid=";

    /**
     *
     */
    public static final String Teacher_GET_EXAM_LIST = FORE_URL + "/teacherGetExamList";

    /**
     * BODY类型
     */
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

}

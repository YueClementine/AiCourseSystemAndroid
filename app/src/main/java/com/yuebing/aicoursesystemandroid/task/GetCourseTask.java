package com.yuebing.aicoursesystemandroid.task;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.model.Course;
import com.yuebing.aicoursesystemandroid.model.Courseuserrel;
import com.yuebing.aicoursesystemandroid.model.Response;
import com.yuebing.aicoursesystemandroid.service.CommonNetService;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class GetCourseTask implements Runnable {

    private long userid;

    private Handler handler;

    private String token;

    public GetCourseTask(long userid, String token, Handler handler) {


        this.userid = userid;
        this.handler = handler;
        this.token = token;
    }

    @Override
    public void run() {
        try {


            String result = CommonNetService.getByToken(Constant.GET_COURSE_BY_USERID + userid, token);


            Bundle bundle = new Bundle();
            bundle.putString("result", result);
            Message message = new Message();
            message.setData(bundle);

            handler.sendMessage(message);
        } catch (IOException e) {

            //填充数据
            Bundle bundle = new Bundle();

            bundle.putString("error", e.getMessage());
            Message message = new Message();
            message.setData(bundle);

            //发送控制器消息
            handler.sendMessage(message);
        }
    }
}

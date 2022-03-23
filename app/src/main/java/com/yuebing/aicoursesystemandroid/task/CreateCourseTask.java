package com.yuebing.aicoursesystemandroid.task;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.model.Courseuserrel;
import com.yuebing.aicoursesystemandroid.service.BaseNetService;
import com.yuebing.aicoursesystemandroid.service.CommonNetService;

import java.io.IOException;


public class CreateCourseTask implements Runnable {

    private long userid;
    private int courseid;
    private Handler handler;
    private String username;
    private String token;
    private int role;

    public CreateCourseTask(String token, long userid, int courseid, int role, String username, Handler handler) {
        this.role = role;
        this.username = username;
        this.userid = userid;
        this.courseid = courseid;
        this.handler = handler;
        this.token = token;
    }

    @Override
    public void run() {
        try {
            Courseuserrel courseuserrel = new Courseuserrel();
            courseuserrel.setUsername(username);
            courseuserrel.setUserid(userid);
            courseuserrel.setCourseid(courseid);
            courseuserrel.setRole(role);
            Gson gson = new Gson();
            String json = gson.toJson(courseuserrel);
            String result = CommonNetService.postByToken(Constant.CREATE_COURSE, json, token);

            Bundle bundle = new Bundle();
            bundle.putString("result", result);

            Message message = new Message();
            message.setData(bundle);

            handler.sendMessage(message);
        } catch (IOException e) {

        }
    }
}

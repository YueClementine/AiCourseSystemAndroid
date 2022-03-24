package com.yuebing.aicoursesystemandroid.task;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.model.Response;
import com.yuebing.aicoursesystemandroid.model.Task;
import com.yuebing.aicoursesystemandroid.service.CommonNetService;

import org.json.JSONException;

import java.io.IOException;

public class CommitTaskTask implements Runnable {

    private int courseid;

    private String title;
    private String taskContent;
    private String coursename;
    private Handler handler;

    private String token;

    public CommitTaskTask(int courseid, String coursename, String taskContent, String title, String token, Handler handler) {

        this.courseid = courseid;
        this.coursename = coursename;
        this.taskContent = taskContent;
        this.title = title;
        this.handler = handler;
        this.token = token;
    }

    @Override
    public void run() {
        try {
            Task task = new Task();
            task.setCourseid(courseid);
            task.setTask(taskContent);
            task.setCoursename(coursename);
            task.setTitle(title);
            Gson gson = new Gson();
            String json = gson.toJson(task);

            Response result = CommonNetService.postByToken(Constant.CREATE_TASK, json, token);

            String data = null;
            String msg = null;
            if (result != null && msg != null) {
                msg = result.getMsg();
                data = result.getData();
            }
            Bundle bundle = new Bundle();
            bundle.putString("result", data);
            bundle.putString("msg", data);
            Message message = new Message();
            message.setData(bundle);

            handler.sendMessage(message);
        } catch (IOException | JSONException e) {

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

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

    private int addvideo;
    private int addppt;
    private String videopath;
    private String pptpath;
    private String videoname;
    private String pptname;


    private int courseid;

    private String title;
    private String taskContent;
    private String coursename;
    private Handler handler;

    private String token;

    public CommitTaskTask(int addvideo, int addppt, String videoname, String videopath, String pptname, String pptpath, int courseid, String coursename, String taskContent, String title, String token, Handler handler) {
        this.addvideo = addvideo;
        this.addppt = addppt;
        this.videoname = videoname;
        this.pptname = pptname;
        this.videopath = videopath;
        this.pptpath = pptpath;
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
            task.setAddppt(addppt);
            task.setAddvideo(addvideo);
            task.setVideoname(videoname);
            task.setVideoaddress(videopath);
            task.setPptaddress(pptpath);
            task.setPptname(pptname);

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

            //????????????
            Bundle bundle = new Bundle();

            bundle.putString("error", e.getMessage());
            Message message = new Message();
            message.setData(bundle);

            //?????????????????????
            handler.sendMessage(message);
        }
    }
}

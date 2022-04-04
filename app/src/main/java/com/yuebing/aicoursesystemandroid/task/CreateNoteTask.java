package com.yuebing.aicoursesystemandroid.task;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.model.Note;
import com.yuebing.aicoursesystemandroid.model.Response;
import com.yuebing.aicoursesystemandroid.model.Task;
import com.yuebing.aicoursesystemandroid.service.CommonNetService;

import org.json.JSONException;

import java.io.IOException;

public class CreateNoteTask implements Runnable {

    private int addvideo;
    private int addppt;
    private String videopath;
    private String pptpath;
    private String videoname;
    private String pptname;


    private int taskid;

    private String title;
    private String taskContent;

    private Handler handler;

    private String token;

    public CreateNoteTask(int taskid, int addvideo, int addppt, String videoname, String videopath, String pptname, String pptpath, String taskContent, String title, String token, Handler handler) {

        this.addvideo = addvideo;
        this.addppt = addppt;
        this.videoname = videoname;
        this.pptname = pptname;
        this.videopath = videopath;
        this.pptpath = pptpath;
        this.taskid = taskid;

        this.taskContent = taskContent;
        this.title = title;
        this.handler = handler;
        this.token = token;
    }

    @Override
    public void run() {
        try {
//            Task task = new Task();
//            task.setTask(taskContent);
//
//            task.setTitle(title);
//            task.setAddppt(addppt);
//            task.setAddvideo(addvideo);
//            task.setVideoname(videoname);
//            task.setVideoaddress(videopath);
//            task.setPptaddress(pptpath);
//            task.setPptname(pptname);
            Note note = new Note();
            note.setNote(taskContent);
            note.setTitle(title);
            note.setAddppt(addppt);
            note.setAddvideo(addvideo);
            note.setTaskid(taskid);
            note.setPptname(pptname);

            note.setVideoname(videoname);
            note.setVideoaddress(videopath);
            note.setPptaddress(pptpath);


            Gson gson = new Gson();
            String json = gson.toJson(note);

            Response result = CommonNetService.postByToken(Constant.CREATE_NOTE, json, token);

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

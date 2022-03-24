package com.yuebing.aicoursesystemandroid.task;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.model.Response;
import com.yuebing.aicoursesystemandroid.service.CommonNetService;

import java.io.IOException;

public class StudentTaskTask implements Runnable {
    private String token;
    private long studentid;
    private Handler handler;

    public StudentTaskTask(long studentid, String token, Handler handler) {

        this.studentid = studentid;
        this.handler = handler;
        this.token = token;
    }

    @Override
    public void run() {

        try {

            String result = CommonNetService.getByToken(Constant.GET_TASK_BY_STUDENTID + studentid, token);


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

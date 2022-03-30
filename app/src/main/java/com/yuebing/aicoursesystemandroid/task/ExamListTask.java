package com.yuebing.aicoursesystemandroid.task;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.service.CommonNetService;

import java.io.IOException;

public class ExamListTask implements Runnable {
    private String token;

    private Handler handler;

    public ExamListTask( String token, Handler handler) {


        this.handler = handler;
        this.token = token;
    }

    @Override
    public void run() {

        try {

            String result = CommonNetService.getByToken(Constant.GET_EXAM_LIST, token);


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

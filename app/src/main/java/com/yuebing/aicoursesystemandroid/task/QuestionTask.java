package com.yuebing.aicoursesystemandroid.task;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.model.Exam;
import com.yuebing.aicoursesystemandroid.model.Question;
import com.yuebing.aicoursesystemandroid.service.CommonNetService;

import java.io.IOException;

public class QuestionTask implements Runnable {

    private String token;
    private int examid;
    private String examname;

    private Handler handler;

    public QuestionTask(int examid, String examname, String token, Handler handler) {
        this.examname = examname;
        this.examid = examid;

        this.handler = handler;
        this.token = token;
    }

    @Override
    public void run() {

        try {

            String result = CommonNetService.getByToken(Constant.GET_QUESTION_LIST + examid, token);


            Bundle bundle = new Bundle();
            bundle.putString("result", result);
            bundle.putString("examname", examname);
            bundle.putInt("examid", examid);
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

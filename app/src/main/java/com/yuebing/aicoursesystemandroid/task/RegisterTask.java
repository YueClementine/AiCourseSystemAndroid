package com.yuebing.aicoursesystemandroid.task;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.yuebing.aicoursesystemandroid.model.User;
import com.yuebing.aicoursesystemandroid.service.LoginNetService;

import org.json.JSONException;

import java.io.IOException;

public class RegisterTask implements Runnable {

    private User user;
    private Handler handler;

    public RegisterTask(User user, Handler handler) {

        this.user = user;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            Boolean isSuccess = LoginNetService.register(user);
            Bundle bundle = new Bundle();
            if (isSuccess) {
                bundle.putBoolean("isSuccess", true);

            }else {
                bundle.putBoolean("isSuccess", false);
            }
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

package com.yuebing.aicoursesystemandroid.task;

import com.google.gson.JsonIOException;
import com.yuebing.aicoursesystemandroid.service.LoginNetService;

import org.json.JSONException;

import java.io.IOException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LoginTask implements Runnable {

    private String username;
    private String password;
    private Handler handler;

    public LoginTask(String username, String password, Handler handler) {
        this.username = username;
        this.password = password;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            String token = LoginNetService.login(username, password);
            Bundle bundle = new Bundle();
            if (token.isEmpty()) {
                bundle.putBoolean("isAuthorized", false);
            }
            bundle.putBoolean("isAuthorized", true);
            bundle.putString("token", token);

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

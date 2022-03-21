package com.yuebing.aicoursesystemandroid.task;

import com.yuebing.aicoursesystemandroid.model.User;
import com.yuebing.aicoursesystemandroid.service.LoginNetService;

import org.json.JSONException;

import java.io.IOException;

public class RegisterTask implements Runnable {

    private User user;

    public RegisterTask(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        try {


            Boolean isSuccess = LoginNetService.register(user);

        } catch (IOException | JSONException e) {

        }

    }
}

package com.yuebing.aicoursesystemandroid.ui;

import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.User;
import com.yuebing.aicoursesystemandroid.task.RegisterTask;

import top.androidman.SuperButton;

public class RegisterActivity extends AppCompatActivity {

    private TextView tv_info;
    private SuperButton bt_register;
    private SuperButton bt_teacher;
    private SuperButton bt_student;

    private TextView tv_userId;
    private TextView tv_password;
    private TextView tv_username;

    private int role;
    private int userid;
    private String username;
    private String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tv_info = findViewById(R.id.tv_writeurinfo);
        bt_register = findViewById(R.id.bt_registerConfirm);
        bt_student = findViewById(R.id.bt_student);
        bt_teacher = findViewById(R.id.bt_teacher);
        tv_userId = findViewById(R.id.tv_registerUserid);
        tv_password = findViewById(R.id.tv_registerPassword);
        tv_username = findViewById(R.id.tv_registerUsername);
        TextPaint tp = tv_info.getPaint();
        tp.setFakeBoldText(true);




        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userid = Integer.parseInt(tv_userId.getText().toString());
                password = tv_password.getText().toString();
                username = tv_username.getText().toString();
                User user = new User();
                user.setPassword(password);
                user.setRole(role);
                user.setUsername(username);
                user.setUserid(userid);
                user.setSex(0);

                new Thread(new RegisterTask(user)).start();
            }
        });


        /**
         * 角色选择
         */
        bt_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                role = 1;
                bt_teacher.setColorNormal(getResources().getColor(R.color.colorButtonPressed));

                bt_student.setColorNormal(getResources().getColor(R.color.colorDisabledButton));
            }
        });

        bt_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                role = 0;

                bt_student.setColorNormal(getResources().getColor(R.color.colorButtonPressed));

                bt_teacher.setColorNormal(getResources().getColor(R.color.colorDisabledButton));

            }
        });

    }


}

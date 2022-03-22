package com.yuebing.aicoursesystemandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.MainActivity;
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
    private Long userid;
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
                userid = Long.parseLong(tv_userId.getText().toString());
                password = tv_password.getText().toString();
                username = tv_username.getText().toString();
                User user = new User();
                user.setPassword(password);
                user.setRole(role);
                user.setUsername(username);
                user.setUserid(userid);
                user.setSex(0);

                new Thread(new RegisterTask(user, handler)).start();
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

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();

            //抽取异常信息
            if (bundle.getString("error") != null) {
                Toast.makeText(getApplicationContext(), bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }

            //获取注册状态
            boolean isSuccess = bundle.getBoolean("isSuccess");

            if (isSuccess) {
                Toast.makeText(getApplication(), "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }

            return true;
        }

    });


}

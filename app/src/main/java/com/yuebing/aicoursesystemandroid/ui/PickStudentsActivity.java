package com.yuebing.aicoursesystemandroid.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Courseuserrel;
import com.yuebing.aicoursesystemandroid.task.CreateCourseTask;

import top.androidman.SuperButton;

public class PickStudentsActivity extends AppCompatActivity {

    private TextView tv_studentidInput;
    private TextView tv_addSuccess;
    private TextView tv_pickedStudentName;
    private TextView tv_pickedStudentId;
    private SuperButton bt_addstudent;
    private String token;
    private long studentid;
    private int courseid;
    private int role;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickstudents);
        token = getIntent().getStringExtra("token");
        courseid = getIntent().getIntExtra("courseid", 1);

        initView();

        bt_addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentid = Long.parseLong(tv_studentidInput.getText().toString());
                role = 0;
                new Thread(new CreateCourseTask(token, studentid, courseid, role, "null", "null", handler)).start();

            }
        });

    }

    private void initView() {
        tv_studentidInput = findViewById(R.id.tv_studentidinput);
        bt_addstudent = findViewById(R.id.bt_searchstudentsbyuserid);
        tv_pickedStudentId = findViewById(R.id.tv_pickedStudentId);
        tv_addSuccess = findViewById(R.id.tv_addSuccess);
        tv_pickedStudentName = findViewById(R.id.tv_pickedstudentName);
    }
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                Toast.makeText(getApplicationContext(), bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }
            String data = bundle.getString("data");
            String msg = bundle.getString("msg");

            Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
            Gson gson = new Gson();
            Courseuserrel cresult = gson.fromJson(data, Courseuserrel.class);
            tv_addSuccess.setText("添加成功");
            tv_pickedStudentId.setText("学号：" + cresult.getUserid().toString());
            tv_pickedStudentName.setText("姓名：" + cresult.getUsername());

            return true;
        }
    });

}

package com.yuebing.aicoursesystemandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.task.CommitTaskTask;

import top.androidman.SuperButton;

public class EditTaskActivity extends AppCompatActivity {

    private TextView tv_tasktitle;
    private TextView tv_taskcontent;
    private SuperButton bt_taskCommit;
    private String title;
    private String task;
    private String token;
    private String coursename;
    private int courseid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edittask);
        initView();
        token = getIntent().getStringExtra("token");
        courseid = getIntent().getIntExtra("courseid", 1);
        coursename = getIntent().getStringExtra("coursename");


        bt_taskCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = tv_taskcontent.getText().toString();
                title = tv_tasktitle.getText().toString();

                new Thread(new CommitTaskTask(courseid, coursename, task, title, token, handler)).start();

            }
        });

    }

    private void initView() {

        tv_tasktitle = findViewById(R.id.tv_taskTitle);
        bt_taskCommit = findViewById(R.id.bt_commitTask);
        tv_taskcontent = findViewById(R.id.tv_task);

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

            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), CreateTaskActivity.class);
            startActivity(intent);

            return true;
        }
    });

}

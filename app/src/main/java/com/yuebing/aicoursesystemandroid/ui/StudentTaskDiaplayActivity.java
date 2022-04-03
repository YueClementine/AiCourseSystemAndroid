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
import com.yuebing.aicoursesystemandroid.task.ConfirmFinishTaskTask;

import top.androidman.SuperButton;

public class StudentTaskDiaplayActivity extends AppCompatActivity {

    private SuperButton bt_commitfinishtask;
    private TextView tv_title;

    private TextView tv_taskdetail;
    private TextView tv_finishtasktitle;

    private String token;

    private String title;
    private String detail;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetail);

        token = getIntent().getStringExtra("token");
        detail = getIntent().getStringExtra("taskdetail");
        title = getIntent().getStringExtra("tasktitle");

        bt_commitfinishtask = findViewById(R.id.bt_commitfinishTask);
        tv_finishtasktitle = findViewById(R.id.finishtaskintime);
        tv_title = findViewById(R.id.tv_taskdisplayTitle);
        tv_taskdetail = findViewById(R.id.tv_displaytask);

        tv_taskdetail.setText(detail);
        tv_title.setText(title);


        bt_commitfinishtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new ConfirmFinishTaskTask(getIntent().getIntExtra("taskid", 1), getIntent().getLongExtra("userid", 1L), getIntent().getStringExtra("token"), taskHandler)).start();

            }
        });







    }
    private Handler taskHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                Toast.makeText(getApplicationContext(), bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }
            String result = bundle.getString("result");

            Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();





            return true;
        }
    });
}

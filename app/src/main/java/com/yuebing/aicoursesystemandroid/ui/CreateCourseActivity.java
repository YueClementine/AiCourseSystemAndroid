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

import com.google.gson.Gson;
import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Courseuserrel;
import com.yuebing.aicoursesystemandroid.task.CreateCourseTask;

import top.androidman.SuperButton;

public class CreateCourseActivity extends AppCompatActivity {

    //userid
    private long userid;

    //token
    private String token;

    private String username;

    private SuperButton bt_confirm;

    private TextView tv_coursename;
    private TextView tv_courseid;

    //role
    private int role;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_createcourse);

        userid = getIntent().getLongExtra("userid", 1L);
        token = getIntent().getStringExtra("token");
        username = getIntent().getStringExtra("username");
        role = getIntent().getIntExtra("role", 1);


        initView();

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new CreateCourseTask(token, userid, Integer.parseInt(tv_courseid.getText().toString()), role, username, tv_coursename.getText().toString(), handler)).start();

//                Intent intent = new Intent(getApplicationContext(), PickStudentsActivity.class);
//                intent.putExtra("userid", userid);
//                intent.putExtra("token", token);
//                intent.putExtra("courseid", tv_courseid.getText().toString());
//                startActivity(intent);

            }
        });


    }


    private void initView() {
        bt_confirm = findViewById(R.id.bt_confirmcourseid);
        tv_courseid = findViewById(R.id.tv_courseid);
        tv_coursename = findViewById(R.id.tv_coursename);
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

            Intent intent = new Intent(getApplicationContext(), PickStudentsActivity.class);
            intent.putExtra("userid", userid);
            intent.putExtra("token", token);
            intent.putExtra("courseid", Integer.parseInt(tv_courseid.getText().toString()));
            startActivity(intent);

            return true;
        }
    });
}

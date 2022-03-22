package com.yuebing.aicoursesystemandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;

import top.androidman.SuperButton;

public class CreateCourseActivity extends AppCompatActivity {

    //userid
    private long userid;

    //token
    private String token;

    private SuperButton bt_confirm;
    private TextView tv_courseid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_createcourse);

        userid = getIntent().getLongExtra("userid", 1L);
        token = getIntent().getStringExtra("token");

        initView();

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PickStudentsActivity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("token", token);
                intent.putExtra("courseid", tv_courseid.getText().toString());
                startActivity(intent);

            }
        });
    }


    private void initView() {
        bt_confirm = findViewById(R.id.bt_confirmcourseid);
        tv_courseid = findViewById(R.id.tv_courseid);
    }
}

package com.yuebing.aicoursesystemandroid.ui;

import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.task.RegisterTask;

public class RegisterActivity extends AppCompatActivity {

    private TextView tv_info;
    private Button bt_register;
    private Button bt_teacher;
    private Button bt_student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tv_info = findViewById(R.id.tv_writeurinfo);
        bt_register = findViewById(R.id.bt_registerConfirm);
        bt_student = findViewById(R.id.bt_student);
        bt_teacher = findViewById(R.id.bt_teacher);



        TextPaint tp = tv_info.getPaint();
        tp.setFakeBoldText(true);




        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bt_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bt_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}

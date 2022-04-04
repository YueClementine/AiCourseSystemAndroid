package com.yuebing.aicoursesystemandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.task.ConfirmFinishTaskTask;
import com.yuebing.aicoursesystemandroid.ui.student.StudentCreateNoteActivity;

import top.androidman.SuperButton;

public class StudentTaskDiaplayActivity extends AppCompatActivity {

    private SuperButton bt_commitfinishtask;
    private TextView tv_title;
    private SuperButton bt_videoorppt;

    private ImageButton im_ppticon;

    private TextView tv_taskdetail;
    private TextView tv_finishtasktitle;

    private TextView videoorpptname;

    private String token;

    private String title;
    private String detail;

    private int addppt;
    private int addvideo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetail);
        im_ppticon = findViewById(R.id.bt_ppticon);
        bt_videoorppt = findViewById(R.id.bt_videoorppt);

        videoorpptname = findViewById(R.id.tv_pptorvideoname);

        token = getIntent().getStringExtra("token");
        detail = getIntent().getStringExtra("taskdetail");
        title = getIntent().getStringExtra("tasktitle");

        bt_commitfinishtask = findViewById(R.id.bt_commitfinishTask);
        tv_finishtasktitle = findViewById(R.id.finishtaskintime);
        tv_title = findViewById(R.id.tv_taskdisplayTitle);
        tv_taskdetail = findViewById(R.id.tv_displaytask);

        tv_taskdetail.setText(detail);
        tv_title.setText(title);

        if (getIntent().getIntExtra("addvideo", 0) == 1) {
            videoorpptname.setText(getIntent().getStringExtra("videoname"));
            im_ppticon.setBackgroundResource(R.drawable.videotest2);
            //视频
            bt_videoorppt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), VideoViewTestActivity.class);

                    intent.putExtra("title", getIntent().getStringExtra("videoname"));
                    intent.putExtra("path", getIntent().getStringExtra("videoaddress"));

                    startActivity(intent);
                }
            });

        } else if (getIntent().getIntExtra("addppt", 0) == 1) {
            videoorpptname.setText(getIntent().getStringExtra("pptname"));
            im_ppticon.setBackgroundResource(R.drawable.item_ppt);
            bt_videoorppt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), PdfActvity.class);
                    intent.putExtra("path", Constant.File_Path + getIntent().getStringExtra("pptaddress"));
                    intent.putExtra("name", getIntent().getStringExtra("pptname"));
                    startActivity(intent);
                }
            });
        } else {
            bt_videoorppt.setVisibility(View.GONE);
            im_ppticon.setVisibility(View.GONE);
        }
        bt_commitfinishtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentTaskDiaplayActivity.this, StudentCreateNoteActivity.class);
                intent.putExtra("taskid", getIntent().getIntExtra("taskid", 1));
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
    }

}

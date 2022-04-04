package com.yuebing.aicoursesystemandroid.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.task.CommitTaskTask;
import com.yuebing.aicoursesystemandroid.task.CreateNoteTask;
import com.yuebing.aicoursesystemandroid.task.PptListTask;
import com.yuebing.aicoursesystemandroid.task.VideoListTask;
import com.yuebing.aicoursesystemandroid.ui.teacher.AddPdfActivity;
import com.yuebing.aicoursesystemandroid.ui.teacher.AddVideoActivity;

import top.androidman.SuperButton;

public class StudentCreateNoteActivity extends AppCompatActivity {

    private TextView tv_tasktitle;
    private TextView tv_taskcontent;
    private SuperButton bt_taskCommit;
    private String title;
    private String task;
    private String token;

    private int taskid;
    private int addvideo;
    private int addppt;
    private String videotitle;
    private String videopath;
    private String ppttitle;
    private String pptpath;

    private int role = 0;

    private Button bt_video;
    private Button bt_ppt;


    private SuperButton bt_addVideo;
    private SuperButton bt_addPdf;

    private TextView tv_videoTitle;
    private TextView tv_pptTitle;
    private TextView tv_bigtitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edittask);
        initView();
        token = getIntent().getStringExtra("token");
        taskid = getIntent().getIntExtra("taskid", 1);
        tv_bigtitle = findViewById(R.id.tv_edittaskNoteTitle);
        tv_bigtitle.setText("请输入笔记内容");




        //点击事件

        bt_addVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new VideoListTask(token, addVideoHandler)).start();
            }
        });

        bt_addPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new PptListTask(token, addPdfHandler)).start();
            }
        });

        bt_taskCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = tv_taskcontent.getText().toString();
                title = tv_tasktitle.getText().toString();


                new Thread(new CreateNoteTask(taskid, addvideo, addppt, videotitle, videopath, ppttitle, pptpath, task, title, token, handler)).start();

            }
        });


    }

    private void initView() {

        tv_tasktitle = findViewById(R.id.tv_taskTitle);
        bt_taskCommit = findViewById(R.id.bt_commitTask);
        tv_taskcontent = findViewById(R.id.tv_task);
        bt_addPdf = findViewById(R.id.bt_addpdf_edittask);
        bt_addVideo = findViewById(R.id.bt_addvideo_edittask);

        addvideo = getIntent().getIntExtra("addvideo", 0);
        addppt = getIntent().getIntExtra("addppt", 0);
        if (addvideo == 1) {
            bt_video = findViewById(R.id.bt_add);
            bt_video.setBackgroundResource(R.drawable.videotest2);
            tv_videoTitle = findViewById(R.id.tv_add_video);
            videotitle = getIntent().getStringExtra("videotitle");
            tv_videoTitle.setText(videotitle);
            videopath = getIntent().getStringExtra("videopath");
        } else if (addppt == 1) {
            bt_ppt = findViewById(R.id.bt_add2);
            bt_ppt.setBackgroundResource(R.drawable.item_ppt);
            tv_pptTitle = findViewById(R.id.tv_add_pdf);
            ppttitle = getIntent().getStringExtra("ppttitle");
            pptpath = getIntent().getStringExtra("pptpath");
            tv_pptTitle.setText(ppttitle);

        }


    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                Toast.makeText(getApplicationContext(), bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }
//            String data = bundle.getString("data");


            Toast.makeText(getApplicationContext(), "创建成功", Toast.LENGTH_SHORT).show();

            return true;
        }
    });

    private Handler addVideoHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                Toast.makeText(StudentCreateNoteActivity.this, bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }
            String result = bundle.getString("result");



            Intent intent = new Intent(getApplicationContext(), AddVideoActivity.class);
            intent.putExtra("role", role);
            intent.putExtra("videolist", result);
            intent.putExtra("token", token);
            intent.putExtra("taskid", taskid);

            startActivity(intent);

            return true;
        }
    });
    private Handler addPdfHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                Toast.makeText(StudentCreateNoteActivity.this, bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }

            String result = bundle.getString("result");
            Intent intent = new Intent(getApplicationContext(), AddPdfActivity.class);
            intent.putExtra("role", role);
            intent.putExtra("pdflist", result);
            intent.putExtra("token", token);
            intent.putExtra("taskid", taskid);

            startActivity(intent);

            return true;
        }
    });

}

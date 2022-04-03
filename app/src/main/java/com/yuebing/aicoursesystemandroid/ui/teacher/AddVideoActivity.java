package com.yuebing.aicoursesystemandroid.ui.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.VideoDo;
import com.yuebing.aicoursesystemandroid.ui.EditTaskActivity;
import com.yuebing.aicoursesystemandroid.ui.VideoCenterActivity;
import com.yuebing.aicoursesystemandroid.ui.VideoCenterAdapter;
import com.yuebing.aicoursesystemandroid.ui.VideoViewTestActivity;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.util.List;

public class AddVideoActivity extends AppCompatActivity {

    private List<VideoDo> videoDoList;
    private String videolistjson;
    private String token;
    private ListView lv_add_video;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_video_upload);
        token = getIntent().getStringExtra("token");
        videolistjson = getIntent().getStringExtra("videolist");
        videoDoList = JsonListUtil.getObjectList(videolistjson, VideoDo.class);

        lv_add_video = findViewById(R.id.lv_add_video);
        VideoCenterAdapter videoCenterAdapter = new VideoCenterAdapter(AddVideoActivity.this, R.layout.item_video, videoDoList);

        lv_add_video.setAdapter(videoCenterAdapter);

        lv_add_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                VideoDo videoDo = videoDoList.get(i);
                Intent intent = new Intent(getApplicationContext(), EditTaskActivity.class);
                intent.putExtra("addvideo", 1);
                intent.putExtra("videotitle", videoDo.getVideoname());
                intent.putExtra("videopath", videoDo.getVideoaddress());
                intent.putExtra("courseid", getIntent().getIntExtra("courseid", 1));
                intent.putExtra("coursename", getIntent().getStringExtra("coursename"));
                intent.putExtra("token", token);

                startActivity(intent);
            }
        });



    }
}

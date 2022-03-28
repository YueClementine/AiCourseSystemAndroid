package com.yuebing.aicoursesystemandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.TaskuserrelVO;
import com.yuebing.aicoursesystemandroid.model.VideoDo;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.util.List;

public class VideoCenterActivity extends AppCompatActivity {

    private String token;
    private String videolistjson;
    private List<VideoDo> videoDoList;
    private ListView lv_videolist;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videocenter);

        token = getIntent().getStringExtra("token");

        videolistjson = getIntent().getStringExtra("videolist");
        videoDoList = JsonListUtil.getObjectList(videolistjson, VideoDo.class);
        lv_videolist = findViewById(R.id.lv_videolist);

        VideoCenterAdapter videoCenterAdapter = new VideoCenterAdapter(VideoCenterActivity.this, R.layout.item_video, videoDoList);

        lv_videolist.setAdapter(videoCenterAdapter);

        lv_videolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                VideoDo videoDo = videoDoList.get(i);
                Intent intent = new Intent(getApplicationContext(), VideoViewTestActivity.class);

                intent.putExtra("title", videoDo.getVideoname());
                intent.putExtra("path", videoDo.getVideoaddress());

                startActivity(intent);
            }
        });


    }
}

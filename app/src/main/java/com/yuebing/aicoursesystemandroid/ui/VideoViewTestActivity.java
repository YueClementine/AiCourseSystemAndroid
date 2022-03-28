package com.yuebing.aicoursesystemandroid.ui;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

import com.nurmemet.nur.nurvideoplayer.NurVideoView;
import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.common.Constant;

public class VideoViewTestActivity extends AppCompatActivity {

    private NurVideoView nurVideoPlayer;
    private String title;
    private String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_test);
//        Window _window = getWindow();
//        WindowManager.LayoutParams params = _window.getAttributes();
//        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE;
//        _window.setAttributes(params);

        title = getIntent().getStringExtra("title");
        path = getIntent().getStringExtra("path");


        String url = Constant.Display_Video + path;

        nurVideoPlayer = findViewById(R.id.videoView);
        nurVideoPlayer.setUp(this, url, title);
    }


    @Override
    public void onBackPressed() {
        if (nurVideoPlayer.getIsFullScreen()) {
            nurVideoPlayer.setChangeScreen(false);

        } else
            super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        nurVideoPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        nurVideoPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nurVideoPlayer.stopPlay();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean b = nurVideoPlayer.onKeyDown(keyCode);
        return b || super.onKeyDown(keyCode, event);
    }
}
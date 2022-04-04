package com.yuebing.aicoursesystemandroid.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Comment;

import java.util.ArrayList;
import java.util.List;

import top.androidman.SuperButton;

public class CommentActivity extends AppCompatActivity {

    private ListView lv_comments;
    private SuperButton bt_comment;
    private TextView tv_input;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        lv_comments = findViewById(R.id.lv_comment);
        List<Comment> comments = new ArrayList<>();
        tv_input = findViewById(R.id.tv_comments);
        bt_comment = findViewById(R.id.bt_commitcomment);

        CommentAdapter adapter = new CommentAdapter(CommentActivity.this, R.layout.item_comment, comments);

        lv_comments.setAdapter(adapter);




        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comment comment = new Comment();
                comment.setComment(tv_input.getText().toString());
                comment.setUsername("李明");
                comments.add(comment);
                adapter.notifyDataSetChanged();
                tv_input.setText("");
            }
        });
    }







}

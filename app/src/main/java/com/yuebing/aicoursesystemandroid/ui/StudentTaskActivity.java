package com.yuebing.aicoursesystemandroid.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextPaint;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.TaskuserrelVO;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.util.List;

public class StudentTaskActivity extends AppCompatActivity {

    private String tasklistjson;
    private List<TaskuserrelVO> taskuserrelVOList;


    private ListView lv_studenttask;
    private TextView tv_finishnum;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studenttask);

        tasklistjson = getIntent().getStringExtra("tasklist");
        taskuserrelVOList = JsonListUtil.getObjectList(tasklistjson, TaskuserrelVO.class);



        tv_finishnum = findViewById(R.id.tv_undoneTaskNum);

        lv_studenttask = findViewById(R.id.lv_studenttask);


        StudentTaskDisplayAdapter studentTaskDisplayAdapter = new StudentTaskDisplayAdapter(StudentTaskActivity.this, R.layout.item_task, taskuserrelVOList);

        lv_studenttask.setAdapter(studentTaskDisplayAdapter);



    }
}

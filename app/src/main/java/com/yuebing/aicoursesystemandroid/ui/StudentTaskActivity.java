package com.yuebing.aicoursesystemandroid.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.AdapterView;
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

    private String token;


    private ListView lv_studenttask;
    private TextView tv_finishnum;
    private int unfinishnum = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studenttask);

        token = getIntent().getStringExtra("token");

        tasklistjson = getIntent().getStringExtra("tasklist");
        taskuserrelVOList = JsonListUtil.getObjectList(tasklistjson, TaskuserrelVO.class);


        for (int i = 0; i < taskuserrelVOList.size(); i++) {
            if (taskuserrelVOList.get(i).getStatus() == 0) {
                unfinishnum++;

            }
        }

        tv_finishnum = findViewById(R.id.tv_undoneTaskNum);
        if (unfinishnum != 0) {
            tv_finishnum.setText("您有" + unfinishnum + "个任务未完成");
            tv_finishnum.setTextColor(Color.RED);

        }
        lv_studenttask = findViewById(R.id.lv_studenttask);


        StudentTaskDisplayAdapter studentTaskDisplayAdapter = new StudentTaskDisplayAdapter(StudentTaskActivity.this, R.layout.item_task, taskuserrelVOList);

        lv_studenttask.setAdapter(studentTaskDisplayAdapter);

        lv_studenttask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TaskuserrelVO taskuserrelVO = taskuserrelVOList.get(position);


                Intent intent = new Intent(getApplicationContext(), StudentTaskDiaplayActivity.class);
                intent.putExtra("taskdetail", taskuserrelVO.getTask());
                intent.putExtra("tasktitle", taskuserrelVO.getTitle());
                intent.putExtra("userid", taskuserrelVO.getUserid());

                intent.putExtra("taskid", taskuserrelVO.getTaskid());

                intent.putExtra("token", token);
                startActivity(intent);

            }
        });



    }
}

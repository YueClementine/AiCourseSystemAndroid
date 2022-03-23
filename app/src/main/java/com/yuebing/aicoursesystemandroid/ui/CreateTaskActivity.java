package com.yuebing.aicoursesystemandroid.ui;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Course;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.util.ArrayList;
import java.util.List;


public class CreateTaskActivity extends AppCompatActivity {

    private String courseListjson;


    private ListView lv_course;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskteacher);

        lv_course = findViewById(R.id.lv_taskcourse);


        courseListjson = getIntent().getStringExtra("courselist");
        List<Course> courseList = new ArrayList<>();
        courseList = JsonListUtil.getObjectList(courseListjson, Course.class);


        CourseDisplayAdapter adapter = new CourseDisplayAdapter(CreateTaskActivity.this, R.layout.item_courses, courseList);

        lv_course = findViewById(R.id.lv_taskcourse);
        lv_course.setAdapter(adapter);

    }
}

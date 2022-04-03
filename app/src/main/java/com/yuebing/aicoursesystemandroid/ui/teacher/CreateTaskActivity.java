package com.yuebing.aicoursesystemandroid.ui.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Course;
import com.yuebing.aicoursesystemandroid.ui.CourseDisplayAdapter;
import com.yuebing.aicoursesystemandroid.ui.EditTaskActivity;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.util.ArrayList;
import java.util.List;


public class CreateTaskActivity extends AppCompatActivity {

    private String courseListjson;
    private List<Course> courseList = new ArrayList<>();
    private String token;
    private ListView lv_course;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskteacher);

        lv_course = findViewById(R.id.lv_taskcourse);

        token = getIntent().getStringExtra("token");
        courseListjson = getIntent().getStringExtra("courselist");

        courseList = JsonListUtil.getObjectList(courseListjson, Course.class);


        CourseDisplayAdapter adapter = new CourseDisplayAdapter(CreateTaskActivity.this, R.layout.item_courses, courseList);

        lv_course = findViewById(R.id.lv_taskcourse);
        lv_course.setAdapter(adapter);

        lv_course.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Course course = courseList.get(position);
                int courseid = course.getCourseid();
                String coursename = course.getCoursename();
                Intent intent = new Intent(getApplicationContext(), EditTaskActivity.class);

                intent.putExtra("courseid", courseid);
                intent.putExtra("coursename", coursename);
                intent.putExtra("token", token);
                startActivity(intent);


            }
        });

    }
}

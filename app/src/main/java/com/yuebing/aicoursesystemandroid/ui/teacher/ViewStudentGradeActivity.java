package com.yuebing.aicoursesystemandroid.ui.teacher;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Exam;
import com.yuebing.aicoursesystemandroid.model.ExamUserRel;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.util.List;

public class ViewStudentGradeActivity extends AppCompatActivity {
    private List<ExamUserRel> examList;
    private String examListJson;
    private ListView lv_grade;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstudent_grade);


        lv_grade = findViewById(R.id.lv_viewstudentGrade);
        examListJson = getIntent().getStringExtra("gradelist");

        examList = JsonListUtil.getObjectList(examListJson, ExamUserRel.class);

        ViewStudentGradeAdapter viewStudentGradeAdapter = new ViewStudentGradeAdapter(ViewStudentGradeActivity.this, R.layout.item_teacherviewstuentgrade, examList);

        lv_grade.setAdapter(viewStudentGradeAdapter);



    }
}

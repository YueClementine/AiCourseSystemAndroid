package com.yuebing.aicoursesystemandroid.ui.teacher;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Exam;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.util.List;

public class TeacherExamPublishActivity extends AppCompatActivity {

    private ListView lv_teacherpublishExam;
    private TextView tv_publishNum;
    private String examListJson;
    private List<Exam> examList;
    private int publishingExam;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherpublishexam);
        tv_publishNum = findViewById(R.id.tv_teacherExamNum);

        examListJson = getIntent().getStringExtra("examlist");

        examList = JsonListUtil.getObjectList(examListJson, Exam.class);
        publishingExam = 0;

        for (int i = 0; i < examList.size(); i++) {
            if (examList.get(i).getIspublish() == 0) {
                publishingExam++;
            }
        }
        if (publishingExam == 0) {
            tv_publishNum.setText("您没有考试待发布");

        } else {
            tv_publishNum.setText("您有" + publishingExam + "场考试待发布");
        }

        lv_teacherpublishExam = findViewById(R.id.lv_teacherpublishexam);

        TeacherPublishExamAdapter teacherPublishExamAdapter = new TeacherPublishExamAdapter(TeacherExamPublishActivity.this, R.layout.item_teacherpublishexam, examList);

        lv_teacherpublishExam.setAdapter(teacherPublishExamAdapter);

    }

    public int getPublishingExam() {
        return publishingExam;
    }

    public TextView getTv_publishNum() {
        return tv_publishNum;
    }
}

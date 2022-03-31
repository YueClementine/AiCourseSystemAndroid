package com.yuebing.aicoursesystemandroid.ui.teacher;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Exam;
import com.yuebing.aicoursesystemandroid.task.GetGradeListTask;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.util.ArrayList;
import java.util.List;

public class ViewStudentExamActivity extends AppCompatActivity {

    private ListView lv_teacherpublishExam;
    private TextView tv_currentNum;
    private String examListJson;
    private List<Exam> examList;
    private int publishingExam;
    private List<Exam> publishedExamList;
    private String token;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstudent_exam);

        token = getIntent().getStringExtra("token");

        examListJson = getIntent().getStringExtra("examlist");

        examList = JsonListUtil.getObjectList(examListJson, Exam.class);
        publishedExamList = new ArrayList<>();


        for (int i = 0; i < examList.size(); i++) {
            if (examList.get(i).getIspublish() == 1) {
                publishedExamList.add(examList.get(i));
            }
        }

        tv_currentNum = findViewById(R.id.tv_currentExamNum);

        tv_currentNum.setText("您目前共发布了" + publishedExamList.size() + "场考试");
        lv_teacherpublishExam = findViewById(R.id.lv_viewExam);

        ViewStudentExamAdapter viewStudentExamAdapter = new ViewStudentExamAdapter(ViewStudentExamActivity.this, R.layout.item_teacherviewstudentexam, publishedExamList);

        lv_teacherpublishExam.setAdapter(viewStudentExamAdapter);

        lv_teacherpublishExam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                new Thread(new GetGradeListTask(publishedExamList.get(i).getExamid(), token, handler)).start();
            }
        });

    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                Toast.makeText(getApplicationContext(), bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }
            String result = bundle.getString("result");



            Intent intent = new Intent(getApplicationContext(), ViewStudentGradeActivity.class);
            intent.putExtra("gradelist", result);
            intent.putExtra("token", getIntent().getStringExtra("token"));

            startActivity(intent);

            return true;
        }
    });
}

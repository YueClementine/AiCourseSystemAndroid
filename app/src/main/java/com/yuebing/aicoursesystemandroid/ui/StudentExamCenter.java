package com.yuebing.aicoursesystemandroid.ui;

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
import com.yuebing.aicoursesystemandroid.model.ExamUserRel;
import com.yuebing.aicoursesystemandroid.model.TaskuserrelVO;
import com.yuebing.aicoursesystemandroid.task.QuestionTask;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.util.List;

public class StudentExamCenter extends AppCompatActivity {
    private ListView lv_examlist;
    private String examlistjson;
    private List<ExamUserRel> examList;
    private TextView tv_examNum;
    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examstudent);
        token = getIntent().getStringExtra("token");

        tv_examNum = findViewById(R.id.tv_studentExamNum);


        examlistjson = getIntent().getStringExtra("examlist");
        examList = JsonListUtil.getObjectList(examlistjson, ExamUserRel.class);
        if (examList.isEmpty()) {
            tv_examNum.setText("您目前没有考试");
        } else {
            tv_examNum.setText("您有" + examList.size() + "套试卷待完成");
        }

        lv_examlist = findViewById(R.id.lv_studentexam);
        StudentExamListAdapter studentExamListAdapter = new StudentExamListAdapter(StudentExamCenter.this, R.layout.item_examstudent, examList);
        lv_examlist.setAdapter(studentExamListAdapter);



        lv_examlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ExamUserRel examUserRel = examList.get(position);
                new Thread(new QuestionTask(examUserRel.getExamid(), examUserRel.getExamname(), token, questionHandler)).start();

            }
        });


    }
    private Handler questionHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                return false;
            }
            String result = bundle.getString("result");
            String examname = bundle.getString("examname");
            int examid = bundle.getInt("examid");


            Intent intent = new Intent(StudentExamCenter.this, QuestionListActivity.class);
            intent.putExtra("examname", examname);
            intent.putExtra("questionlist", result);
            intent.putExtra("examid", examid);
            intent.putExtra("token", getIntent().getStringExtra("token"));

            startActivity(intent);

            return true;
        }
    });
}

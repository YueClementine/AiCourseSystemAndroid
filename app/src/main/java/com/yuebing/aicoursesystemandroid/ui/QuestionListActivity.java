package com.yuebing.aicoursesystemandroid.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.model.Exam;
import com.yuebing.aicoursesystemandroid.model.Question;
import com.yuebing.aicoursesystemandroid.service.CommonNetService;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionListActivity extends AppCompatActivity {

    private ListView lv_questionList;
    private List<Question> questionList;
    private String questionListJson;
    private String token;
    private TextView tv_examTitle;
    private TextView tv_confirm;
    String examname;
    private int examid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionlist);
        token = getIntent().getStringExtra("token");
        examid = getIntent().getIntExtra("examid", 1);
        examname = getIntent().getStringExtra("examname");


        tv_examTitle = findViewById(R.id.tv_examTitle);
        tv_confirm = findViewById(R.id.tv_confirmCommit);

        tv_examTitle.setText(examname);



        questionListJson = getIntent().getStringExtra("questionlist");
        questionList = JsonListUtil.getObjectList(questionListJson, Question.class);

        lv_questionList = findViewById(R.id.lv_questionlist);

        QuestionListAdapter questionListAdapter = new QuestionListAdapter(QuestionListActivity.this, R.layout.item_question, questionList);

        lv_questionList.setAdapter(questionListAdapter);

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_confirm.setText("已交卷");
                tv_confirm.setClickable(false);
                int correctnum = 0;
                for (int i = 0; i < questionList.size(); i++) {
                    if (questionList.get(i).getQuestiontag() == 1) {
                        correctnum++;
                    }
                }
                int grade = 100 * correctnum / questionList.size();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            CommonNetService.getByToken(Constant.COMMIT_EXAM + grade + "&examid=" + examid, token);
                        }catch (IOException e){
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).start();
            }
        });


    }
}

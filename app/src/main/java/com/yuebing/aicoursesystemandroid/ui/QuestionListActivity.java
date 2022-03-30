package com.yuebing.aicoursesystemandroid.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionListActivity extends AppCompatActivity {

    private ListView lv_questionList;
    private List<Question> questions;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionlist);

        Question question = new Question();
        question.setQuestioncontent("test111");
        question.setOpa("AAAA");
        question.setOpb("BBB");
        question.setOpc("ccc");
        question.setOpd("ddd");
        Question question1 = new Question();
        question1.setQuestioncontent("test222");
        question1.setOpa("AAAA");
        question1.setOpb("BBB");
        question1.setOpc("ccc");
        question1.setOpd("ddd");
        questions = new ArrayList<>();
        questions.add(question);
        questions.add(question1);

        lv_questionList = findViewById(R.id.lv_questionlist);

        QuestionListAdapter questionListAdapter = new QuestionListAdapter(QuestionListActivity.this, R.layout.item_question, questions);

        lv_questionList.setAdapter(questionListAdapter);


    }
}

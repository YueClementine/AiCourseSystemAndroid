package com.yuebing.aicoursesystemandroid.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Discuss;
import com.yuebing.aicoursesystemandroid.ui.teacher.ViewDiscussAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentDiscussDisplayActivity extends AppCompatActivity {

    private List<Discuss> discusses;
    private ListView lv_discuss;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_discuss);

        lv_discuss = findViewById(R.id.lv_discussdisplay);

        discusses = mockdata();

        ViewDiscussAdapter viewDiscussAdapter = new ViewDiscussAdapter(StudentDiscussDisplayActivity.this, R.layout.item_discuss_teacher, discusses);


        lv_discuss.setAdapter(viewDiscussAdapter);

        lv_discuss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(StudentDiscussDisplayActivity.this, StudentMessageActivity.class);
                startActivity(intent);

            }
        });


    }

    private List<Discuss> mockdata() {
        Discuss discuss = new Discuss();
        discuss.setTopic("人工智能在生活中的应用");
        List<Discuss> discusses = new ArrayList<>();
        discusses.add(discuss);
        return discusses;
    }
}

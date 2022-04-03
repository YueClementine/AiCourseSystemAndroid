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
import com.yuebing.aicoursesystemandroid.model.Course;
import com.yuebing.aicoursesystemandroid.model.Discuss;
import com.yuebing.aicoursesystemandroid.task.CreateDiscussTask;
import com.yuebing.aicoursesystemandroid.task.TeacherGetGroupTask;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.util.List;

public class NewDiscussActivity extends AppCompatActivity {

    private List<Course> courses;
    private String discussListJson;
    private ListView lv_discuss;
    private String token;
    private TextView tv_create;
    private TextView tv_groupNum;
    private TextView tv_topic;
    private String topic;
    private int groupNum;
    private int courseid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_discuss);

        tv_topic = findViewById(R.id.tv_topic);
        tv_groupNum = findViewById(R.id.tv_groupnum);


        tv_create = findViewById(R.id.tv_createDiscuss);

        token = getIntent().getStringExtra("token");

        lv_discuss = findViewById(R.id.lv_newdiscusscourse);


        NewDiscussAdapter newDiscussAdapter = new NewDiscussAdapter(NewDiscussActivity.this, R.layout.item_discuss_teacher, courses);
        lv_discuss.setAdapter(newDiscussAdapter);
        lv_discuss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.findViewById(R.id.im_check).setVisibility(View.VISIBLE);

                courseid = courses.get(i).getCourseid();
            }
        });


        tv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new Thread(new CreateDiscussTask()).start();

                Intent intent = new Intent();

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
            int discussid = bundle.getInt("discussid", 1);


            Intent intent = new Intent(getApplicationContext(), CreateDiscussActivity.class);
            intent.putExtra("discusslist", discussid);
            intent.putExtra("grouplist", result);
            intent.putExtra("token", token);

            startActivity(intent);

            return true;
        }
    });
}

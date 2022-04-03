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
import com.yuebing.aicoursesystemandroid.model.Discuss;
import com.yuebing.aicoursesystemandroid.model.ExamUserRel;
import com.yuebing.aicoursesystemandroid.task.TeacherGetGroupTask;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.util.List;

public class CreateDiscussActivity extends AppCompatActivity {

    private List<Discuss> discussList;
    private String discussListJson;
    private ListView lv_discuss;
    private String token;
    private TextView tv_create;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss_display);

        tv_create = findViewById(R.id.tv_createDiscuss);

        token = getIntent().getStringExtra("token");

        lv_discuss = findViewById(R.id.lv_discussdisplay);
        discussListJson = getIntent().getStringExtra("discusslist");

        discussList = JsonListUtil.getObjectList(discussListJson, Discuss.class);

        ViewDiscussAdapter viewDiscussAdapter = new ViewDiscussAdapter(CreateDiscussActivity.this, R.layout.item_discuss_teacher, discussList);
        lv_discuss.setAdapter(viewDiscussAdapter);
        lv_discuss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                new Thread(new TeacherGetGroupTask(discussList.get(i).getDiscussid(), token, groupHandler)).start();

            }
        });


    }
    private Handler groupHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                Toast.makeText(getApplicationContext(), bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }
            String result = bundle.getString("result");
            int discussid = bundle.getInt("discussid", 1);


            Intent intent = new Intent(getApplicationContext(), ViewGroupActivity.class);
            intent.putExtra("discussid", discussid);
            intent.putExtra("grouplist", result);
            intent.putExtra("token", token);

            startActivity(intent);

            return true;
        }
    });
}

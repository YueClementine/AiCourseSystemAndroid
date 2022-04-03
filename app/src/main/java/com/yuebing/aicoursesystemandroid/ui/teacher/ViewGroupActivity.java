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
import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.model.Discuss;
import com.yuebing.aicoursesystemandroid.model.Group;
import com.yuebing.aicoursesystemandroid.service.CommonNetService;
import com.yuebing.aicoursesystemandroid.task.TeacherGetGroupTask;
import com.yuebing.aicoursesystemandroid.task.TeacherGetMessageTask;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewGroupActivity extends AppCompatActivity {


    private List<Group> groupList;
    private String groupListJson;
    private ListView lv_group;
    private String token;
    private int discussid;
    private TextView tv_finish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_display);

        discussid = getIntent().getIntExtra("discussid", 1);


        tv_finish = findViewById(R.id.tv_finishDiscuss);

        token = getIntent().getStringExtra("token");

        lv_group = findViewById(R.id.lv_viewgroup);
        groupListJson = getIntent().getStringExtra("grouplist");

        groupList = mockData();

        ViewGroupAdapter viewGroupAdapter = new ViewGroupAdapter(ViewGroupActivity.this, R.layout.item_group_display, groupList);

        lv_group.setAdapter(viewGroupAdapter);
        lv_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                new Thread(new TeacherGetMessageTask(groupList.get(i).getGroupname(), groupList.get(i).getGroupid(), token, messageHandler)).start();

            }
        });

        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            CommonNetService.getByToken(Constant.FINISHDISCUSS + discussid, token);
                        }catch (IOException e){
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).start();
            }
        });


    }

    private Handler messageHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                Toast.makeText(getApplicationContext(), bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }
            String groupname = bundle.getString("groupname");
            String result = bundle.getString("result");


            Intent intent = new Intent(getApplicationContext(), ViewDialogActivity.class);
            intent.putExtra("groupname", groupname);
            intent.putExtra("dialoglist", result);
            intent.putExtra("token", token);

            startActivity(intent);

            return true;
        }
    });

    private List<Group> mockData() {
        Group group = new Group();
        group.setGroupid(1);
        group.setGroupname("李明、张三等同学小组");
        group.setDiscussid(10);
        Group group1 = new Group();
        group1.setGroupid(2);
        group1.setGroupname("C、D等同学小组");
        group1.setDiscussid(10);
        Group group2 = new Group();
        group2.setGroupid(3);
        group2.setGroupname("小李、小张等同学小组");
        group2.setDiscussid(10);


        List<Group> groupList = new ArrayList<>();
        groupList.add(group);
        groupList.add(group1);
        groupList.add(group2);
        return groupList;

    }
}

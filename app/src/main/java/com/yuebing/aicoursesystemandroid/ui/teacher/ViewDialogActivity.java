package com.yuebing.aicoursesystemandroid.ui.teacher;


import android.os.Bundle;

import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ViewDialogActivity extends AppCompatActivity {


    private List<Message> dialogList;
    private String dialogListJson;
    private ListView lv_dialog;
    private String token;
    private String groupname;
    private TextView tv_dialogtitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);


        tv_dialogtitle = findViewById(R.id.tv_dialog_title);
        tv_dialogtitle.setText(getIntent().getStringExtra("groupname"));

        token = getIntent().getStringExtra("token");

        lv_dialog = findViewById(R.id.lv_dialogdisplay);
        dialogListJson = getIntent().getStringExtra("dialoglist");

//        dialogList = JsonListUtil.getObjectList(dialogListJson, Message.class);
        dialogList = mockdata();

        ViewDialogAdapter viewDialogAdapter = new ViewDialogAdapter(ViewDialogActivity.this, R.layout.item_dialog, dialogList);
        lv_dialog.setAdapter(viewDialogAdapter);



    }

    private List<Message> mockdata() {
        Message message1 = new Message();
        message1.setMessage("在医学领域，ai可以帮助医生进行医学影像辅助诊断");
        message1.setDiscussid(10);
        message1.setUsername("李明：");
        message1.setGroupid(1);

        Message message2 = new Message();
        message2.setMessage("AI算法模型可以帮助我们优化推荐效果，使商品推荐更加准确");
        message2.setDiscussid(10);
        message2.setUsername("Alex：");
        message2.setGroupid(1);


        Message message3 = new Message();
        message3.setMessage("自然语言处理能够实现类如Siri的问答系统，简化操作，提高人们的生活质量");
        message3.setDiscussid(10);
        message3.setUsername("张三：");

        message3.setGroupid(1);
        List<Message> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);

        return messages;
    }


}

package com.yuebing.aicoursesystemandroid.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Message;
import com.yuebing.aicoursesystemandroid.ui.teacher.ViewDialogActivity;
import com.yuebing.aicoursesystemandroid.ui.teacher.ViewDialogAdapter;

import java.util.ArrayList;
import java.util.List;

import top.androidman.SuperButton;

public class StudentMessageActivity extends AppCompatActivity {

    private ListView lv_message;
    private List<Message> messages;
    private SuperButton bt_commitMessage;
    private TextView tv_input;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_message);

        bt_commitMessage = findViewById(R.id.bt_commitmessage);
        tv_input = findViewById(R.id.tv_inputDiscuss);

        lv_message = findViewById(R.id.lv_dialogdisplay_student);

        messages = mockdata();

        ViewDialogAdapter viewDialogAdapter = new ViewDialogAdapter(StudentMessageActivity.this, R.layout.item_dialog, messages);

        lv_message.setAdapter(viewDialogAdapter);

        bt_commitMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message();
                message.setUsername("李明：");
                message.setMessage(tv_input.getText().toString());
                messages.add(message);

                viewDialogAdapter.notifyDataSetChanged();
                tv_input.setText("");

            }
        });


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

package com.yuebing.aicoursesystemandroid.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.task.RegisterTask;

public class RegisterActivity extends AppCompatActivity {

    private TextView tv_username = findViewById(R.id.tv_registerUsername);
    private TextView tv_password = findViewById(R.id.tv_registerPassword);
    private Button bt_register = findViewById(R.id.bt_registerConfirm);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = tv_username.getText().toString();
                String password = tv_password.getText().toString();
                new Thread(new RegisterTask())
            }
        });
    }


}

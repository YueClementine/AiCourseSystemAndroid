package com.yuebing.aicoursesystemandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yuebing.aicoursesystemandroid.service.LoginNetService;

import org.jetbrains.annotations.NonNls;

import lombok.NonNull;
import lombok.SneakyThrows;

public class MainActivity extends AppCompatActivity {

    private EditText tv_username;
    private EditText tv_password;
    private Button bt_loginConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView loginTitle = findViewById(R.id.logintitle);
        loginTitle.setText("Ai通识课学习平台");

        tv_username = findViewById(R.id.loginUsername);
        tv_password = findViewById(R.id.loginPassword);

        bt_loginConfirm = findViewById(R.id.loginConfirm);

        bt_loginConfirm.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View view) {

                String username = tv_username.getText().toString();
                String password = tv_password.getText().toString();

                String token = LoginNetService.login(username, password);
            }
        });

    }


}
package com.yuebing.aicoursesystemandroid;

import android.content.Intent;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.yuebing.aicoursesystemandroid.task.LoginTask;
import com.yuebing.aicoursesystemandroid.ui.HelloActivity;
import com.yuebing.aicoursesystemandroid.ui.RegisterActivity;


import lombok.SneakyThrows;

public class MainActivity extends AppCompatActivity {

    private EditText tv_username;
    private EditText tv_password;
    private TextView tv_register;
    private Button bt_loginConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView loginTitle = findViewById(R.id.logintitle);
        loginTitle.setText("Ai通识课学习平台");

        tv_username = findViewById(R.id.loginUsername);
        tv_password = findViewById(R.id.loginPassword);

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        bt_loginConfirm = findViewById(R.id.loginConfirm);
        bt_loginConfirm.setText("登录");
        bt_loginConfirm.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View view) {

                String username = tv_username.getText().toString();
                String password = tv_password.getText().toString();


                new Thread(new LoginTask(username, password, handler)).start();
            }
        });

    }
    /**
     * UI线程更新处理器
     */
    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();

            //抽取异常信息
            if (bundle.getString("error") != null) {
                Toast.makeText(getApplicationContext(), bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }
            //获取认证状态
            boolean isAuthorized = bundle.getBoolean("isAuthorized");

            if (isAuthorized) {
                Intent intent = new Intent(getApplicationContext(), HelloActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }


            return true;
        }
    });


}
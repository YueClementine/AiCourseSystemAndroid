package com.yuebing.aicoursesystemandroid.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Course;
import com.yuebing.aicoursesystemandroid.task.CreateCourseTask;
import com.yuebing.aicoursesystemandroid.task.GetCourseTask;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.util.List;

public class TeacherFragment extends Fragment {

    private ImageButton im_create;
    private ImageButton im_task;


    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabteacher, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //获取控件
        im_create = getView().findViewById(R.id.id_createcourse);
        im_task = getView().findViewById(R.id.id_taskteacher);

        // 创建课程
        im_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreateCourseActivity.class);
                intent.putExtra("userid", getActivity().getIntent().getLongExtra("userid", 1L));
                intent.putExtra("token", getActivity().getIntent().getStringExtra("token"));
                intent.putExtra("username", getActivity().getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });

        // 布置任务
        im_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new GetCourseTask(getActivity().getIntent().getLongExtra("userid", 1L), getActivity().getIntent().getStringExtra("token"), taskHandler)).start();

            }
        });








    }

    private Handler taskHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                Toast.makeText(getContext(), bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }
            String result = bundle.getString("result");



            Intent intent = new Intent(getActivity().getApplicationContext(), CreateTaskActivity.class);
            intent.putExtra("courselist", result);

            startActivity(intent);

            return true;
        }
    });


}

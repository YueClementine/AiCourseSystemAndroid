package com.yuebing.aicoursesystemandroid.ui.teacher;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.task.GetCourseTask;
import com.yuebing.aicoursesystemandroid.task.PptListTask;
import com.yuebing.aicoursesystemandroid.task.TeacherGetExamTask;
import com.yuebing.aicoursesystemandroid.task.VideoListTask;
import com.yuebing.aicoursesystemandroid.ui.CreateCourseActivity;
import com.yuebing.aicoursesystemandroid.ui.CreateTaskActivity;
import com.yuebing.aicoursesystemandroid.ui.PptCenterActivity;
import com.yuebing.aicoursesystemandroid.ui.VideoCenterActivity;

public class TeacherFragment extends Fragment {

    private ImageButton im_create;
    private ImageButton im_task;
    private ImageButton im_video;
    private ImageButton im_ppt;
    private ImageButton im_exam;
    private ImageButton im_grade;



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
        im_video = getView().findViewById(R.id.id_videoteacher);
        im_ppt = getView().findViewById(R.id.id_pptteacher);
        im_exam = getView().findViewById(R.id.id_examteacher);
        im_grade = getView().findViewById(R.id.id_grade_teacher);

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

        //视频中心
        im_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new VideoListTask(getActivity().getIntent().getLongExtra("userid", 1L), getActivity().getIntent().getStringExtra("token"), videoHandler)).start();

            }
        });

        im_ppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new PptListTask(getActivity().getIntent().getLongExtra("userid", 1L), getActivity().getIntent().getStringExtra("token"), pptHandler)).start();

            }
        });

        im_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new TeacherGetExamTask(getActivity().getIntent().getStringExtra("token"), examHandler)).start();
            }
        });

        im_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new TeacherGetExamTask(getActivity().getIntent().getStringExtra("token"), gradeHandler)).start();
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
            intent.putExtra("token", getActivity().getIntent().getStringExtra("token"));

            startActivity(intent);

            return true;
        }
    });

    private Handler videoHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                Toast.makeText(getContext(), bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }
            String result = bundle.getString("result");



            Intent intent = new Intent(getActivity().getApplicationContext(), VideoCenterActivity.class);
            intent.putExtra("videolist", result);
            intent.putExtra("token", getActivity().getIntent().getStringExtra("token"));

            startActivity(intent);

            return true;
        }
    });
    private Handler pptHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                Toast.makeText(getContext(), bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }
            String result = bundle.getString("result");



            Intent intent = new Intent(getActivity().getApplicationContext(), PptCenterActivity.class);
            intent.putExtra("pptlist", result);
            intent.putExtra("token", getActivity().getIntent().getStringExtra("token"));

            startActivity(intent);

            return true;
        }
    });
    private Handler examHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                Toast.makeText(getContext(), bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }
            String result = bundle.getString("result");



            Intent intent = new Intent(getActivity().getApplicationContext(), TeacherExamPublishActivity.class);
            intent.putExtra("examlist", result);
            intent.putExtra("token", getActivity().getIntent().getStringExtra("token"));

            startActivity(intent);

            return true;
        }
    });
    private Handler gradeHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();
            if (bundle.getString("error") != null) {
                Toast.makeText(getContext(), bundle.getString("error"), Toast.LENGTH_SHORT).show();
                return false;
            }
            String result = bundle.getString("result");



            Intent intent = new Intent(getActivity().getApplicationContext(), ViewStudentExamActivity.class);
            intent.putExtra("examlist", result);
            intent.putExtra("token", getActivity().getIntent().getStringExtra("token"));

            startActivity(intent);

            return true;
        }
    });



}

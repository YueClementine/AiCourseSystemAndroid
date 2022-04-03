package com.yuebing.aicoursesystemandroid.ui;

import android.content.Intent;
import android.media.Image;
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
import com.yuebing.aicoursesystemandroid.task.ExamListTask;
import com.yuebing.aicoursesystemandroid.task.PptListTask;
import com.yuebing.aicoursesystemandroid.task.StudentTaskTask;
import com.yuebing.aicoursesystemandroid.task.VideoListTask;
import com.yuebing.aicoursesystemandroid.ui.student.StudentDiscussDisplayActivity;

public class StudentMainFragment extends Fragment {

    private ImageButton im_studentTask;
    private ImageButton im_studentVideo;
    private ImageButton im_studentPpt;
    private ImageButton im_studentExam;
    private ImageButton im_discuss;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container,@NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabstudent, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initView
        im_studentTask = getActivity().findViewById(R.id.id_task);
        im_studentPpt = getActivity().findViewById(R.id.id_pptstudentmain);
        im_studentVideo = getActivity().findViewById(R.id.id_video);
        im_studentExam = getActivity().findViewById(R.id.id_exam);

        im_discuss = getActivity().findViewById(R.id.id_discuss_student);





        im_studentTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new StudentTaskTask(getActivity().getIntent().getLongExtra("userid", 1L), getActivity().getIntent().getStringExtra("token"), taskHandler)).start();
            }
        });

        im_studentVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new VideoListTask(getActivity().getIntent().getStringExtra("token"), videoHandler)).start();
            }
        });

        im_studentPpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new PptListTask(getActivity().getIntent().getStringExtra("token"), pptHandler)).start();

            }
        });

        im_studentExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new ExamListTask(getActivity().getIntent().getStringExtra("token"), examHandler)).start();
            }
        });

        im_discuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), StudentDiscussDisplayActivity.class);
                startActivity(intent);

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



            Intent intent = new Intent(getActivity().getApplicationContext(), StudentTaskActivity.class);
            intent.putExtra("tasklist", result);
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



            Intent intent = new Intent(getActivity().getApplicationContext(), StudentExamCenter.class);
            intent.putExtra("examlist", result);
            intent.putExtra("token", getActivity().getIntent().getStringExtra("token"));

            startActivity(intent);

            return true;
        }
    });


}

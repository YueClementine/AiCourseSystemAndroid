package com.yuebing.aicoursesystemandroid.ui;

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
import com.yuebing.aicoursesystemandroid.task.StudentTaskTask;

public class StudentMainFragment extends Fragment {

    private ImageButton im_studentTask;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container,@NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabstudent, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        im_studentTask = getActivity().findViewById(R.id.id_task);



        im_studentTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new StudentTaskTask(getActivity().getIntent().getLongExtra("userid", 1L), getActivity().getIntent().getStringExtra("token"), taskHandler)).start();
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


}

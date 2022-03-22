package com.yuebing.aicoursesystemandroid.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yuebing.aicoursesystemandroid.R;

public class TeacherFragment extends Fragment {

    private ImageButton im_create;


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






    }


}

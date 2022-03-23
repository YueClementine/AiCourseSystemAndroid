package com.yuebing.aicoursesystemandroid.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import com.yuebing.aicoursesystemandroid.R;

public class StudentMainFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container,@NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabstudent, container, false);
        return view;
    }
}

package com.yuebing.aicoursesystemandroid.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yuebing.aicoursesystemandroid.R;

public class AiMomentFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);
        TextView txt = (TextView) view.findViewById(R.id.txt2);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("dd","dddd");
                Toast.makeText(getActivity(), "当前页面：2", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}

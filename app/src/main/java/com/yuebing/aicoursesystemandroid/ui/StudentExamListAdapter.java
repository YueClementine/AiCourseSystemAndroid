package com.yuebing.aicoursesystemandroid.ui;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Exam;
import com.yuebing.aicoursesystemandroid.model.ExamUserRel;
import com.yuebing.aicoursesystemandroid.model.TaskuserrelVO;

import java.util.List;

public class StudentExamListAdapter extends ArrayAdapter<ExamUserRel> {

    private final int resourseId;

    public StudentExamListAdapter(@NonNull Context context, int resource, @NonNull List<ExamUserRel> objects) {
        super(context, resource, objects);
        resourseId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ExamUserRel exam = getItem(position);

        View view;
        ViewHolder viewHolder;
        //判断以免ListView每次滚动时都要重新加载布局，提高运行效率
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_examlist = view.findViewById(R.id.item_tv_studentexam);


            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }
//
//        viewHolder.tv_courseid.setText(String.valueOf(taskuserrel.getCourseid()));
//
//        viewHolder.tv_coursename.setText(course.getCoursename());

        viewHolder.tv_examlist.setText(exam.getExamname());

        return view;
    }

    //定义内部类 用于对控件实例进行缓存
    class ViewHolder {
        TextView tv_examlist;


    }
}

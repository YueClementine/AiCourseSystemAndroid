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
import com.yuebing.aicoursesystemandroid.model.TaskuserrelVO;

import java.util.List;

public class StudentTaskDisplayAdapter extends ArrayAdapter<TaskuserrelVO> {

    private final int resourseId;

    public StudentTaskDisplayAdapter(@NonNull Context context, int resource, @NonNull List<TaskuserrelVO> objects) {
        super(context, resource, objects);
        resourseId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TaskuserrelVO taskuserrelVO = getItem(position);
        View view;
        ViewHolder viewHolder;
        //判断以免ListView每次滚动时都要重新加载布局，提高运行效率
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_status = view.findViewById(R.id.tv_taskStatus);
            viewHolder.tv_title = view.findViewById(R.id.tv_taskitemtitle);

            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }
//
//        viewHolder.tv_courseid.setText(String.valueOf(taskuserrel.getCourseid()));
//
//        viewHolder.tv_coursename.setText(course.getCoursename());

        viewHolder.tv_title.setText(taskuserrelVO.getTitle());
        if (taskuserrelVO.getStatus() == 0) {
            viewHolder.tv_status.setText("未完成");
            viewHolder.tv_status.setTextColor(Color.parseColor("#CD0000"));
        }else {
            viewHolder.tv_status.setText("已完成");
            viewHolder.tv_status.setTextColor(Color.parseColor("#4CAF50"));
        }

        return view;
    }

    //定义内部类 用于对控件实例进行缓存
    class ViewHolder {
        TextView tv_status;
        TextView tv_title;
    }
}

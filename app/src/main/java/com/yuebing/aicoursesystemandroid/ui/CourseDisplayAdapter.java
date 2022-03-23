package com.yuebing.aicoursesystemandroid.ui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Course;

import java.util.List;

public class CourseDisplayAdapter extends ArrayAdapter<Course> {

    private final int resourseId;

    public CourseDisplayAdapter(@NonNull Context context, int resource, @NonNull List<Course> objects) {
        super(context, resource, objects);
        resourseId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Course course = getItem(position);
        View view;
        ViewHolder viewHolder;
        //判断以免ListView每次滚动时都要重新加载布局，提高运行效率
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_courseid = view.findViewById(R.id.tv_courseidListitem);
            viewHolder.tv_coursename = view.findViewById(R.id.tv_coursenameListitem);

            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.tv_courseid.setText(String.valueOf(course.getCourseid()));

        viewHolder.tv_coursename.setText(course.getCoursename());
        return view;
    }

    //定义内部类 用于对控件实例进行缓存
    class ViewHolder {
        TextView tv_courseid;
        TextView tv_coursename;
    }
}

package com.yuebing.aicoursesystemandroid.ui.teacher;

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
import com.yuebing.aicoursesystemandroid.model.ExamUserRel;

import java.util.List;

public class ViewStudentGradeAdapter extends ArrayAdapter<ExamUserRel> {

    private final int resourseId;

    public ViewStudentGradeAdapter(@NonNull Context context, int resource, @NonNull List<ExamUserRel> objects) {
        super(context, resource, objects);
        resourseId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ExamUserRel examUserRel = getItem(position);
        View view;
        ViewHolder viewHolder;
        //判断以免ListView每次滚动时都要重新加载布局，提高运行效率
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_grade = view.findViewById(R.id.item_grade);
            viewHolder.tv_userid = view.findViewById(R.id.item_tv_userid);
            viewHolder.tv_username = view.findViewById(R.id.item_tv_gradeusername);

            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }
        if (examUserRel.getGrade() == null) {
            viewHolder.tv_grade.setText("未考试");
        }else {
            if (examUserRel.getGrade() < 60) {
                viewHolder.tv_grade.setTextColor(Color.parseColor("#C80000"));

            }
            viewHolder.tv_grade.setText(examUserRel.getGrade() + "分");
        }

        viewHolder.tv_username.setText("姓名：" + examUserRel.getUsername());
        viewHolder.tv_userid.setText("学号：" + examUserRel.getUserid());

        return view;
    }

    //定义内部类 用于对控件实例进行缓存
    class ViewHolder {
        TextView tv_grade;
        TextView tv_username;
        TextView tv_userid;
    }
}

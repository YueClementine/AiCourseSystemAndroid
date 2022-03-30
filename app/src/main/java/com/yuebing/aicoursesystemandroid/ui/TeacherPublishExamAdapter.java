package com.yuebing.aicoursesystemandroid.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Exam;
import com.yuebing.aicoursesystemandroid.model.ExamUserRel;
import com.yuebing.aicoursesystemandroid.model.Task;
import com.yuebing.aicoursesystemandroid.task.TeacherPublishExamTask;

import java.util.List;

import top.androidman.SuperButton;

public class TeacherPublishExamAdapter extends ArrayAdapter<Exam> {

    private TeacherExamPublishActivity mcontext;
    private final int resourseId;

    public TeacherPublishExamAdapter(TeacherExamPublishActivity context, int resource, @NonNull List<Exam> objects) {
        super(context, resource, objects);
        resourseId = resource;
        mcontext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Exam exam = getItem(position);

        View view;
        TeacherPublishExamAdapter.ViewHolder viewHolder;
        //判断以免ListView每次滚动时都要重新加载布局，提高运行效率
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
            viewHolder = new TeacherPublishExamAdapter.ViewHolder();
            viewHolder.tv_examlist = view.findViewById(R.id.tv_teacheritemexam);

            viewHolder.bt_publish = view.findViewById(R.id.bt_publishexam);


            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (TeacherPublishExamAdapter.ViewHolder) view.getTag();

        }
//
//        viewHolder.tv_courseid.setText(String.valueOf(taskuserrel.getCourseid()));
//
//        viewHolder.tv_coursename.setText(course.getCoursename());

        viewHolder.tv_examlist.setText(exam.getExamname());
        if (exam.getIspublish() == 1) {
            viewHolder.bt_publish.setButtonClickable(false);
            viewHolder.bt_publish.setColorNormal(Color.GRAY);
            viewHolder.bt_publish.setText("已发布");

        } else {
            viewHolder.bt_publish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewHolder.bt_publish.setButtonClickable(false);
                    viewHolder.bt_publish.setColorNormal(Color.GRAY);
                    viewHolder.bt_publish.setText("已发布");
                    int publishingExam = mcontext.getPublishingExam();
                    publishingExam--;
                    if (publishingExam == 0) {
                        mcontext.getTv_publishNum().setText("您没有考试待发布");

                    } else {
                        mcontext.getTv_publishNum().setText("您有" + publishingExam + "场考试待发布");
                    }
                    new Thread(new TeacherPublishExamTask(exam.getExamid(), mcontext.getIntent().getStringExtra("token"), publishHandler)).start();

                }
            });
        }


        return view;
    }

    private Handler publishHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bundle bundle = message.getData();

            String result = bundle.getString("result");

            Toast.makeText(mcontext, "发布考试成功", Toast.LENGTH_SHORT).show();
            return true;
        }
    });

    //定义内部类 用于对控件实例进行缓存
    class ViewHolder {
        TextView tv_examlist;
        SuperButton bt_publish;


    }
}

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
import com.yuebing.aicoursesystemandroid.model.Question;
import com.yuebing.aicoursesystemandroid.model.TaskuserrelVO;

import java.util.List;

public class QuestionListAdapter extends ArrayAdapter<Question> {

    private final int resourseId;

    public QuestionListAdapter(@NonNull Context context, int resource, @NonNull List<Question> objects) {
        super(context, resource, objects);
        resourseId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Question question = getItem(position);
        View view;
        ViewHolder viewHolder;
        //判断以免ListView每次滚动时都要重新加载布局，提高运行效率
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.content = view.findViewById(R.id.tv_questioncontent);
            viewHolder.optionA = view.findViewById(R.id.tv_option1);
            viewHolder.optionB = view.findViewById(R.id.tv_option2);
            viewHolder.optionC = view.findViewById(R.id.tv_option3);
            viewHolder.optionD = view.findViewById(R.id.tv_option4);
            viewHolder.let_optionA = view.findViewById(R.id.tv_meta_A);
            viewHolder.let_optionB = view.findViewById(R.id.tv_meta_B);
            viewHolder.let_optionC = view.findViewById(R.id.tv_meta_C);
            viewHolder.let_optionD = view.findViewById(R.id.tv_meta_D);


            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }
//
//        viewHolder.tv_courseid.setText(String.valueOf(taskuserrel.getCourseid()));
//
//        viewHolder.tv_coursename.setText(course.getCoursename());

        viewHolder.content.setText(question.getQuestioncontent());
        viewHolder.optionA.setText(question.getOpa());
        viewHolder.optionB.setText(question.getOpb());
        viewHolder.optionC.setText(question.getOpc());
        viewHolder.optionD.setText(question.getOpd());

        viewHolder.let_optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.let_optionA.setTextColor(Color.parseColor("#EEEEEE"));
                viewHolder.let_optionB.setTextColor(Color.parseColor("#1381D8"));
                viewHolder.let_optionC.setTextColor(Color.parseColor("#1381D8"));
                viewHolder.let_optionD.setTextColor(Color.parseColor("#1381D8"));
                viewHolder.let_optionA.setBackgroundResource(R.drawable.oval_4);
                viewHolder.let_optionC.setBackgroundResource(R.drawable.sel_homework_checkbox_bg);
                viewHolder.let_optionD.setBackgroundResource(R.drawable.sel_homework_checkbox_bg);
                viewHolder.let_optionB.setBackgroundResource(R.drawable.sel_homework_checkbox_bg);
                if (question.getCorrectop() == 1) {
                    question.setQuestiontag(1);

                }else {
                    question.setQuestiontag(0);
                }
            }
        });
        viewHolder.let_optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.let_optionB.setTextColor(Color.parseColor("#EEEEEE"));
                viewHolder.let_optionA.setTextColor(Color.parseColor("#1381D8"));
                viewHolder.let_optionC.setTextColor(Color.parseColor("#1381D8"));
                viewHolder.let_optionD.setTextColor(Color.parseColor("#1381D8"));
                viewHolder.let_optionB.setBackgroundResource(R.drawable.oval_4);
                viewHolder.let_optionC.setBackgroundResource(R.drawable.sel_homework_checkbox_bg);
                viewHolder.let_optionD.setBackgroundResource(R.drawable.sel_homework_checkbox_bg);
                viewHolder.let_optionA.setBackgroundResource(R.drawable.sel_homework_checkbox_bg);
                if (question.getCorrectop() == 2) {
                    question.setQuestiontag(1);

                }else {
                    question.setQuestiontag(0);
                }
            }
        });
        viewHolder.let_optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.let_optionC.setTextColor(Color.parseColor("#EEEEEE"));
                viewHolder.let_optionA.setTextColor(Color.parseColor("#1381D8"));
                viewHolder.let_optionB.setTextColor(Color.parseColor("#1381D8"));
                viewHolder.let_optionD.setTextColor(Color.parseColor("#1381D8"));
                viewHolder.let_optionC.setBackgroundResource(R.drawable.oval_4);
                viewHolder.let_optionB.setBackgroundResource(R.drawable.sel_homework_checkbox_bg);
                viewHolder.let_optionD.setBackgroundResource(R.drawable.sel_homework_checkbox_bg);
                viewHolder.let_optionA.setBackgroundResource(R.drawable.sel_homework_checkbox_bg);
                if (question.getCorrectop() == 3) {
                    question.setQuestiontag(1);

                }else {
                    question.setQuestiontag(0);
                }
            }
        });
        viewHolder.let_optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.let_optionD.setTextColor(Color.parseColor("#EEEEEE"));
                viewHolder.let_optionA.setTextColor(Color.parseColor("#1381D8"));
                viewHolder.let_optionB.setTextColor(Color.parseColor("#1381D8"));
                viewHolder.let_optionC.setTextColor(Color.parseColor("#1381D8"));
                viewHolder.let_optionD.setBackgroundResource(R.drawable.oval_4);
                viewHolder.let_optionC.setBackgroundResource(R.drawable.sel_homework_checkbox_bg);
                viewHolder.let_optionB.setBackgroundResource(R.drawable.sel_homework_checkbox_bg);
                viewHolder.let_optionA.setBackgroundResource(R.drawable.sel_homework_checkbox_bg);
                if (question.getCorrectop() == 4) {
                    question.setQuestiontag(1);

                }else {
                    question.setQuestiontag(0);
                }
            }
        });



        return view;
    }

    //定义内部类 用于对控件实例进行缓存
    class ViewHolder {
        private TextView content;
        private TextView optionA;
        private TextView let_optionA;
        private TextView optionB;
        private TextView let_optionB;
        private TextView optionC;
        private TextView let_optionC;
        private TextView optionD;
        private TextView let_optionD;
    }
}

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
import com.yuebing.aicoursesystemandroid.model.Discuss;
import com.yuebing.aicoursesystemandroid.model.ExamUserRel;

import java.util.List;

public class ViewDiscussAdapter extends ArrayAdapter<Discuss> {

    private final int resourseId;

    public ViewDiscussAdapter(@NonNull Context context, int resource, @NonNull List<Discuss> objects) {
        super(context, resource, objects);
        resourseId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Discuss discuss = getItem(position);
        View view;
        ViewHolder viewHolder;
        //判断以免ListView每次滚动时都要重新加载布局，提高运行效率
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.tv_discuss = view.findViewById(R.id.tv_discuss_name_item);

            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.tv_discuss.setText(discuss.getTopic());

        return view;
    }

    //定义内部类 用于对控件实例进行缓存
    class ViewHolder {
        TextView tv_discuss;
    }
}

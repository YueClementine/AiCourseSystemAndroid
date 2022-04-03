package com.yuebing.aicoursesystemandroid.ui.teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Course;
import com.yuebing.aicoursesystemandroid.model.Message;

import java.util.List;

public class NewDiscussAdapter extends ArrayAdapter<Course> {

    private final int resourseId;

    public NewDiscussAdapter(@NonNull Context context, int resource, @NonNull List<Course> objects) {
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

            viewHolder.tv_username = view.findViewById(R.id.tv_item_username_message);
            viewHolder.tv_message = view.findViewById(R.id.item_message);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }

//        viewHolder.tv_username.setText(message.getUsername());
//        viewHolder.tv_message.setText(message.getMessage());


        return view;
    }

    //定义内部类 用于对控件实例进行缓存
    class ViewHolder {
        TextView tv_username;
        TextView tv_message;
    }
}

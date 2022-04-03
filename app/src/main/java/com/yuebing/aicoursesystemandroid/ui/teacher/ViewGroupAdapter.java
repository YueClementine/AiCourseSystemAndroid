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
import com.yuebing.aicoursesystemandroid.model.Discuss;
import com.yuebing.aicoursesystemandroid.model.Group;

import java.util.List;

public class ViewGroupAdapter extends ArrayAdapter<Group> {

    private final int resourseId;

    public ViewGroupAdapter(@NonNull Context context, int resource, @NonNull List<Group> objects) {
        super(context, resource, objects);
        resourseId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Group group = getItem(position);
        View view;
        ViewHolder viewHolder;
        //判断以免ListView每次滚动时都要重新加载布局，提高运行效率
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.tv_group = view.findViewById(R.id.item_tv_group);

            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.tv_group.setText(group.getGroupname());

        return view;
    }

    //定义内部类 用于对控件实例进行缓存
    class ViewHolder {
        TextView tv_group;
    }
}

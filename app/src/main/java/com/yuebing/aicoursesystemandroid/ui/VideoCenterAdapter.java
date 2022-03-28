package com.yuebing.aicoursesystemandroid.ui;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.Course;
import com.yuebing.aicoursesystemandroid.model.VideoDo;

import java.util.List;

public class VideoCenterAdapter extends ArrayAdapter<VideoDo> {

    private final int resourseId;

    public VideoCenterAdapter(@NonNull Context context, int resource, @NonNull List<VideoDo> objects) {
        super(context, resource, objects);
        resourseId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        VideoDo video = getItem(position);
        View view;
        ViewHolder viewHolder;
        //判断以免ListView每次滚动时都要重新加载布局，提高运行效率
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_videoList = view.findViewById(R.id.tv_videonameitem);
            viewHolder.im_videoList = view.findViewById(R.id.im_videolistitem);

            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.tv_videoList.setText(video.getVideoname());

        return view;
    }

    //定义内部类 用于对控件实例进行缓存
    class ViewHolder {
        TextView tv_videoList;
        ImageView im_videoList;
    }
}

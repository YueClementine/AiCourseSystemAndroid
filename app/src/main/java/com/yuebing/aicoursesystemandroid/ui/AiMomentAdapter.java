package com.yuebing.aicoursesystemandroid.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.model.Note;
import com.yuebing.aicoursesystemandroid.model.PptDo;

import java.util.List;

import top.androidman.SuperButton;

public class AiMomentAdapter extends ArrayAdapter<Note> {

    private final int resourseId;

    public AiMomentAdapter(@NonNull Context context, int resource, @NonNull List<Note> objects) {
        super(context, resource, objects);
        resourseId = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Note note = getItem(position);
        View view;
        ViewHolder viewHolder;
        //判断以免ListView每次滚动时都要重新加载布局，提高运行效率
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_username = view.findViewById(R.id.tv_aimoment_username);

            viewHolder.tv_title = view.findViewById(R.id.tv_aimomennt_note_title);
            viewHolder.tv_note_content = view.findViewById(R.id.tv_aimoment_content);
            viewHolder.constraintLayout = view.findViewById(R.id.layout_file);
            viewHolder.bt_comment = view.findViewById(R.id.bt_comment);
            viewHolder.bt_aimoment_file = view.findViewById(R.id.bt_aimoment_videoorppt);

            viewHolder.videoorppt = view.findViewById(R.id.bt_aimomenticon);
            viewHolder.videoorpptname = view.findViewById(R.id.tv_aimoment_text_videoorppt);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tv_username.setText(note.getUsername() + ":");
        viewHolder.tv_title.setText(note.getTitle());
        viewHolder.tv_note_content.setText(note.getNote());
        if (note.getAddvideo() == 1) {
            viewHolder.videoorppt.setBackgroundResource(R.drawable.videotest2);
            viewHolder.videoorpptname.setText(note.getVideoname());

            viewHolder.videoPath = note.getVideoaddress();
            viewHolder.bt_aimoment_file.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), VideoViewTestActivity.class);

                    intent.putExtra("title", note.getVideoname());
                    intent.putExtra("path", viewHolder.videoPath);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    getContext().startActivity(intent);
                }
            });


        } else if (note.getAddppt() == 1) {
            viewHolder.videoorppt.setBackgroundResource(R.drawable.item_ppt);
            viewHolder.videoorpptname.setText(note.getPptname());
            viewHolder.pdfPath = note.getPptaddress();
            viewHolder.bt_aimoment_file.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), PdfActvity.class);
                    intent.putExtra("path", Constant.File_Path + viewHolder.pdfPath);

                    intent.putExtra("name", note.getPptname());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    getContext().startActivity(intent);
                }
            });

        } else{
            viewHolder.constraintLayout.setVisibility(View.GONE);
        }
        viewHolder.bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CommentActivity.class);
                intent.putExtra("noteid", note.getNoteid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                getContext().startActivity(intent);
            }
        });
        return view;
    }

    //定义内部类 用于对控件实例进行缓存
    class ViewHolder {
        TextView tv_title;
        TextView tv_note_content;
        SuperButton bt_comment;
        TextView tv_username;
        Button videoorppt;
        TextView videoorpptname;
        ConstraintLayout constraintLayout;

        SuperButton bt_aimoment_file;

        String videoPath;
        String pdfPath;

    }
}

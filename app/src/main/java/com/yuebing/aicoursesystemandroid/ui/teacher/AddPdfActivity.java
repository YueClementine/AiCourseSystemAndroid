package com.yuebing.aicoursesystemandroid.ui.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.model.PptDo;
import com.yuebing.aicoursesystemandroid.model.VideoDo;
import com.yuebing.aicoursesystemandroid.ui.EditTaskActivity;
import com.yuebing.aicoursesystemandroid.ui.PptCenterAdapter;
import com.yuebing.aicoursesystemandroid.ui.VideoCenterAdapter;
import com.yuebing.aicoursesystemandroid.ui.student.StudentCreateNoteActivity;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.util.List;

public class AddPdfActivity extends AppCompatActivity {

    private List<PptDo> pptDoList;
    private String pptlistjson;
    private String token;
    private ListView lv_add_ppt;
    private int role;
    private int taskid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pdf_upload);
        role = getIntent().getIntExtra("role", 1);
        token = getIntent().getStringExtra("token");
        taskid = getIntent().getIntExtra("taskid", 1);
        pptlistjson = getIntent().getStringExtra("pdflist");
        pptDoList = JsonListUtil.getObjectList(pptlistjson, PptDo.class);

        lv_add_ppt = findViewById(R.id.lv_add_pdf);
        PptCenterAdapter pptCenterAdapter = new PptCenterAdapter(AddPdfActivity.this, R.layout.item_ppt, pptDoList);

        lv_add_ppt.setAdapter(pptCenterAdapter);

        lv_add_ppt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (role == 0) {
                    PptDo pptDo = pptDoList.get(i);
                    Intent intent = new Intent(getApplicationContext(), StudentCreateNoteActivity.class);
                    intent.putExtra("addppt", 1);
                    intent.putExtra("ppttitle", pptDo.getPptname());
                    intent.putExtra("pptpath", pptDo.getPptaddress());
                    intent.putExtra("token", token);
                    intent.putExtra("taskid", taskid);

                    startActivity(intent);
                }else {
                    PptDo pptDo = pptDoList.get(i);
                    Intent intent = new Intent(getApplicationContext(), EditTaskActivity.class);
                    intent.putExtra("addppt", 1);
                    intent.putExtra("ppttitle", pptDo.getPptname());
                    intent.putExtra("pptpath", pptDo.getPptaddress());
                    intent.putExtra("courseid", getIntent().getIntExtra("courseid", 1));
                    intent.putExtra("coursename", getIntent().getStringExtra("coursename"));
                    intent.putExtra("token", token);

                    startActivity(intent);
                }

            }
        });


    }
}

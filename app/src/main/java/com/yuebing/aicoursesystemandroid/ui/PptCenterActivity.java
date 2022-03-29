package com.yuebing.aicoursesystemandroid.ui;

import android.content.Intent;


import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;



import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.model.PptDo;

import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.io.File;
import java.util.List;

public class PptCenterActivity extends AppCompatActivity {

    private String token;
    private String pptlistjson;
    private List<PptDo> pptDoList;
    private ListView lv_pptlist;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pptcenter);

        token = getIntent().getStringExtra("token");

        pptlistjson = getIntent().getStringExtra("pptlist");
        pptDoList = JsonListUtil.getObjectList(pptlistjson, PptDo.class);
        lv_pptlist = findViewById(R.id.lv_pptlist);

        PptCenterAdapter pptCenterAdapter = new PptCenterAdapter(PptCenterActivity.this, R.layout.item_ppt, pptDoList);

        lv_pptlist.setAdapter(pptCenterAdapter);

        lv_pptlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PptDo pptDo = pptDoList.get(i);
                String type = pptDo.getPptaddress().substring(pptDo.getPptaddress().lastIndexOf("."));
                String path = Constant.File_Path + pptDo.getPptaddress();
                openFileIntent(path, type);
            }
        });


    }


    private void openFileIntent(String path, String type) {

            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            Uri uri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(PptCenterActivity.this, getApplicationContext().getPackageName() + ".fileProvider", new File(path));
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//给目标文件临时授权，必需添加
            } else {
                uri = Uri.fromFile(new File(path));
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                if (type.equals(".doc")||type.equals(".docx")) {
                    intent.setDataAndType(uri, "application/msword");
                    this.startActivity(intent);
                    this.finish();
                } else if (type.equals(".xls")||type.equals(".xlsx")) {
                    intent.setDataAndType(uri, "application/vnd.ms-excel");
                    this.startActivity(intent);
                    this.finish();
                } else if (type.equals(".ppt")||type.equals(".pptx")) {
                    intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
                    this.startActivity(intent);
                    this.finish();
                }
            } catch (Exception e) {

                finish();
            }
        }

}

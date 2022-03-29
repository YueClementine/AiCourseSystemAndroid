package com.yuebing.aicoursesystemandroid.ui;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;


import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;



import com.yuebing.aicoursesystemandroid.R;
import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.model.PptDo;

import com.yuebing.aicoursesystemandroid.utils.HttpDownloader;
import com.yuebing.aicoursesystemandroid.utils.JsonListUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import lombok.SneakyThrows;

public class PptCenterActivity extends AppCompatActivity  {

    private String token;
    private String pptlistjson;
    private List<PptDo> pptDoList;
    private ListView lv_pptlist;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pptcenter);
        verifyStoragePermissions(this);

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
                String name = pptDo.getPptaddress();
                if (type.equals(".pdf")) {
                    Intent intent = new Intent(getApplicationContext(), PdfActvity.class);
                    intent.putExtra("path", path);
                    intent.putExtra("name", name);
                    startActivity(intent);


                } else {


                    new Thread(new Runnable() {
                        @SneakyThrows
                        @Override
                        public void run() {
//                            HttpDownloader httpDownloader = new HttpDownloader();
//                            httpDownloader.downloadFile(path, "MyDownLoad", name);
                            download(path, name);
                            openFileIntent(Environment.getExternalStorageDirectory() + "/MyDownLoad" + "/" + name, type);


                        }
                    }).start();

                }

            }
        });


    }



    private boolean downloadTask(String url) throws Exception {
        if (!url.startsWith("http")) {
            return false;
        }
        String name = "temp.mcaddon";
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "Download");
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.mkdirs();
            }
            File result = new File(file.getAbsolutePath() + File.separator + name);
            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
            request.setDestinationUri(Uri.fromFile(result));
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            if (downloadManager != null) {
                downloadManager.enqueue(request);
            }
            //mToast(mContext, "Starting download...");
            MediaScannerConnection.scanFile(PptCenterActivity.this, new String[]{result.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                        }
                    });
        } catch (Exception e) {
            Log.e(">>>>>", e.toString());
            //mToast(this, e.toString());
            return false;
        }
        return true;
    }
    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void download(String downloadUrl, String name) {

        try {
            URL url = new URL(downloadUrl);
            //打开连接
            URLConnection conn = url.openConnection();
            //打开输入流
            InputStream is = conn.getInputStream();
            //获得长度
            int contentLength = conn.getContentLength();
            //创建文件夹 MyDownLoad，在存储卡下
            String dirName = Environment.getExternalStorageDirectory() + "/MyDownLoad/";
            File file = new File(dirName);
            //不存在创建
            if (!file.exists()) {
                file.mkdir();
            }
            //下载后的文件名
            String fileName = dirName + name;
            File file1 = new File(fileName);
            if (file1.exists()) {
                file1.delete();
            }
            //创建字节流
            byte[] bs = new byte[1024];
            int len;
            OutputStream os = new FileOutputStream(fileName);
            //写数据
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

            os.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

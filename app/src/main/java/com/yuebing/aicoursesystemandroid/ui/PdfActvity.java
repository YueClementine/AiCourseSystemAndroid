package com.yuebing.aicoursesystemandroid.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yuebing.aicoursesystemandroid.R;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class PdfActvity extends AppCompatActivity implements DownloadFile.Listener {

    private PDFPagerAdapter adapter;
    private RemotePDFViewPager remotePDFViewPager;
    private String path;
    private String name;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        path = getIntent().getStringExtra("path");
        name = getIntent().getStringExtra("name");

        setDownloadListener();


    }


    /*设置监听*/
    protected void setDownloadListener() {
        final DownloadFile.Listener listener = this;
        remotePDFViewPager = new RemotePDFViewPager(this, path, listener);
        remotePDFViewPager.setId(R.id.pdfViewPager);

    }

    /*加载成功调用*/
    @Override
    public void onSuccess(String url, String destinationPath) {

        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(adapter);
        setContentView(remotePDFViewPager);

    }



    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }

}

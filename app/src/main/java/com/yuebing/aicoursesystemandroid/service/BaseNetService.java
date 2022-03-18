package com.yuebing.aicoursesystemandroid.service;

import android.util.Pair;

import com.yuebing.aicoursesystemandroid.common.Constant;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 网络服务基类
 */
public class BaseNetService {
    /**
     * POST
     * @param url URL
     * @param json BODY数据
     * @return 响应结果
     * @throws IOException 输入输出异常
     */
    protected static Pair<Response,String> post(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, Constant.JSON);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String data=new String();
            if(response.code()==200){
                data=response.body().string();
            }
            return new Pair<>(response,data);
        }
    }

    protected static Pair<Response,String> get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String data=new String();
            if(response.code()==200){
                data=response.body().string();
            }
            return new Pair<>(response,data);
        }
    }

    protected static Pair<Response,String> put(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, Constant.JSON);

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String data=new String();
            if(response.code()==200){
                data=response.body().string();
            }
            return new Pair<>(response,data);
        }
    }

}

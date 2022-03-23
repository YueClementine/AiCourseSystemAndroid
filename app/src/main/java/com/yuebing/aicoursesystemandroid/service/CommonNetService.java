package com.yuebing.aicoursesystemandroid.service;

import com.yuebing.aicoursesystemandroid.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class CommonNetService extends BaseNetService {

    public static Response postByToken(String url, String json, String token) throws IOException, JSONException {
        String result = postWithToken(url, json, token);

        if(!result.isEmpty()){
            JSONObject jsonObject=new JSONObject(result);

            int code = jsonObject.getInt("code");
            String msg = jsonObject.getString("msg");
            String data = jsonObject.getString("data");
            Response response = new Response(code, msg, data);
            return response;

        }
        return null;
    }
}

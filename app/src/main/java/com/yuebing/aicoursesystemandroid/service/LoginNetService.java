package com.yuebing.aicoursesystemandroid.service;


import android.util.Pair;

import com.google.gson.Gson;
import com.yuebing.aicoursesystemandroid.common.Constant;
import com.yuebing.aicoursesystemandroid.model.User;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

/**
 * 登陆网络服务
 */
public class LoginNetService extends BaseNetService {

    /**
     * 查询用户名是否已注册
     * @param username 用户名
     * @return 是否注册
     * @throws IOException 输入输出异常
     * @throws JSONException JSON解析异常
     */
    public static boolean isRegistered(String username) throws IOException, JSONException {

        if(username.isEmpty()){
            return false;
        }

        // 生成JSON
        User user=new User();
        user.setUsername(username);
        Gson gson=new Gson();
        String json=gson.toJson(user);

        // POST请求
        String result = post(Constant.IS_REGISTERED_URL,json);

        // 结果处理
        if(!result.isEmpty()){
            JSONObject jsonObject = new JSONObject(result);
            int code = jsonObject.getInt("code");
            return code != 200;
        }
        return false;
    }

    /**
     * 注册
     * @param user 用户
     * @return 是否成功注册
     * @throws IOException 输入输出异常
     * @throws JSONException JSON解析异常
     */
    public static boolean register(User user) throws IOException, JSONException {

        if(user.getUsername().isEmpty()||user.getPassword().isEmpty()){
            return false;
        }

        // 生成JSON
        Gson gson=new Gson();
        String json=gson.toJson(user);

        // POST请求
        String result= post(Constant.REGISTER_URL,json);



        if(!result.isEmpty()){
            JSONObject jsonObject=new JSONObject(result);

            int code = jsonObject.getInt("code");

            return code == 200;
        }
        return false;
    }

    /**
     * 登陆
     * @param user 用户
     * @return 是否成功登陆
     * @throws IOException 输入输出异常
     * @throws JSONException JSON解析异常
     */
    public static boolean login(User user) throws IOException, JSONException {
        if(user.getUsername()==null||user.getPassword()==null){
            return false;
        }



        // 生成JSON
        Gson gson=new Gson();
        String json=gson.toJson(user);

        // POST请求
        String result = post(Constant.LOGIN_URL, json);

//        // 结果处理
//        Response response = result.first;
//        String message = response.message();
//
//        if(response.code()==200){
//            // 去除前缀
//            String token=response.header("Authorization").substring(7);
//            user.setToken(token);
//            return true;
//        }
        /**
         * TODO：set一个token
         */
        return false;
    }
}

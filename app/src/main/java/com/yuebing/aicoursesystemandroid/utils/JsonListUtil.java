package com.yuebing.aicoursesystemandroid.utils;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

public class JsonListUtil {


    public static <T> List<T> jsonToObjectList(String jsonStr) {
        Gson gson = new Gson();
        //要转化的类型
        //Type导入的是java.lang.reflect.Type的包
        //TypeToken导入的是 com.google.gson.reflect.TypeToken的包
        Type type = new TypeToken<List<T>>() {
        }.getType();
        List<T> pointList = gson.fromJson(jsonStr, type);
        return pointList;
    }


}
package com.yuebing.aicoursesystemandroid.model;

import lombok.Data;

@Data
public class Response {
    private int code;
    private String msg;
    private String data;

    public Response(int code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}

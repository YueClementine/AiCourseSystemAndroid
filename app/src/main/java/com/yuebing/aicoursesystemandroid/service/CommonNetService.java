package com.yuebing.aicoursesystemandroid.service;

import java.io.IOException;

public class CommonNetService extends BaseNetService {

    public static String postByToken(String url, String json, String token) throws IOException {
        return postWithToken(url, json, token);
    }
}

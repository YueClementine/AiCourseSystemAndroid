package com.yuebing.aicoursesystemandroid.service;

import com.yuebing.aicoursesystemandroid.model.User;

import junit.framework.TestCase;

import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginNetServiceTest extends TestCase {
    @Test
    public void testIsRegistered() throws IOException, JSONException {
        User user = new User();
        user.setUsername("test2");
        user.setUserid(1212);
        user.setPassword("12345");
        user.setSex(1);
        user.setRole(1);
        System.out.println(LoginNetService.register(user));

    }

    public void testRegister() {
    }

    public void testLogin() throws IOException, JSONException {
        User user = new User();
        user.setUsername("test1");
        user.setPassword("123");

    }
}
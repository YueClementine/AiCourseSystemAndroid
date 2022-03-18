package com.yuebing.aicoursesystemandroid.model;


import lombok.Data;

@Data
public class User {
    private Integer id;

    private Integer userid;

    private String username;

    private String password;

    private Integer sex;

    private Integer role;

    /** 令牌 */
    private String token;
}

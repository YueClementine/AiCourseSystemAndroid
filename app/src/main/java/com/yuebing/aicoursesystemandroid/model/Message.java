package com.yuebing.aicoursesystemandroid.model;

import lombok.Data;

@Data
public class Message {

    private Integer messageid;

    private Long userid;

    private Integer groupid;

    private Integer discussid;

    private Long date;

    private String username;

    private String message;

}

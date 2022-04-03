package com.yuebing.aicoursesystemandroid.model;

import lombok.Data;

@Data
public class Discuss {
    private Integer discussid;

    private String topic;

    private Integer isterminated;

    private Long teacherid;

    private int groupnum;
}

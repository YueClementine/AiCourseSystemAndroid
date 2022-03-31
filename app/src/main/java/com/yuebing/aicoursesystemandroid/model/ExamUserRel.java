package com.yuebing.aicoursesystemandroid.model;

import lombok.Data;

@Data
public class ExamUserRel {
    private Integer id;

    private Long userid;

    private Integer examid;

    private String examname;

    private Long teacherid;

    private Integer grade;

    private String date;

    private String username;
}

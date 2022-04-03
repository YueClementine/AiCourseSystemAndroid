package com.yuebing.aicoursesystemandroid.model;

import lombok.Data;

@Data
public class Task {
    private Integer taskid;

    private String task;

    private Integer courseid;

    private String coursename;

    private String title;

    private Integer videoid;

    private String videoname;

    private String videoaddress;

    private Integer pptid;

    private String pptname;

    private String pptaddress;

    private Integer addvideo;

    private Integer addppt;


}

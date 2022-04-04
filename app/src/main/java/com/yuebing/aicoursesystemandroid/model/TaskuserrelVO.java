package com.yuebing.aicoursesystemandroid.model;

import lombok.Data;

@Data
public class TaskuserrelVO {

    private Integer taskid;

    private Long userid;
    private String title;

    private String task;


    private Integer status;

    private Integer videoid;

    private String videoname;

    private String videoaddress;

    private Integer pptid;

    private String pptname;

    private String pptaddress;

    private Integer addvideo;

    private Integer addppt;


}

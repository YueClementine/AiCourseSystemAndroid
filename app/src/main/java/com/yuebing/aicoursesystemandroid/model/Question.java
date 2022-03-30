package com.yuebing.aicoursesystemandroid.model;

import lombok.Data;

@Data
public class Question {
    private Integer questionid;

    private String questioncontent;

    private String opa;

    private String opb;

    private String opc;

    private String opd;

    private Integer correctop;

    private Integer questiontag;

    private Long userid;

    private Integer examid;
}

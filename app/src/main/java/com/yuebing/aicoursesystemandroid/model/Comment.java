package com.yuebing.aicoursesystemandroid.model;

import lombok.Data;

@Data
public class Comment {
    private Integer commentid;

    private String comment;

    private Integer noteid;

    private Long userid;

    private String username;

}

package com.sll.blog.vo;

import lombok.Data;

@Data
public class BlogQuery {

    private String title;
    private Long typeId;
    private boolean recommend;


}

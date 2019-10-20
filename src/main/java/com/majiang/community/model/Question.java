package com.majiang.community.model;

import lombok.Data;

/**
 * @ClassName Question
 * @Description TODO
 * @Author 刘志强
 * @Date 2019/9/14 1:44
 * @Version v1.0
 **/
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}

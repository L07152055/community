package com.majiang.community.dto;

import com.majiang.community.model.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName QuestionDTO
 * @Description TODO
 * @Author 刘志强
 * @Date 2019/10/20 14:25
 * @Version v1.0
 **/
@Getter
@Setter
public class QuestionDTO {
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
    private User user;
}

package com.nowcoder.community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author xindong
 * @create 2022-05-15 11:29
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Integer id;
    private Integer userId;

    //评论类型
    private Integer entityType;

    //表示该评论所对应的（帖子/评论）的id
    private Integer entityId;

    //表示在帖子的评论下评论到底是回复谁
    private Integer targetId;
    private String content;
    private Integer status;
    private Date createTime;

}

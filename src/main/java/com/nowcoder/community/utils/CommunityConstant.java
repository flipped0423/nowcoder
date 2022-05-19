package com.nowcoder.community.utils;

/**
 * @author xindong
 * @create 2022-05-11 20:46
 */
public interface CommunityConstant {
    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS = 0;

    /**
     * 重复激活
     */
    int ACTIVATION_REPEAT = 1;

    /**
     * 激活失败
     */
    int ACTIVATION_FAILURE = 2;

    /**
     * 默认状态的登录凭证的超时时间，12h
     */
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    /**
     * 记住状态的登录凭证超时时间
     */
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;

    /**
     * 实体类型：帖子的评论
     */
    int ENTITY_TYPE_POST = 1;

    /**
     * 实体类型：评论的评论
     */
    int ENTITY_TYPE_COMMENT = 2;

    /**
     * 实体类型：帖子（redis）
     */
    int ENTITY_POST = 1;

    /**
     * 实体类型：评论(redis)
     */
    int ENTITY_COMMENT = 2;

    /**
     * 实体类型：用户（redis）
     */
    int ENTITY_USER = 3;

}

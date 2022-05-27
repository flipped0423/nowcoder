package com.nowcoder.community.utils;


import org.apache.kafka.common.protocol.types.Field;

/**
 * @author xindong
 * @create 2022-05-18 9:30
 */
public class RedisKeyUtil {

    private static final String SPLIT = ":";
    private static final String PREFIX_ENTITY_LIKE = "like:entity";
    private static final String PREFIX_USER_LIKE = "like:user";
    private static final String PREFIX_FOLLOWEE = "followee";//关注目标
    private static final String PREFIX_FOLLOWER = "follower";//粉丝
    private static final String PREFIX_KAPTCHA = "kaptcha";//验证码
    private static final String PREFIX_TICKET = "ticket";//登录凭证
    private static final String PREFIX_USER = "user"; //缓存用户
    private static final String PREFIX_UV = "uv"; //网站访问量：使用IP统计
    private static final String PREFIX_DAU = "dau"; //网站日活用户：使用userId统计

    //在键上保留某个实体的信息
    //like:entity:entityType:entityId -> set(userId)（redis键值对）
    public static String getEntityLikeKey(int entityType, int entityId) {
        return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    //某个用户的赞的键
    //like:user:userId -> int
    public static String getUserLikeKey(int userId) {
        return PREFIX_USER_LIKE + SPLIT + userId;
    }

    //某个用户关注的实体，此实体是redis重定义的实体，和sql不一致
    //followee:userId:entityType -> zset(entityId,now)
    public static String getFolloweeKey(int userId, int entityType) {
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    //某个实体所拥有的粉丝
    //follower:entityType:entityId -> zset(userId,now)
    public static String getFollowerKey(int entityType, int entityId) {
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

    //登录验证码
    public static String getKaptchaKey(String owner) {//owner 一个字符串，用于用户登录前临时凭证
        return PREFIX_KAPTCHA + SPLIT + owner;
    }

    //登录凭证
    public static String getTicketKey(String ticket) {
        return PREFIX_TICKET + SPLIT + ticket;
    }

    //用户
    public static String getUserKey(int userId) {
        return PREFIX_USER + SPLIT + userId;
    }

    //单日UV
    public static String getUVKey(String date) {
        return PREFIX_UV + SPLIT + date;
    }

    //区间UV
    public static String getUVKey(String startDate, String endDate) {
        return PREFIX_UV + SPLIT + startDate + SPLIT + endDate;
    }

    //单日活跃用户
    public static String getDAUKey(String date) {
        return PREFIX_DAU + SPLIT + date;
    }

    //区间活跃用户
    public static String getDAUKey(String startDate, String endDate) {
        return PREFIX_DAU + SPLIT + startDate + SPLIT + endDate;
    }
}

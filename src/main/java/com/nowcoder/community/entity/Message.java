package com.nowcoder.community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author xindong
 * @create 2022-05-16 15:23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    //属性建议包装类，局部变量基本数据类型。因为前端传值的时候比如空串情况包装类为null.而基本数据类型为0，变成一个可能合法的值了
    private Integer id;
    //根据数值不同分为普通用户发送和系统发送
    private Integer fromId;
    private Integer toId;
    private String conversationId;
    private String content;
    //表示已读，未读，删除状态
    private int status;
    private Date createTime;

}

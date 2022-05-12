package com.nowcoder.community.dao;

import com.nowcoder.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author xindong
 * @create 2022-05-12 13:57
 */
@Mapper
@Repository
public interface LoginTicketMapper {

    /**
     *对于支持自动生成记录主键的数据库，
     * 如：MySQL，SQL Server，此时设置useGeneratedKeys参数值为true，
     * 在执行添加记录之后可以获取到数据库自动生成的主键ID，只能在insert中使用
     */
    @Insert({
            "insert into login_ticket(user_id, ticket, status, expired) ",
            "values(#{userId},#{ticket},#{status},#{expired})"
    })
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    @Select({
            "select id, user_id, ticket, status, expired ",
            "from login_ticket where ticket = #{ticket}"
    })
    LoginTicket selectByTicket(String ticket);

    @Update({
            "update login_ticket set status = #{status} where ticket = #{ticket}"
    })
    int updateStatus(String ticket, int status);

}

package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @author xindong
 * @create 2022-05-07 14:41
 */

@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void testUserMapper(){
        System.out.println(userMapper.selectById(101));
        System.out.println(userMapper.selectByName("liubei"));
        System.out.println(userMapper.selectByEmail("nowcoder101@sina.com"));

    }

    @Test
    public void testSelectPosts(){
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149, 0, 10);
        for (DiscussPost discussPost : list) {
            System.out.println(discussPost);
        }

        System.out.println(discussPostMapper.selectDiscussPostRows(149));
    }

    @Test
    public void testLoginTicket(){
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("1234");
        System.out.println(loginTicket);
        //loginTicketMapper.insertLoginTicket(new LoginTicket(null,11,"车票",1,new Date()));
        loginTicketMapper.updateStatus("车票", 0);
    }
}

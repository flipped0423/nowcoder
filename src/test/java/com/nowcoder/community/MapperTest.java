package com.nowcoder.community;

import com.nowcoder.community.dao.*;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.Message;
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

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private CommentMapper commentMapper;

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

    @Test
    public void testInsertDiscussPost(){
        DiscussPost discussPost = new DiscussPost(null,159,"暑期实习",
                "今年暑期实习好难", 0,1,new Date(), 123,10.0);
        discussPostMapper.insertDiscussPost(discussPost);

    }

//    //查询当前用户的会话列表，针对每个会话只返回（显示）一条最新的私信
//    List<Message> selectConversations(int userId, int offset, int limit);
//
//    //查询当前用户的会话数量
//    int selectConversationCount(int userId);
//
//    //查询每个会话所包含的私信列表
//    List<Message> selectLetters(String conversationId, int offset, int limit);
//
//    //查询某个会话所包含的私信数量
//    int selectLetterCount(String conversationId);
//
//    //查询未读私信的数量（包括所有未读的私信和每个会话未读的私信）
//    int selectLetterUnreadCount(int userId, String conversationId);

    @Test
    public void testSelectConversations(){
        List<Message> messages = messageMapper.selectConversations(111, 0, 20);
        for (Message message : messages) {
            System.out.println(message);
        }
        int count = messageMapper.selectConversationCount(111);
        System.out.println(count);

        List<Message> messageList = messageMapper.selectLetters("111_112", 0, 10);
        for (Message message : messageList) {
            System.out.println(message);
        }
        System.out.println(messageMapper.selectLetterCount("111_112"));
        System.out.println(messageMapper.selectLetterUnreadCount(131,null));
    }

    @Test
    public void testSelectCountByUser(){
        System.out.println(2);
        System.out.println(commentMapper.selectCountByUser(159));
    }
}

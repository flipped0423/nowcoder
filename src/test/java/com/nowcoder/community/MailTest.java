package com.nowcoder.community;

import com.nowcoder.community.utils.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author xindong
 * @create 2022-05-11 10:27
 */

@SpringBootTest
public class MailTest {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSendMail(){
        mailClient.sendMail("2125526625@qq.com","测试","context");
    }

    @Test
    public void testSendHtml(){
        Context context = new Context();
        context.setVariable("username", "Wednesday");
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);
        mailClient.sendMail("flipped0423@sina.com","Html",content);
    }
}

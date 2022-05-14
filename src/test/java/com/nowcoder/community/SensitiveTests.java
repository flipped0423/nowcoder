package com.nowcoder.community;

import com.nowcoder.community.utils.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xindong
 * @create 2022-05-14 12:32
 */

@SpringBootTest
public class SensitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter(){
        String text = "这里可以谈赌博，可以谈小姐，还可以谈革命，哈哈哈";
        String filter = sensitiveFilter.filter(text);
        System.out.println(filter);

        text = "这里可以谈*赌*博，可以谈*小*姐，还可以谈*革**命*，哈哈哈";
        String filter1 = sensitiveFilter.filter(text);
        System.out.println(filter1);
    }
}

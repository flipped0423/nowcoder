package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * @author xindong
 * @create 2022-05-06 16:45
 */

@SpringBootApplication
public class CommunityApplication {

    @PostConstruct
    public void init(){
        //解决netty启动和ES冲突的问题
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class,args);
    }

}

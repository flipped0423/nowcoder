package com.nowcoder.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xindong
 * @create 2022-05-06 19:42
 */
@Controller
public class Hello {

    @RequestMapping("/")
    public String hello(){
        return "success";
    }
}

package com.nowcoder.community.utils;

import com.nowcoder.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author xindong
 * @create 2022-05-13 9:31
 * 持有用户信息，用来代替session对象
 * 当多线程访问时，为了避免操作共享数据导致的安全问题，使用ThreadLocal
 * 为每一个线程创建数据的安全副本，底层以当前线程为键，以数据为值存储。
 * 线程存在，数据就一直存在（相当于放入一个共享域中）
 */
@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user){
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }

    //清空数组
    public void clear(){
        users.remove();
    }

}

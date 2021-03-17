package com.atguigu.junit;

import com.atguigu.bean.User;
import com.atguigu.service.userService;
import com.atguigu.service.serviceimpl.userServiceImpl;
import org.junit.Test;

/**
 * @author Mr.yang
 * @create 2020-10-16 19:49
 */
public class userServiceTest {
    @Test
    public void registUser() {
        userService userService = new userServiceImpl();
        System.out.println(userService.registUser(new User(null,"Mr.Guo","123456","liu@qq.com")));
    }

    @Test
    public void login() {
        userService userService = new userServiceImpl();
        System.out.println(userService.login(new User(null, "Mr.yang68", "123456", null)));
    }

    @Test
    public void existsUsername() {
        userService userService = new userServiceImpl();
        System.out.println(userService.existsUsername("Mr.yang"));
    }
}

package com.atguigu.service;

import com.atguigu.bean.User;

/**
 * @author Mr.yang
 * @create 2020-10-16 19:34
 */
public interface userService {

    /**
     * 注册用户
     * @param user 用户对象
     */
    public int registUser(User user);

    /**
     * 用户登录
     * @param user 用户对象
     * @return 如果返回null，则说明用户名和密码错误，反之亦然。
     */
    public User login(User user);

    /**
     * 检查用户名是否已注册
     * @param username 用户名
     * @return 返回true表示用户已注册，返回false表示该用户名可注册
     */
    public boolean existsUsername(String username);
}

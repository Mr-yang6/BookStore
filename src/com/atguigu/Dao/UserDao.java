package com.atguigu.Dao;

import com.atguigu.bean.User;

/**
 * @author Mr.yang
 * @create 2020-10-15 23:15
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 如果返回null，说明没有这个用户，反之亦然。
     */
    public User queryUserByusername(String username);

    /**
     * 保存用户信息
     * @param user 用户信息
     * @return 如果返回-1，则说明用户信息保存失败，反之亦然。
     */
    public int saveUser(User user);

    /**
     * 根据用户名和密码查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return 如果返回null，说明用户名或密码错误，反之亦然。
     */
    public User queryUserByusernameAndpassword(String username, String password);

}

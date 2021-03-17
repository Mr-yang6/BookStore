package com.atguigu.junit;

import com.atguigu.Dao.UserDao;
import com.atguigu.Dao.Daoimpl.UserDaoImpl;
import com.atguigu.bean.User;
import org.junit.Test;

/**
 * @author Mr.yang
 * @create 2020-10-16 19:21
 */
public class UserDaoTest {
    @Test
    public void queryUserByusername() {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.queryUserByusername("Mr.yang99");
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        UserDao userDao = new UserDaoImpl();
        int i = userDao.saveUser(new User(null, "Mr.yang99", "123456", "yang@qq.com"));
        System.out.println("共" + i + "行受到影响");
    }

    @Test
    public void queryUserByusernameAndpassword() {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.queryUserByusernameAndpassword("admin", "admin");
        System.out.println(user);
    }
}

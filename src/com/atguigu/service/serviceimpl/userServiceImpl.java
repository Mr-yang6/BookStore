package com.atguigu.service.serviceimpl;

import com.atguigu.Dao.UserDao;
import com.atguigu.bean.User;
import com.atguigu.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mr.yang
 * @create 2020-10-16 19:42
 */
@Service
@Transactional
public class userServiceImpl implements userService {
    @Autowired
    UserDao userDao;

    @Override
    public int registUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User login(User user) { return userDao.queryUserByusernameAndpassword(user.getUsername(),user.getPassword()); }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByusername(username) == null){
            return false;
        } else {
            return true;
        }
    }
}

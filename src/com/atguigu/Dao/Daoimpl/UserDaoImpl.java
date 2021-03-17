package com.atguigu.Dao.Daoimpl;

import com.atguigu.Dao.BaseDao;
import com.atguigu.Dao.UserDao;
import com.atguigu.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @author Mr.yang
 * @create 2020-10-15 23:24
 */
@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public User queryUserByusername(String username) {
        String sql = "select id, username, password, email from t_user where username = ?";
        User user = (User) getInstance(User.class, sql, username);
        return user;
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username, password, email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User queryUserByusernameAndpassword(String username, String password) {
        String sql = "select id, username, password, email from t_user where username = ? and password = ?";
        User user = (User) getInstance(User.class,sql,username,password);
        return user;
    }
}

package com.atguigu.Dao;

import com.atguigu.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-10-15 21:41
 */
@Repository
public class BaseDao<T> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 通用的增删改操作(只能执行一条SQL语句)
     *
     * @param sql
     * @param args
     */
    public int update(String sql, Object... args) {
        int update = jdbcTemplate.update(sql, args);
        return update;
    }


    /**
     * 通用的查询操作，用于返回数据表（不同表）中的一条记录
     *
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     * @throws SQLException
     */
    public <T> T getInstance(Class<T> clazz, String sql, Object... args) {
        T object = null;
        try {
            object = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<T>(clazz), args);
        } catch (Exception e) {
        }
        return object;
    }

    /**
     * 通用的查询操作，用于返回数据表(不同表)中的多条记录构成的集合
     *
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz), args);
    }

    /**
     * 获取一条数据（例如：count()）
     *
     * @param sql
     * @param args
     * @return
     */
    public Object getValue(String sql, Object... args) {
        Object object = jdbcTemplate.queryForObject(sql, Object.class, args);
        return object;
    }

    /**
     * 删除数据后，再添加数据时，让id连续自增的处理方式
     */
    public void SetId() {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql1 = "alter table t_book drop id";
            String sql2 = "alter table t_book add id int not null primary key auto_increment first";
            pre = conn.prepareStatement(sql1);
            pre.execute();
            pre = conn.prepareStatement(sql2);
            pre.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

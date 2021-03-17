package com.atguigu.Dao.Daoimpl;

import com.atguigu.Dao.BaseDao;
import com.atguigu.Dao.OrderDao;
import com.atguigu.bean.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-16 10:22
 */
@Repository
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`)values(?,?,?,?,?);";
        return update(sql, order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select `order_id` `orderId`,`create_time` `createTime`,`price`,`status`,`user_id` `userId` from t_order;";
        return getForList(Order.class, sql);
    }

    @Override
    public void changeOrderStatus(String orderId, Integer status) {
        String sql = "update t_order set `status` = ? where order_id = ?;";
        update(sql,status,orderId);
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        String sql = "select `order_id` `orderId`,`create_time` `createTime`,`price`,`status`,`user_id` `userId` from t_order where `user_id` = ?;";
        return getForList(Order.class, sql,userId);
    }
}

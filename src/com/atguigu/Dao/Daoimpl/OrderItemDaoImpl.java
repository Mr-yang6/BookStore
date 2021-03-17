package com.atguigu.Dao.Daoimpl;

import com.atguigu.Dao.BaseDao;
import com.atguigu.Dao.OrderItemDao;
import com.atguigu.bean.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-16 10:23
 */
@Repository
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`id`,`name`,`count`,`price`,`total_price`,`order_id`)values(?,?,?,?,?,?);";
        return update(sql,orderItem.getId(),orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "select `id`,`name`,`count`,`price`,`total_price` `totalPrice`,`order_id` `orderId` from t_order_item where `order_id`=?;";
        return getForList(OrderItem.class, sql,orderId);
    }
}

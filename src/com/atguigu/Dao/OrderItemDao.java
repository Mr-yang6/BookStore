package com.atguigu.Dao;

import com.atguigu.bean.OrderItem;

import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-16 10:15
 */
public interface OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     */
    public int saveOrderItem(OrderItem orderItem);

    /**
     * 根据订单号查询订单明细
     * @param orderId
     * @return
     */
    public List<OrderItem> queryOrderItemByOrderId(String orderId);
}

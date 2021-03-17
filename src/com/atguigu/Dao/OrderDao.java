package com.atguigu.Dao;

import com.atguigu.bean.Order;

import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-16 10:14
 */
public interface OrderDao {
    /**
     * 保存订单
     * @param order
     */
    public int saveOrder(Order order);

    /**
     * 查询全部订单
     * @return
     */
    public List<Order> queryOrders();

    /**
     * 修改订单状态
     * @param orderId
     * @param status
     */
    public void changeOrderStatus(String orderId,Integer status);

    /**
     * 根据用户编号查询订单信息
     * @param userId
     * @return
     */
    public List<Order> queryOrdersByUserId(Integer userId);
}

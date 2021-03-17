package com.atguigu.service;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Order;
import com.atguigu.bean.OrderItem;

import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-16 11:22
 */
public interface OrderService {
    /**
     * 生成订单
     * @param cart
     * @param userId
     */
    public String createOrder(Cart cart,Integer userId);

    /**
     * 查询全部订单
     */
    public List<Order> showAllOrders();

    /**
     * 设置订单发货
     */
    public  void sendOrder(String orderId,Integer status);

    /**
     * 根据订单编号，查看订单详情
     * @param orderId
     */
    public List<OrderItem> showOrderDetail(String orderId);

    /**
     * 查看我的订单
     * @param userId
     */
    public List<Order> showMyOrders(Integer userId);

    /**
     * 签收订单/确认收货
     * @param orderId
     */
    public void receiverOrder(String orderId);
}

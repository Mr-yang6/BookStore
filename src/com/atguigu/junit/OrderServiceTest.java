package com.atguigu.junit;

import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.bean.Order;
import com.atguigu.bean.OrderItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.serviceimpl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author Mr.yang
 * @create 2020-11-16 14:42
 */
public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();
    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        System.out.println( "订单号是：" + orderService.createOrder(cart, 1) );
    }

    @Test
    public void showAllOrders() {
        for (Order order : orderService.showAllOrders()) {
            System.out.println(order);
        }
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("1234567891", 1);
        for (Order order : orderService.showAllOrders()) {
            System.out.println(order);
        }
    }

    @Test
    public void showOrderDetail() {
        for (OrderItem orderItem : orderService.showOrderDetail("1234567890")) {
            System.out.println(orderItem);
        }
    }

    @Test
    public void showMyOrders() {
        for (Order order : orderService.showMyOrders(1)) {
            System.out.println(order);
        }
    }

    @Test
    public void receiverOrder() {
        orderService.receiverOrder("1234567890");
        for (Order order : orderService.showAllOrders()) {
            System.out.println(order);
        }
    }
}

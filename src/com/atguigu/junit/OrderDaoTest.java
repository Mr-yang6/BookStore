package com.atguigu.junit;

import com.atguigu.Dao.Daoimpl.OrderDaoImpl;
import com.atguigu.Dao.OrderDao;
import com.atguigu.bean.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-16 10:48
 */

public class OrderDaoTest {
    private OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        Order order = new Order("1234567890", new Date(), new BigDecimal(50), 0, 1);
        Order order1 = new Order("1234567891", new Date(), new BigDecimal(100), 0, 1);
        orderDao.saveOrder(order);
        orderDao.saveOrder(order1);
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus("1234567890", 1);
        List<Order> orders = orderDao.queryOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void queryOrdersByUserId() {
        List<Order> orders = orderDao.queryOrdersByUserId(1);
        System.out.println(orders);
    }
}

package com.atguigu.junit;

import com.atguigu.Dao.Daoimpl.OrderDaoImpl;
import com.atguigu.Dao.Daoimpl.OrderItemDaoImpl;
import com.atguigu.Dao.OrderDao;
import com.atguigu.Dao.OrderItemDao;
import com.atguigu.bean.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-16 10:48
 */
public class OrderItemDaoTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        OrderItem orderItem = new OrderItem(1, "spring", 1, new BigDecimal(50), new BigDecimal(50),"1234567890");
        OrderItem orderItem1 = new OrderItem(2, "springBoot", 1, new BigDecimal(50), new BigDecimal(50),"1234567890");
        OrderItem orderItem2 = new OrderItem(3, "java", 1, new BigDecimal(50), new BigDecimal(50),"1234567891");
        OrderItem orderItem3 = new OrderItem(4, "javaWeb", 1, new BigDecimal(50), new BigDecimal(50),"1234567891");
        orderItemDao.saveOrderItem(orderItem);
        orderItemDao.saveOrderItem(orderItem1);
        orderItemDao.saveOrderItem(orderItem2);
        orderItemDao.saveOrderItem(orderItem3);
    }

    @Test
    public void queryOrderItemByOrderId() {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId("1234567890");
        System.out.println(orderItems);
    }
}

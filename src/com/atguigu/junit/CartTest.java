package com.atguigu.junit;

import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author Mr.yang
 * @create 2020-11-15 19:27
 */
public class CartTest {
    private Cart cart = new Cart();
    @Test
    public void addItem() {
        //    public CartItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice)
        cart.addItem(new CartItem(1,"java从学习到精通",10,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"java从入门到放弃",10,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(3,"java从入门到入土",10,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(4, "java天下第一", 10, new BigDecimal(100),new BigDecimal(100)));

        System.out.println(cart.toString());
        System.out.println(cart.getItems().get(1));
    }

    @Test
    public void deleteItem() {
        cart.addItem(new CartItem(1,"java从学习到精通",10,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"java从入门到放弃",10,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(3,"java从入门到入土",10,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(4, "java天下第一", 10, new BigDecimal(100),new BigDecimal(100)));
        cart.deleteItem(1);
        System.out.println(cart.toString());
    }

    @Test
    public void updateCount() {
    }

    @Test
    public void clear() {
    }
}

package com.atguigu.bean;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 * @author Mr.yang
 * @create 2020-11-15 17:23
 */
public class Cart {
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer, CartItem>();//key是商品编号，value是商品信息

    /**
     * 购物车中添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        //先查看购物车中是否已经添加过此商品
        //如果已添加，则数量累加，总金额更新，如果没有添加，直接放到集合中即可
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            //之前没有添加过此商品
            items.put(cartItem.getId(), cartItem);
        } else {
            //已经添加过的情况:
            item.setCount(item.getCount() + 1);    //数量累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//总金额更新
        }
    }

    /**
     * 购物车中删除商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 修改购物车中某个商品的数量
     * @param id
     * @param Count
     */
    public void updateCount(Integer id,Integer Count){
        //先查看购物车中是否有此商品。如果有，修改商品数量，更新总金额
        CartItem cartItem = items.get(id);
        if (cartItem != null){
            cartItem.setCount(Count);//修改商品数量
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));//修改商品总金额
        }
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}

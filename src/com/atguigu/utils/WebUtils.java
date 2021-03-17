package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

/**
 * @author Mr.yang
 * @create 2020-11-09 21:27
 */
public class WebUtils {

    /**
     * 从IOC容器中获取组件的方法
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>T getBean(Class<T> clazz){
        //获取IOC容器
        WebApplicationContext ioc = ContextLoader.getCurrentWebApplicationContext();
        return ioc.getBean(clazz);
    }

    /**
     * 把Map中的值注入到对应的JavaBean属性中
     * @param value
     * @param bean
     */
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换成为int类型的数据
     * @param strInt
     * @return
     */
    public static int parseInt(String strInt,int defaultValue){
        if (strInt != null)
            return Integer.parseInt(strInt);
        return defaultValue;
    }
}

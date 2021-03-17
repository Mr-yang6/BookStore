package com.atguigu.web;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Order;
import com.atguigu.bean.OrderItem;
import com.atguigu.bean.User;
import com.atguigu.service.OrderService;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Mr.yang
 * @create 2020-11-16 14:56
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService = WebUtils.getBean(OrderService.class);
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车中的信息
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取用户的id信息
        User user = (User) req.getSession().getAttribute("user");
        //如果用户还未登录，必须要先登录
        if (user == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        //用户已登录
        Integer userId = user.getId();

        //生成订单
        String orderId = orderService.createOrder(cart, userId);
        //保存订单号
        req.getSession().setAttribute("orderId", orderId);
        //请求重定向
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * 通过客户编号查看客户订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数(用户编号)
        int userId = WebUtils.parseInt(req.getParameter("userId"), 0);
        //通过用户编号查找订单
        List<Order> orders = orderService.showMyOrders(userId);
        req.setAttribute("orders", orders);
        //请求转发到pages/order/order.jsp页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    /**
     * 查看订单中的商品信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数（订单编号）
        String orderId = req.getParameter("orderId");
        int status = WebUtils.parseInt(req.getParameter("status"), 0);
        int userId = WebUtils.parseInt(req.getParameter("userId"), 0);
        //2.通过订单编号，查询订单信息
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        //3.将订单信息保存到request域中
        req.setAttribute("orderItems", orderItems);
        //4.请求转发到/pages/order/orderItem.jsp页面
        req.getRequestDispatcher("/pages/order/orderItem.jsp?orderId=" + orderId + "&status=" + status + "&userId=" + userId).forward(req, resp);
    }

    /**
     * 给管理员查看所有订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查看所有订单
        List<Order> orders = orderService.showAllOrders();
        //将全部订单保存到request域中
        req.setAttribute("orders", orders);
        //请求转发
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    /**
     * 管理员点击发货（卖家发货）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数(订单编号和订单的状态码)
        String orderId = req.getParameter("orderId");
        Integer status = WebUtils.parseInt(req.getParameter("status"), 0);
        //设置订单状态
        orderService.sendOrder(orderId, status);
        //查看所有订单
        List<Order> orders = orderService.showAllOrders();
        //将全部订单保存到request域中
        req.setAttribute("orders", orders);
        //请求转发到/pages/manager/order_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    /**
     * 买家确认收货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数(订单编号和订单的状态码和用户编号)
        String orderId = req.getParameter("orderId");
        Integer status = WebUtils.parseInt(req.getParameter("status"), 1);
        int userId = WebUtils.parseInt(req.getParameter("userId"), 0);
        //设置订单状态
        orderService.sendOrder(orderId, status);
        //通过用户编号查找订单
        List<Order> orders = orderService.showMyOrders(userId);
        req.setAttribute("orders", orders);
        //请求转发
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }
}

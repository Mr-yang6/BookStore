<%@ page import="java.util.Date" %>
<%@ page import="com.atguigu.bean.Order" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%--包含相关的js和jQuery路径--%>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            //给【发货】绑定单击事件
            $("#sendOrder").click(function () {
                return confirm("你确定要发货吗?")
            });

        })
    </script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">订单管理系统</span>
    <%@include file="/pages/common/manager_menu.jsp" %>
</div>
<%
    Date nowDate = new Date();
    Date createTime = null;
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    for (Order order : orders) {
        createTime = order.getCreateTime();
        if ((nowDate.getTime() - createTime.getTime()) / (24 * 60 * 60 * 1000) > 1)
            order.setStatus(3);
    }
    request.setAttribute("orders", orders);
%>
<div id="main">
    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>详情</td>
            <td>状态</td>
        </tr>
        <c:forEach items="${requestScope.orders}" var="order" >
            <tr>
                <td>${order.createTime}</td>
                <td>${order.price}</td>
                <td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
                    <c:if test="${order.status == 0}">
                        <td><a id="sendOrder" href="orderServlet?action=sendOrder&status=1&orderId=${order.orderId}">点击发货</a></td>
                    </c:if>
                    <c:if test="${order.status == 1}">
                        <td>已发货</td>
                    </c:if>
                    <c:if test="${order.status == 2}">
                        <td>已收货</td>
                    </c:if>
                    <c:if test="${order.status == 3}">
                        <td>等待收货</td>
                    </c:if>
            </tr>
        </c:forEach>
    </table>
</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>
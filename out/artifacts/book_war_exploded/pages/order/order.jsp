<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%--包含相关的js和jQuery路径--%>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">我的订单</span>

    <%--静态包含，登录成功之后的菜单--%>
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
        </tr>
        <c:forEach items="${requestScope.orders}" var="order">
            <tr>
                <td>${order.createTime}</td>
                <td>${order.price}</td>
                <c:choose>
                    <c:when test="${order.status == 0}">
                        <td>未发货</td>
                    </c:when>
                    <c:when test="${order.status == 1}">
                        <td>已发货</td>
                    </c:when>
                    <c:when test="${order.status == 2}">
                        <td>已收货</td>
                    </c:when>
                </c:choose>
                <td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}&status=${order.status}&userId=${order.userId}">查看详情</a></td>
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
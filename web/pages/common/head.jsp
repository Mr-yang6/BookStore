<%--
  Created by IntelliJ IDEA.
  User: Mr·yang
  Date: 2020/10/25
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";

    pageContext.setAttribute("basePath",basePath);
%>
<!--写base标签永远固定相对路径跳转的结果-->
<base href="<%=basePath%>>">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js">
</script>

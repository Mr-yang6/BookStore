<%--
  Created by IntelliJ IDEA.
  User: Mr·yang
  Date: 2020/11/14
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--分页条的开始--%>
<div id="page_nav">

    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%--页码输出的开始--%>
    <c:choose>
        <%--情况1：如果总页码小于等于5的情况，页码的范围是：1 ~ 总页码 --%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${i == requestScope.page.pageNo}">
                    【${i}】
                </c:if>
                <c:if test="${i != requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>
        </c:when>
        <%--情况2：如果总页码大于5的情况--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <%--小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1~5--%>
            <c:if test="${requestScope.page.pageNo <= 3}">
                <c:forEach begin="1" end="5" var="i">
                    <c:if test="${i == requestScope.page.pageNo}">
                        【${i}】
                    </c:if>
                    <c:if test="${i != requestScope.page.pageNo}">
                        <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                    </c:if>
                </c:forEach>
            </c:if>
            <%--小情况2：当前页码为最后3个：8，9，10，页码范围是：总页码减4 ~ 总页码 --%>
            <c:if test="${requestScope.page.pageNo > requestScope.page.pageTotal - 3}">
                <c:forEach begin="${requestScope.page.pageTotal - 4}" end="${requestScope.page.pageTotal}" var="i">
                    <c:if test="${i == requestScope.page.pageNo}">
                        【${i}】
                    </c:if>
                    <c:if test="${i != requestScope.page.pageNo}">
                        <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                    </c:if>
                </c:forEach>
            </c:if>
            <%--小情况3：当前页码为：4，5，6，7。页码范围是：当前页码减2 ~ 当前页码加2--%>
            <c:if test="${requestScope.page.pageNo > 3 && requestScope.page.pageNo < requestScope.page.pageTotal - 2}">
                <c:forEach begin="${requestScope.page.pageNo - 2}" end="${requestScope.page.pageNo + 2}" var="i">
                    <c:if test="${i == requestScope.page.pageNo}">
                        【${i}】
                    </c:if>
                    <c:if test="${i != requestScope.page.pageNo}">
                        <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                    </c:if>
                </c:forEach>
            </c:if>
        </c:when>
    </c:choose>


    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${ requestScope.page.pageTotal }页，${ requestScope.page.pageTotalCount }条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">
        $(function () {
            //跳到指定的页码
            $("#searchPageBtn").click(function () {
                //获取输入框中的内容
                var pageNo = $("#pn_input").val();

                //javascript语言中提供了一个location地址栏的对象
                //它有一个属性叫href，它可以获取浏览器地址栏中的地址
                //href属性可读，可写

                //数据边界的有效性
                if (${requestScope.page.pageNo > 1 && requestScope.page.pageNo <= requestScope.page.pageTotal})
                    location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            })
        })
    </script>

</div>
<%--分页条的结束--%>

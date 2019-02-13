<%--
  Created by IntelliJ IDEA.
  User: jyx
  Date: 2019/1/24
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- 用来实现国际化的jstl标签，和以前上课讲的那个不一样 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${requestScope.errors}" var="error">
    ${error.getDefaultMessage()}<br>
</c:forEach>
<a>----------------------------</a><br><br>
<fmt:message key="hello"></fmt:message>
<fmt:message key="welcome"></fmt:message>

<h1>welcome to spring mvc</h1>
request==============<br>
id：${requestScope.student1.id}<br>
姓名：${requestScope.student1.name}<br>
id：${requestScope.student2.id}<br>
姓名：${requestScope.student2.name}<br>
id：${requestScope.student3.id}<br>
姓名：${requestScope.student3.name}<br>
id：${requestScope.student4.id}<br>
姓名：${requestScope.student4.name}<br>
<br><br>
Session==============<br>
id：${sessionScope.student1.id}<br>
姓名：${sessionScope.student1.name}<br>
id：${sessionScope.student2.id}<br>
姓名：${sessionScope.student2.name}<br>
id：${sessionScope.student3.id}<br>
姓名：${sessionScope.student3.name}<br>
id：${sessionScope.student4.id}<br>
姓名：${sessionScope.student4.name}<br>


</body>
</html>

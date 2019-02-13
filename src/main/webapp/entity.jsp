<%--
  Created by IntelliJ IDEA.
  User: jyx
  Date: 2019/1/26
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/springmvc/testServletAPI" method="post">
    id：<input name="id"><br><br>
    姓名：<input name="name"><br><br>
    家庭住址：<input name="address.homeAddress"><br><br>
    学校<input name="address.schoolAddress"><br><br>
    <input type="submit" value="提交">
</form>

</body>
</html>

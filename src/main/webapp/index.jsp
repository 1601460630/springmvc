<%--
  Created by IntelliJ IDEA.
  User: jyx
  Date: 2019/1/24
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#testJson").click(function () {
                // 通过ajax先SpringMVC请求Json
                $.post(
                    "springmvc/testJson",//服务器地址
                    function (result) {//后端传来的Json数组会自动存放到result中，原理不知道，应该涉及到了ajax
                        for (var i = 0; i < result.length; i++) {
                            alert("id：" + result[i].id + "，姓名：" + result[i].name + "，年龄：" + result[i].age);//输出测试，中文可行
                        }
                    }
                );
            });
        });
    </script>
</head>
<body>

<input id="testJson" type="button" value="testJson"><br>

<a>-------------------------------------------------------------</a><br><br>

<form action="/springmvc/welcome6">
    name:<input name="name"><br><br>
    age:<input name="age"><br><br>
    <input type="submit"><br><br>
    <a href="/springmvc/welcome1">1.first Springmvc welcome</a><br>
    <a href="/springmvc/welcome2">2.first Springmvc welcome</a><br>
    <a href="/springmvc/welcome3/abc">3.first Springmvc welcome</a><br>
    <a href="/springmvc/welcome4/lll">4.first Springmvc welcome</a><br>
    <a href="/springmvc/welcome5/1/23/abc">5.first Springmvc welcome</a><br>
    <a href="/springmvc/welcome7/lll">7.first Springmvc welcome</a><br>
</form>

<a>-------------------------------------------------------------</a><br><br>

<%--增：post--%>
<form action="/springmvc/testPost/1234" method="post">
    <input type="submit" value="增">
</form>
<%--删：DELETE。 没有实现此功能，据说在tomcat8.0之后不支持此功能--%>
<form action="/springmvc/testDelete/1234" method="post">
    <input type="hidden" name="_method" value="delete">
    <input type="submit" value="删">
</form>
<%--改：PUT。 没有实现此功能，据说在tomcat8.0之后不支持此功能--%>
<form action="/springmvc/testPut/1234" method="post">
    <input type="hidden" name="_method" value="put">
    <input type="submit" value="改">
</form>
<%--查：get--%>
<form action="/springmvc/testGet/1234" method="get">
    <input type="submit" value="查">
</form>

<a>-------------------------------------------------------------</a><br><br>

<form action="/springmvc/testParam">
    <input name="test">
    <input type="submit" value="提交">
</form>

<a>-------------------------------------------------------------</a><br><br>

<a href="/springmvc/testRequestHeader">获取请求头</a><br>
<a href="/springmvc/testCookieValue">获取Cookie</a><br>
<a href="/springmvc/testModelAndView">ModelAndView</a><br>
<a href="/springmvc/testModelMap">测试ModelMap</a><br>
<a href="/springmvc/testMap">测试Map</a><br>
<a href="/springmvc/testModel">测试Model</a><br>

<a>-------------------------------------------------------------</a><br><br>

<form action="/springmvc/testModelAttribute" method="post">
    id：<input name="id" type="hidden"><br><br>
    姓名：<input name="name"><br><br>
    <input type="submit" value="提交">
</form>

<a>-------------------------------------------------------------</a><br><br>

<a href="/springmvc/testI18n">测试国际化</a><br>
<a href="/springmvc/testViewController">ViewController</a><br>

<a>-------------------------------------------------------------</a><br><br>

<a href="/springmvc/testForward">测试forward</a><br>
<a href="/springmvc/testRedirect">测试redirect</a><br>
<br><br>

<a>-------------------------------------------------------------</a><br><br>

<form action="/springmvc/testConverter" method="post">
    学生信息：<input name="studentInfo"><br><br>
    <input type="submit" value="提交">
</form>

<a>-------------------------------------------------------------</a><br><br>

<form action="/springmvc/testDateTimeFormat" method="get">
    id：<input name="id"><br><br>
    姓名：<input name="name"><br><br>
    年龄：<input name="age"><br><br>
    生日：<input name="birthday"><br><br>
    邮箱：<input name="email"><br><br>
    <input type="submit" value="提交">
</form>

<a>-------------------------------------------------------------</a><br><br>

<form action="/springmvc/testUpload" method="post" enctype="multipart/form-data">

    <input type="file" name="file"><br><br>
    描述：<input name="desc"><br><br>

    <input type="submit" value="上传">
</form>
</body>
</html>

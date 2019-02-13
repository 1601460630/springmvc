<%--
  Created by IntelliJ IDEA.
  User: jyx
  Date: 2019/2/1
  Time: 14:56
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

</body>
</html>

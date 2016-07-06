<%--
  Created by IntelliJ IDEA.
  User: tuyu
  Date: 2016/6/17
  Time: 16:54
  To change this template use File | Settings | File Templates.
  2、在/WEB-INF/pages/目录下编写用户注册的jsp页面register.jsp
  凡是位于WEB-INF目录下的jsp页面是无法直接通过URL地址直接访问的，
　　　　在开发中如果项目中有一些敏感web资源不想被外界直接访问，那么可以考虑将这些敏感的web资源放到WEB-INF目录下，
这样就可以禁止外界直接通过URL来访问了。

　register.jsp中的<form action="${pageContext.request.contextPath}/servlet/RegisterServlet" method="post">指明
表单提交后，交给RegisterServlet进行处理
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>用户注册</title>
</head>

<body style="text-align: center;">
<form action="${pageContext.request.contextPath}/serivce/UserServlet" method="post">
    <table width="60%" border="1">
        <tr>
            <td>用户名</td>
            <td>
                <%--使用EL表达式${}提取存储在request对象中的formbean对象中封装的表单数据(formbean.userName)以及错误提示消息(formbean.errors.userName)--%>
                <input type="text" name="userName" value="">
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td>
                <input type="password" name="userPwd" value="">
            </td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>
                <input type="text" name="email" >
            </td>
        </tr>
        <tr>
            <td>
                <input type="reset" value="清空">
            </td>
            <td>
                <input type="submit" value="注册">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
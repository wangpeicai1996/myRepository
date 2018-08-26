<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/20
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<h3>这是自定义的登录页面</h3>
<table align="center">
    <form name="loginForm" action="/springsecurity/login" method="post">
        用户名：<input type="text" name="username" /><br/>
        密码：<input type="password" name="password"/><br/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="登录">&nbsp;&nbsp;
        <input type="reset" value="取消">
        <span>${msg}</span>
    </form>
</table>
</body>
</html>

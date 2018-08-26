<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/19
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>管理页面</title>
</head>
<body>
<h3><security:authentication property="principal.username"/>：欢迎你</h3>
<a href="/springsecurity/logout">退出</a>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function submit(){
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		alert(username+"  "+password);
		if(username != null && password != null){
			ajax(username,password);	
		}
	}
	
	function ajax(username,password){
		var username = username;
		var password = password;
		//1.创建XMLHttpRequest对象
		var req;
		if(window.ActiveXObject){
			req = new ActiveXObject("Microsoft.XMLHTTP");//IE浏览器
		}else if(window.XMLHttpRequest){
			req = new XMLHttpRequest();//非IE浏览器
		}
		//2.发送请求
		var url = "/spring/login";
		req.open("POST",url,true);
		//如果请求方式是GET：req.send(null);
		//POST
		//设置请求头
		req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		//设置请求回调函数
		req.onreadystatechange = function(){
			if(req.readyState == 4 && req.status == 200){//请求成功
				alert("请求成功");
			}
		}
		//封装要传输的数据,以a=a1&b=b1格式传递
		var data = "username="+username+"&"+"password="+password; 
		req.send(data);	
	}
	
</script>
<body>
	<table>
		<thead>
			<tb>用户登录</tb></br>
		</thead>
		<tbody>
			<tb>
			<tr>用户名</tr>
			<tr><input type="text" id="username" label="请输入用户名" /></tr>				
			</tb></br>
			<tb>
			<tr>密码</tr>
			<tr><input type="password" id="password" label="请输入密码" /></tr>				
			</tb></br>
			&nbsp;&nbsp;<tb><input type="button" id="login" onclick="submit();" value="登录" /></tb>&nbsp;&nbsp;
			<tb><input type="reset" id="cancel" /></tb>
		</tbody>
	</table>
</body>
</html>
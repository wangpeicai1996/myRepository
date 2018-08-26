<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

			<!--
            	时间：2015-12-30
            	描述：菜单栏
            -->
			<div class="container-fluid">
				<div class="col-md-4">
					<!--  <img src="${pageContext.request.contextPath}/img/logo2.png" />-->
				</div>
				<div class="col-md-5">
					<img src="${pageContext.request.contextPath}/img/header.png" />
				</div>
				<div class="col-md-3" style="padding-top:20px">
					<ol class="list-inline">
						
						<c:if test="${empty user}">
							<li><a href="${pageContext.request.contextPath}/user?method=loginUI">登录</a></li>
							<li><a href="${pageContext.request.contextPath}/user?method=registerUI">注册</a></li>
						</c:if>
						<c:if test="${not empty user}">
							${user.name}:你好！
							<li><a href="${pageContext.request.contextPath}/collection?method=findMyCollectionsByPage&pageNumber=1">收藏</a></li>
							<li><a href="${pageContext.request.contextPath}/order?method=findMyOrdersByPage&pageNumber=1">订单</a></li>
							
							<li><a href="${pageContext.request.contextPath}/user?method=logout">退出</a></li>
						</c:if>
						<li><a href="${pageContext.request.contextPath}/cart?method=cartUI">购物车</a></li>
					</ol>
				</div>
			</div>
			<!--
            	时间：2015-12-30
            	描述：导航条
            -->
			<div class="container-fluid">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="${pageContext.request.contextPath}">首页</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav" id="c_ul">
							<!-- 此处插入ajax请求响应的数据 -->
							</ul>
							<form id="search" method="post" action="${pageContext.request.contextPath}/product?method=findByPage&pageNumber=1" class="navbar-form navbar-right" role="search">
								<div class="form-group">
									<input type="text" name="keyword"class="form-control" placeholder="Search">
								</div>
								<button type="submit" class="btn btn-default">搜索</button>
							</form>

						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
			</div>
			<script type="text/javascript">
			$(function(){
				//发送ajax请求，查询分类信息
				$.post("${pageContext.request.contextPath}/category",{"method":"findAll"},function(result){
					//alert(result);
					$("#c_ul").html("");//清空ul
					//遍历json数据，获取数据包装成li标签插入到页面ul内部
					$(result).each(function(i,value){
						var li="<li><a href='${pageContext.request.contextPath}/product?method=findByPage&pageNumber=1&cid="+value.cid+"'>"+value.cname+"</a></li>";
						$("#c_ul").append(li);
					})
				
				},"json");
					
				}) 
</script>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>收藏商品</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
			
			.container .row div {
				/* position:relative;
	 float:left; */
			}
			
			font {
				color: #3164af;
				font-size: 18px;
				font-weight: normal;
				padding: 0 10px;
			}
		</style>
	</head>

	<body>
	<%@include file="/jsp/head.jsp" %>
		<div class="container">
			<div class="row">
				<c:if test="${empty user }">
					<h3>请先登录！</h3>
				</c:if>
				<c:if test="${not empty user}">
				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong style="font-size:16px;margin:5px 0;">用户收藏详情</strong>
					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>小计</th>
								<th>操作</th>
							</tr>
							
							<c:forEach items="${product}" var="product">
								<tr class="active">
									<td width="60" width="40%">
										<input type="hidden" name="id" value="22">
										<img src="${pageContext.request.contextPath}/${product.pimage}" width="70" height="60">
									</td>
									<td width="30%">
										<a target="_blank" href="${pageContext.request.contextPath}/product?method=getById&pid=${product.pid}">${product.pname}</a>
									</td>
									<td width="20%">
										￥${product.shop_price}
									</td>
									<td width="15%">
										<span class="subtotal">￥${product.shop_price}</span>
									</td>
									<td>
										<a href="javascript:void(0);" onclick="removeFromCollection('${product.pid}')" class="delete">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			</c:if>
		</div>

		<div style="margin-top:50px;">
			<img src="${pageContext.request.contextPath}/image/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a>关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2005-2016 明日综合商城 版权所有
		</div>

	</body>
	<script type="text/javascript">
			function removeFromCollection(pid){
				var flag=confirm("确认删除吗？");
				if(flag){
					$.ajax({
						url:"${pageContext.request.contextPath}/collection",
						type:"post",
						data:{"method":"remove","pid":pid},
						dataType:"text",
						success:function(result){
							if("success"==result){
								alert("删除成功");
								window.location.reload();
							}else{
								alert("删除失败");
							}
						},
						error:function(){
							alert("请求服务失败");
						}
					});
				}
			}
		</script>
</html>
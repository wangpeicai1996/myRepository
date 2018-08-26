<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>订单列表</title>
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
		</style>
		<script type="text/javascript">
			function deleteOrder(oid){
				var flag=confirm("确认删除吗？");
				if(flag){
					$.ajax({
						url:"${pageContext.request.contextPath}/order",
						type:"post",
						data:{"method":"deleteOrder","oid":oid},
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
	</head>

	<body>

			

	<%@include file="/jsp/head.jsp" %>

		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>我的订单</strong>
					<table class="table table-bordered">
						<c:forEach items="${page.data}" var="o">
						<br/>
						<tbody>
							<tr class="warning">
								<th colspan="2">订单编号:${o.oid}</th>
								<th colspan="1">
									<c:if test="${o.state==0}">
									<a href="${pageContext.request.contextPath}/order?method=getById&oid=${o.oid}">去付款</a>
									</c:if>
									<c:if test="${o.state==1}">已付款</c:if>
									<c:if test="${o.state==2}">
									<a href="${pageContext.request.contextPath}/adminOrder?method=updateState&oid=${o.oid}&state=4">确认收货</a>
									</c:if>
									<c:if test="${o.state==4}">已完成</c:if>
								</th>
								<th><a href="javascript:void(0);" onclick="deleteOrder('${o.oid}');">删除订单</a></th>
								<h3>${msg}</h3>
								<th>金额:${o.total}元</th>
							</tr>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
							</tr>
							<c:forEach items="${o.items}" var="oi">
							<tr class="active">
								<td width="60" width="40%">
									<input type="hidden" name="id" value="22">
									<img src="${pageContext.request.contextPath}/${oi.product.pimage}" width="70" height="60">
								</td>
								<td width="30%">
									<a target="_blank" href="${pageContext.request.contextPath}/product?method=getById&pid=${oi.product.pid}" >${oi.product.pname}</a>
								</td>
								<td width="20%">
									￥${oi.product.shop_price}
								</td>
								<td width="10%">
									${oi.count}
								</td>
								<td width="15%">
									<span class="subtotal">￥${oi.subtotal}</span>
								</td>
							</tr>
							<tr>
								<td colspan='4'></td>
							</tr>
							</c:forEach>
						</tbody>
						</c:forEach>
					</table>
				</div>
			</div>
			<%@include file="/jsp/orderPage.jsp" %>
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
			Copyright &copy; 2017-2018 明日综合商城 版权所有
		</div>
	</body>

</html>
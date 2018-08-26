<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
		function deleteUserById(uid){
			var flag=confirm("确认删除吗？");
			if(flag){
				$.ajax({
					url:"${pageContext.request.contextPath}/adminUser",
					type:"post",
					data:{"method":"deleteUserById","uid":uid},
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
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>用户列表</strong>
						</TD>
					</tr>
					<tr>
						
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="18%">
										序号
									</td>
									<td align="center" width="17%">
										用户名称
									</td>
									<td align="center" width="17%">
										真实姓名
									</td>
									<td width="7%" align="center">
										编辑
									</td>
									<td width="7%" align="center">
										删除
									</td>
								</tr>
									<c:forEach var="u" items="${page.data}" varStatus="vs">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="18%">
												${vs.count}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${u.username}
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${u.name}
											</td>
											
											<td align="center" style="HEIGHT: 22px">
												<a href="${ pageContext.request.contextPath }/adminUser?method=editUI&uid=${u.uid}">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<a href="javascript:void(0);" onclick="deleteUserById('${u.uid}')">
													<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
											</td>
										</tr>
									</c:forEach>
							</table>
						</td>
					</tr>
					<tr align="center">
						<td colspan="7">
						<a href="${ pageContext.request.contextPath }/adminUser?method=findAllUser&pageNumber=1">首页</a>|
							<c:if test="${page.pageNumber == 1 }">
								<a href="${ pageContext.request.contextPath }/adminUser?method=findAllUser&pageNumber=1">上一页</a>|
							</c:if>
							<c:if test="${page.pageNumber!= 1 }">
								<a href="${ pageContext.request.contextPath }/adminUser?method=findAllUser&pageNumber=${page.pageNumber-1}">上一页</a>|
							</c:if>
							
							<c:forEach begin="1" end="${page.totalPage}" var="n">
							<c:if test="${page.pageNumber==n}">
								<a href="javascript:void(0)">${n}</a>
							</c:if>
							<c:if test="${page.pageNumber!=n}">
								<a href="${pageContext.request.contextPath}/adminUser?method=findAllUser&pageNumber=${n}">${n}</a>
							</c:if>
							</c:forEach>
							<c:if test="${page.pageNumber==page.totalPage}">
								<a href="${ pageContext.request.contextPath }/adminUser?method=findAllUser&pageNumber=${page.totalPage}">下一页</a>|
							</c:if>
							<c:if test="${page.pageNumber!=page.totalPage}">
								<a href="${ pageContext.request.contextPath }/adminUser?method=findAllUser&pageNumber=${page.pageNumber+1}">下一页</a>|
							</c:if>
							<a href="${ pageContext.request.contextPath }/adminUser?method=findAllUser&pageNumber=${page.totalPage}">尾页</a>|
						</td>
				
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>


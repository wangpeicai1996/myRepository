<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:50px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
				<!-- 判断是否是第一页 -->
				<c:if test="${page.pageNumber==1}">
				<li >
					<a href="${pageContext.request.contextPath}/order?method=findMyOrdersByPage&pageNumber=1&cid=${param.cid}" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				</c:if>
				<c:if test="${page.pageNumber!=1}">
				<li>
					<a href="${pageContext.request.contextPath}/order?method=findMyOrdersByPage&pageNumber=${page.pageNumber-1}&cid=${param.cid}">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				</c:if>
				<!-- 中间的页码 -->
				<c:forEach begin="1" end="${page.totalPage}" var="n">
					<c:if test="${page.pageNumber==n}">
						<li class="active"><a href="javascript:void(0)">${n}</a></li>
					</c:if>
					<c:if test="${page.pageNumber!=n}">
						<li ><a href="${pageContext.request.contextPath}/order?method=findMyOrdersByPage&pageNumber=${n}&cid=${param.cid}">${n}</a></li>
					</c:if>
				</c:forEach>
				
				
				<!-- 是否是最后一页 -->
				<c:if test="${page.pageNumber==page.totalPage}">
				<li>
					<a href="${pageContext.request.contextPath}/order?method=findMyOrdersByPage&pageNumber=${page.totalPage}&cid=${param.cid}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				</c:if>
				<c:if test="${page.pageNumber!=page.totalPage}">
				<li>
					<a href="${pageContext.request.contextPath}/order?method=findMyOrdersByPage&pageNumber=${page.pageNumber+1}&cid=${param.cid}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				</c:if>
			</ul>
		</div>
		<!-- 分页结束=======================        -->
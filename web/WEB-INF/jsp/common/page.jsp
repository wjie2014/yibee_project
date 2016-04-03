<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<input type="hidden" name="pageCount" id="pageCount"
	value="${pageBean.pageCount}" />
<input type="hidden" name="currentPage" id="currentPage"
	value="${pageBean.currentPage}" />
<input type="hidden" name="pageNo" id="pageNo"
	value="${pageBean.currentPage}" />

<div class="module-head" style="height:45px;line-height:45px;">
	<span class="title" style="margin-left: 20px;">共
		${pageBean.pageCount} 页， 总共 ${pageBean.recordCount} 条记录</span>
	<ul class="pagination" style="float: right;margin: 8px 30px;">

		<c:if test="${pageBean.pageCount <= 1 }">
			<li class="disabled"><a href="#">上一页</a></li>
			<li class="active"><a href="#">1</a></li>
			<li class="disabled"><a href="#">下一页</a></li>
		</c:if>
		<c:if test="${pageBean.pageCount >1 }">

			<c:if test="${pageBean.currentPage == 1 }">
				<li class="disabled"><a href="#" onclick="return previous()">上一页</a></li>
			</c:if>
			<c:if test="${pageBean.currentPage > 1 }">
				<li><a href="#" onclick="return previous()">上一页</a></li>
			</c:if>

			<c:set var="pageStart" value="1" />
			<c:set var="pageStop" value="${pageBean.pageCount}" />
			<c:if
				test="${pageBean.currentPage + 2 >= pageBean.pageCount && pageBean.pageCount > 5}">
				<c:set var="pageStart" value="${pageBean.pageCount - 4}" />
			</c:if>
			<c:if
				test="${pageBean.currentPage + 2 < pageBean.pageCount && pageBean.pageCount > 5}">
				<c:set var="pageStart" value="${pageBean.currentPage - 2}" />
				<c:set var="pageStop" value="${pageBean.currentPage + 2}" />
				<c:if test="${pageBean.currentPage < 3}">
					<c:set var="pageStart" value="1" />
					<c:set var="pageStop" value="5" />
				</c:if>
			</c:if>

			<c:forEach var="iNum" begin="${pageStart}" end="${pageStop}" step="1">
				<c:if test="${pageBean.currentPage != iNum}">
					<li><a href="#" onclick="return jumpPage(${iNum})">${iNum}</a></li>
				</c:if>
				<c:if test="${pageBean.currentPage == iNum}">
					<li class="active"><a href="#">${iNum}</a></li>
				</c:if>
			</c:forEach>

			<c:if test="${pageBean.currentPage >= pageBean.pageCount }">
				<li class="disabled"><a href="#" onclick="return false;">下一页</a></li>
			</c:if>
			<c:if test="${pageBean.currentPage < pageBean.pageCount }">
				<li><a href="#" onclick="return next()">下一页</a></li>
			</c:if>
		</c:if>
	</ul>
</div>

<script type="text/javascript">
	var pageCount = "${pageBean.pageCount}"; 
</script>
<script type="text/javascript"
	src="${baseUrlStatic}/js/common/page.js?ver=${version}"></script>

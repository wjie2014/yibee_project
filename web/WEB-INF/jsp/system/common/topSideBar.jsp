
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/yibee/system"> <c:if
					test="${!empty sysUser}">
          		后台管理
			</c:if>
			</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav ">
				<li class="active"><a href="/yibee/system"><span
						class="glyphicon glyphicon-home"></span> 主页</a></li>
				<li ><a href="/yibee/system/user/listUser"
					target="mainframe"><span
						class="glyphicon"></span>用户管理</a></li>
				<li><a href="/yibee/system/article"
					target="mainframe"><span
						class="glyphicon"></span>地图</a></li>
			</ul>
			<!--           	<span style="color: white;float: right;">欢迎您：</span> -->

			<!--           <form class="navbar-form navbar-right"> -->
			<!--             <input type="text" class="form-control" placeholder="Search..."> -->
			<!--           </form> -->
			<div class="pull-right">
				<c:if test="${!empty sysUser}">
					<p style="padding-top: 10px;color: #777;">
						<span class="glyphicon glyphicon-user"></span> 欢迎您， <a href="#"
							class="navbar-link">${sysUser.userName}</a>&nbsp;&nbsp;
						<button id="sysAdminLogout" type="button"
							class="btn btn-danger btn-sm logout" onfocus="this.blur()">退出</button>
					</p>
				</c:if>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${baseUrlStatic}/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="${baseUrlStatic}/js/system/index.js?ver=${version}"></script>
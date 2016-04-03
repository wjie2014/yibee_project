<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="keywords"
	content="java网站,spring mvc,阿里云,建站,java web网站,系统演示,java做的网站" />
<meta name="author" content="jack">
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="shortcut icon" href="${baseUrlStatic}/icon/favicon.png">
<title>后台管理</title>
<!-- Bootstrap core CSS -->
<link href="${baseUrlStatic}/css/system/bootstrap.min.css"
	rel="stylesheet">
<!-- 我们自己的css样式文件放在这里 -->
<link href="${baseUrlStatic}/css/system/dashboard.css" rel="stylesheet">
<script type="text/javascript"
	src="${baseUrlStatic}/js/ckeditor/ckeditor.js"></script>
<style type="text/css">
textarea {
	display: block;
}
</style>
</head>
<body>
	<div id="contentDiv" class="">
		<ul id="contentUl" class="nav nav-tabs" role="tablist">
			<li class="active"><a href="#tab1" data-toggle="tab">用户管理</a></li>
			<li><a href="#tab2" data-toggle="tab">添加用户</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab1">
				<div id="listDiv" class="listDiv">
					
					<form id="courseListForm"
						action="/yibee/system/user/listUser">
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>用户id</th>
										<th>用户名</th>
										<th>创建时间</th>
										<th class="text-center">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="sysUserInfoBo"
										items="${pageBean.recordList}" varStatus="status">
										<tr>
											<td style="width: 20%;"><span class="ellipsis"
												title="test">${sysUserInfoBo.id}</span></td>
											<td style="width: 20%;"><span class="ellipsis"
												title="test">${sysUserInfoBo.userName }</span></td>
											<td style="width: 20%;"><span class="ellipsis"
												title="test">${sysUserInfoBo.createTime}</span></td>
											
											<td class="text-center " style="width:20%;"><a
												id="update_article" href="javascript:"
												onclick="toUpdateUser(${sysUserInfoBo.id})">修改</a> 
												<a
												href="javascript:" 
												onclick="delUserById(${sysUserInfoBo.id})">删除</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
					</form>
				</div>
			</div>
			<div class="tab-pane" id="tab2">
				<div id="listDiv" class="listDiv">
					
					<form id="saveForm" method='post'
						action="/yibee/system/user/saveUser">

						<div class="form-group">
							<input type="text" class="form-control" id="add_name" name="userName"
								placeholder="用户名">
						</div>
						<%--<div class="form-group">--%>
							<%--<select class="form-control" name="userType" id="add_type">--%>
								<%--<option>类型</option>--%>
								<%--<option value="1">普通用户</option>--%>
								<%--<option value="2">超级用户</option>--%>
							<%--</select>--%>
						<%--</div>--%>

						<div class="form-group">
							<input type="password" class="form-control" id="userPass"
								name="userPass" placeholder="密码">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="userPassAgain"
								   name="userPassAgain" placeholder="再次输入密码">
						</div>
						<input name="hi" type="button" value="添加" onClick="saveUser()"
							class="btn btn-primary">
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- 请首先引用jquery，再引用其他js文件 -->
	<!-- Placed at the end of the document so the pages load faster -->
	<%--     <script type="text/javascript" src="${baseUrlStatic}/js/common/jquery-1.11.1.min.js"></script> --%>
	<script type="text/javascript"
		src="${baseUrlStatic}/js/common/jquery.min.js"></script>
	<script type="text/javascript"
		src="${baseUrlStatic}/js/common/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${baseUrlStatic}/js/common/commonFunc.js"></script>
	<script type="text/javascript"
		src="${baseUrlStatic}/js/common/commonFunc.js"></script>
	<script type="text/javascript"
		src="${baseUrlStatic}/js/common/bootstrap-confirmation.js"></script>
	<script type="text/javascript"
		src="${baseUrlStatic}/js/system/userList.js"></script>


</body>
</html>

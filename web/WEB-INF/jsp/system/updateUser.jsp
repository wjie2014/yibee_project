<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>修改用户资料</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="keywords" content="opensource rich wysiwyg text editor jquery bootstrap execCommand html5" />
    <meta name="description" content="This tiny jQuery Bootstrap WYSIWYG plugin turns any DIV into a HTML5 rich text editor" />
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

			

<div class="container">
<div class="tabbable tabs-below">
<div class="tab-pane" id="tab2">
				<div id="listDiv" class="listDiv">
					<div class="sub-header">
						<span id="periodTitle" class="title h2">修改用户资料</span>
					</div>
					<form id="updateForm" method='post'
						action="/yibee/system/user/updateUser">
 
						<div class="form-group">
							<input type="text" class="form-control" id="add_name" name="userName"
								placeholder="用户名" value="${SysUserInfoBo.userName }">
						    <input type="hidden" name="id" value="${SysUserInfoBo.id }">
						</div>
						<%--<div class="form-group">--%>
							<%--<select class="form-control" name="userType" id="add_type">--%>
								<%--<option>类型</option>--%>
								<%--<option value="1">普通用户</option>--%>
								<%--<option value="2">超级用户</option>--%>
							<%--</select>--%>
						<%--</div>--%>

						<div class="form-group">
							<input type="password" class="form-control" id="userOldPass"
								name="userOldPass" placeholder="原始密码">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="userNewPass"
								name="userNewPass" placeholder="新密码">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="userNewPassAgain"
								name="userNewPassAgain" placeholder="请再输一遍新密码">
						</div>

						<input name="hi" type="button" value="保存" onClick="updateUser()"
							class="btn btn-primary">
					</form>
				</div>
			</div>
		</div></div>
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
		src="${baseUrlStatic}/js/system/userList.js"></script>



</html>

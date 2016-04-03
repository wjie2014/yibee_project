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
			<li class="active"><a href="#tab1" data-toggle="tab">每日瑜伽管理</a></li>
			<li><a href="#tab2" data-toggle="tab">添加瑜伽</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab1">
				<div id="listDiv" class="listDiv">
					<form id="courseListForm"
						action="/yibee/system/article/listArticle">
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>文章标题</th>
										<th>分类</th>
										<th>发表时间</th>
										<th>作者</th>
										<th class="text-center">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="sportInformationBo"
										items="${pageBean.recordList}" varStatus="status">
										<tr>
											<td style="width: 20%;"><span class="ellipsis"
												title="test">${sportInformationBo.name}</span></td>
											<td style="width: 20%;"><span class="ellipsis"
												title="test">${sportInformationBo.typeName}</span></td>
											<td style="width: 20%;"><span class="ellipsis"
												title="test">${sportInformationBo.updateTime}</span></td>
											<td style="width: 20%;"><span class="ellipsis"
												title="test"></span></td>
											<td class="text-center " style="width:20%;"><a
												id="update_article" href="javascript:"
												onclick="toUpdateArticle(${sportInformationBo.id})">修改</a> 
												<a
												href="javascript:" 
												onclick="delArticleById(${sportInformationBo.id})">删除</a></td>
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
					<!-- <div class="sub-header">
						<span id="periodTitle" class="title h2">添加瑜伽</span>
					</div> -->
					<form id="saveForm" method='post'
						action="/yibee/system/article/saveArticle">

						<div class="form-group">
							<input type="text" class="form-control" id="add_name" name="name"
								placeholder="文章名称">
						</div>
						<div class="form-group">
							<select class="form-control" name="type" id="add_type">
								<option>类别</option>
								<option value="1">每日瑜伽</option>
								<option value="2">瑜伽视频</option>
							</select>
						</div>

						<div class="form-group">
							<input type="text" class="form-control" id="add_times"
								name="times" placeholder="点击量">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="add_thumb"
								name="thumb" placeholder="缩略图">
							<button>上传</button>
						</div>
						<div class="form-group ">
							<textarea cols="80" id="contentupdate" name="detail"> 

							</textarea>

							<script type="text/javascript"> 
							   //   CKEDITOR.replace('content');   //content为textarea的id
							      CKEDITOR.replace('contentupdate');
							      
							      </script>
						</div>

						<input name="hi" type="button" value="保存" onClick="saveArticale()"
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
		src="${baseUrlStatic}/js/system/articleList.js"></script>


</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>修改文章</title>
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
						<span id="periodTitle" class="title h2">修改文章</span>
					</div>
					<form id="updateForm" method='post'
						action="/yibee/system/article/updateArticle">

						<div class="form-group">
							<input   type="hidden"id="id" name="id"
								 value="${SportInformationBo.id}" >
							<input type="text" class="form-control" id="update_name" name="name"
								placeholder="文章名称" value="${SportInformationBo.name}">
						</div>
						<div class="form-group">
							<select class="form-control" name="type" id="update_type">
								<c:if test=" ${SportInformationBo.typeId == '1'}"><option>类别</option></c:if>
								<option>类别</option>
								<option value="1" <c:if test="${SportInformationBo.typeId == '1'}">selected="selected"</c:if>>每日瑜伽</option>
								<option value="2" <c:if test="${SportInformationBo.typeId == '2'}">selected="selected"</c:if>>瑜伽视频</option>
								
							</select>
						</div>

						<div class="form-group">
							<input type="text" class="form-control" id="update_times"
								name="times" placeholder="点击量" value="${SportInformationBo.times}">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="update_thumb"
								name="thumb" placeholder="缩略图" value="${SportInformationBo.thumb}">
							<button>上传</button>
						</div>
						<div class="form-group ">
							<textarea cols="80" id="detail" name="detail"> 
                               ${SportInformationBo.detail}
							</textarea>

							<script type="text/javascript"> 
							   //   CKEDITOR.replace('content');   //content为textarea的id
							      CKEDITOR.replace('detail');
							      
							      </script>
						</div>

						<input name="hi" type="button" value="保存" onClick="updateArticale()"
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
		src="${baseUrlStatic}/js/system/articleList.js"></script>



</html>

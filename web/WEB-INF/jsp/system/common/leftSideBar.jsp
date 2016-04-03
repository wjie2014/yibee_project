<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<c:if test="${!empty sysUser}">

	<div id="lside" class="col-sm-3 col-md-2 sidebar">
		<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<div class="panel-heading ">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne"> <i class="glyphicon glyphicon-th-list"></i>
							文章管理
						</a>
					</h4>
				</div>
				<div id="collapseOne" class="panel-collapse collapse in">
					<div class="panel-body">
						<ul class="nav nav-list">
							<li class="active"><a
								href="/yibee/system/article/listArticle" target="mainframe"><i
									class="glyphicon glyphicon-list-alt"></i> 每日瑜伽</a></li>
									<li ><a
								href="/yibee/system/article/listArticle" target="mainframe"><i
									class="glyphicon glyphicon-film"></i> 瑜伽视频</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="#collapseTwo"><i class="glyphicon glyphicon-cog"></i>
							系统管理 </a>
					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse">
					<div class="panel-body">
						<ul class="nav nav-list">
							<li><a href="/yibee/system/user/listUser"
								target="mainframe"><i class="glyphicon glyphicon-user"></i> 用户管理</a></li>
							<li><a href="/yibee/system/user/listUser"
								target="mainframe"><i class="glyphicon glyphicon-asterisk"></i> 权限管理</a></li>
						</ul>
					</div>
				</div>
			</div>

		</div>

	</div>
</c:if>

//保存数据提交
function saveUser() {
	var userPass = $("#userPass").val();
	var userPassAgain = $("#userPassAgain").val();

	if(userPass==""){
		alert("密码不能为空");
		return;
	}
	if(userPassAgain==""){
		alert("请再次输入密码");
		return;
	}
	if(userPass!=userPassAgain){
		alert("两次密码输入不一致");
		return;
	}
	document.getElementById("saveForm").submit();
}
// 提交更新，刷新父窗口
function updateUser() {
	var userNewPass = $("#userNewPass").val();
	var userOldPass = $("#userOldPass").val();
	var userNewPassAgain = $("#userNewPassAgain").val();

	if(userOldPass==""){
		alert("原密码不能为空");
		return;
	}
	if(userNewPass==""){
		alert("新密码不能为空");
		return;
	}
	if(userNewPassAgain==""){
		alert("请再次填写新密码");
		return;
	}
	if(userNewPass!=userNewPassAgain){
		alert("两次密码输入不一致");
		return;
	}
	document.getElementById("updateForm").submit();
	setTimeout(function() {
		window.close();
		window.opener.location.reload();
	}, 20);
	/*
	 * var id = encodeURI($("#id").val()); var name =
	 * encodeURI($("#update_name").val()); var thumb =
	 * encodeURI($("#update_thumb").val()); var typeId =
	 * encodeURI($("#update_type").val()); var times =
	 * encodeURI($("#update_times").val()); var detail =
	 * encodeURI($("#detail").val()); var information = { id : id, name : name,
	 * thumb : thumb, typeId : typeId, times : times, detail : "" };
	 * 
	 * $.ajax({ url : '/yibee/system/article/updateArticle', type : "post", data :
	 * "data="+JSON.stringify(encodeURI(information)), success : function(data) //
	 * 回传函数 { //window.close(); // window.opener.location.reload(); } });
	 */
}
// 打开更新页面
function toUpdateUser(id) {
	// $("#update_name").val(id);
	window.open("/yibee/system/user/getUser?id=" + id);
}
// 通过id删除一条记录
function delUserById(id) {
	
	if(confirm("确实要删除本条记录吗?")) {
		$.ajax({
			url : '/yibee/system/user/deleteUser',
			type : "post",
			data : "id=" + id,
			success : function(data) { 
			}
		});
		setTimeout(function() {
			location.reload();
		}, 20);

	} else {
		return;
	}
	
}

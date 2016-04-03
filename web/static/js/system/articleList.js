//保存数据提交
function saveArticale() {
	document.getElementById("saveForm").submit();
}
// 提交更新，刷新父窗口
function updateArticale() {
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
function toUpdateArticle(id) {
	// $("#update_name").val(id);
	window.open("/yibee/system/article/getArticle?id=" + id);
}
// 通过id删除一条记录
function delArticleById(id) {
	if(confirm("确实要删除本条记录吗?")) {
		$.ajax({
			url : '/yibee/system/article/deleteArticle',
			type : "post",
			data : "id=" + id,
			success : function(data) { 
				location.reload();
			}
		});
	} else {
		return;
	}
}

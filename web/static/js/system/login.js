/*=======================================*/
/*	鐧婚檰鍔熻兘鐨刯s                           */
/*	@author coding浜�                       */
/*	2014-9-23 21:28:24                         */
/*=======================================*/

$(document).ready(function(){
	$("#loginBtn").on('click',function(){
		login();
    });
	// 鎸夊洖杞︽墽琛屾煡璇�
	$("#userName").on("keypress", function(){
		var event = arguments.callee.caller.arguments[0]||window.event;//娑堥櫎娴忚鍣ㄥ樊寮�
		if (event.keyCode == 13) {
//			$("#loginForm").submit();
			login();
		} // 鍥炶溅閿殑閿�涓�3
	}); 
	$("#userPass").on("keypress", function(){
		var event = arguments.callee.caller.arguments[0]||window.event;//娑堥櫎娴忚鍣ㄥ樊寮�
		if (event.keyCode == 13) {
			login();
		} // 鍥炶溅閿殑閿�涓�3
	}); 
	$("#authCode").on("keypress", function(){
		var event = arguments.callee.caller.arguments[0]||window.event;//娑堥櫎娴忚鍣ㄥ樊寮�
		if (event.keyCode == 13) {
			login();
		} // 鍥炶溅閿殑閿�涓�3
	}); 
	randomImg();   //鍔犺浇椤甸潰鏃剁敓鎴愰獙璇佺爜
});

function login(){
// 			var datasent = {"userName":"鐜媡est","userPass":"admin","userEmail":"wangyong@qq.com"};
	var btn = $("#loginBtn");
    btn.button('loading');
	var datasent = $("#loginForm").serializeObject();
	params = JSON.stringify(datasent); 
	$.ajax({
		type : "POST",
		url : "/yibee/system/login/loginCheck",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : params,
		async : false,
		success : function(data) {
			if(data.resultCode == 0){   //鐧诲綍鎴愬姛
				window.location.href="/yibee/system";
			}else{
				randomImg();   //鍔犺浇椤甸潰鏃剁敓鎴愰獙璇佺爜
				alert(data.resultMessage);
			}
			btn.button('reset');
		}
	});
}

function randomImg() {
	var random = new Date().getTime();
	$("#randomString").val(random);
	$("#authCodeImg").attr("src", "/yibee/valid/image/getValidImage?type=syslogin&randomString="+random);
}

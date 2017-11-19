$(function() {
	document.onkeydown = keyDowm;
	var cookies = document.cookie.split(";");
	for (var i = 0; i < cookies.length; i++) {
		var cookie = cookies[i];
		var attr = cookie.split("=");
		if (attr[0] == "username") {
			$('#username').val(decodeURI(attr[1]));
		}
	}
})
function keyDowm() {
	if (event.keyCode == 13) {
		login();
	}
}
function login() {
	var url = basePath + '/login/login';
	var username = $('#username').val();
	var password = $('#password').val();
	var autologin = $('#autologin').val();
	var param = 'user.name=' + username + '&&user.password=' + password + '&&autologin=' + autologin;
	if (username == null || username == '' || password == '' || password == null)
		return;
//	$.ajax({
//		url : url,
//		type : 'POST',
//		cache : false,
//		data : param,
//		dataType : 'json',
//		success : function(data) {
//			if (data.res == 'succ') {
//				$('#loginsucc').slideToggle('normal');
//				window.location.href = basePath;
//			}
//		},
//		error : function(XMLHttpRequest, textStatus) {
//			alert(textStatus)
//		}
//	});
	$.post(url, param, function(data) {
		if (data.res == 'succ') {
			//$('#loginsucc').slideToggle('normal');
			window.location.href = basePath;
		}
	}, 'json');
}
function register() {
	var url = basePath + "/login/Register.jsp";
	window.open(url, "FI_Register");
}
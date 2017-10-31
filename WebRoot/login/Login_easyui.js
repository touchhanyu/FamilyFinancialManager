$(function() {
	document.onkeydown = keyDowm;
	$('#loginform').form({
		url : basePath + '/login/login',
		onSubmit : function() {
			if (!$('#loginform').form('validate')) {
				return false;
			}
		},
		success : function() {
			window.location.href = basePath + '/sys/main';
		}
	});
	var cookies = document.cookie.split(";");
	for (var i = 0; i < cookies.length; i++) {
		var cookie = cookies[i];
		var attr = cookie.split("=");
		if (attr[0] == "username") {
			$('#username').textbox("setValue", decodeURI(attr[1]));
		}
	}
})
function keyDowm() {
	if (event.keyCode == 13) {
		login();
	}
}
function login() {
	$('#loginform').submit();
}
function register() {
	var url = basePath + "/login/Register.jsp";
	window.open(url, "FI_Register");
}
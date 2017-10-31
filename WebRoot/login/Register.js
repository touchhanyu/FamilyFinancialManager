$(function() {
	document.onkeydown = keyDowm;
//	var url = basePath + '/login/register';
//	$('#userform').form({
//		url : url,
//		onSubmit : function() {
//		},
//		success : function(data) {
			
//		}
//	});
})
function register() {
//	$('#userform').submit();
	var url = basePath + '/login/register';
	$('#userform').attr('action', url);
	$('#userform').submit();
}
function keyDowm() {
	if (event.keyCode == 13) {// 按下回车键
		register();
	}
}
$(function() {
	var url = basePath + '/sys/menus';
	$('#menuList').menulist({
		url : url,
		onclick : function(obj) {
//			alert(obj)
		}
	});
})
function searchMenu() {
	
}
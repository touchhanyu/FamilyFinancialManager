$(function() {
	$('#menu').tree({
		url : basePath + '/sys/sysMenu',
		loadFilter : function(data) {
			return data;
		},
		checkbox : true,
		onlyLeafCheck : true,
		lines : true
	});
});
function saveData() {
	var menus = $('#menu').tree('getChecked');
	var data = "";
	for (var i = 0; i < menus.length; i++) {
		var menu = menus[i];
		data += menu.id + ",";
	}
	$.ajax({
		url : basePath + '/sys/menuPermission',
		data : 'data=' + data,
		dataType : 'text',
		success : function(data) {
			alert(data);
		},
		error : function(data) {
			alert(data)
		}
	});
}
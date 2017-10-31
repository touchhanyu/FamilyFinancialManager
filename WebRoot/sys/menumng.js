$(function() {
	$('#menu').tree({
		url : basePath + '/sys/sysMenu',
		loadFilter : function(data) {
			return data;
		},
		lines : true,
		dnd : true,
		onContextMenu : function(e, node) {
			e.preventDefault();
			$('#menu').tree('select', node.target);
			$('#mm').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	});
});
function addMenu(level) {
	$('#dialog').dialog({
		title : '新增菜单',
		width : 550,
		height : 400,
		modal : true,
		href : basePath + '/sys/addMenu.jsp',
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				
				addMenuCallBack();
			}
		}, {
			text : '取消',
			iconCls : 'icon-no',
			handler : function() {
				$('#dialog').window('close');
			}
		} ]
	});
}
function editMenu() {
	var node = $('#menu').tree('getSelected');
	$('#menu').tree('beginEdit', node.target);
}
function removeMenu() {
	var node = $('#menu').tree('getSelected');
	$('#menu').tree('remove', node.target);
}
function addMenuCallBack() {
	var node = $('#menu').tree('getSelected');
	if (node) {
		if (level == 0) {
			$('#menu').tree('append', {
				data : [ {
					text : ''
				} ]
			});
		} else if (level == 1) {
			$('#menu').tree('insert', {
				after : node.target,
				data : {
					text : ''
				}
			});
		}
	}
}
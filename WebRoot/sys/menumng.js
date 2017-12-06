$(function() {
	$('#sysmenu').tree({
		url : basePath + '/sys/sysMenu',
		loadFilter : function(data) {
			return data;
		},
		lines : true,
		dnd : true,
		onContextMenu : function(e, node) {
			e.preventDefault();
			$('#sysmenu').tree('select', node.target);
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
	var node = $('#sysmenu').tree('getSelected');
	$('#sysmenu').tree('beginEdit', node.target);
}
function removeMenu() {
	var node = $('#sysmenu').tree('getSelected');
	$('#sysmenu').tree('remove', node.target);
}
function addMenuCallBack() {
	var node = $('#sysmenu').tree('getSelected');
	if (node) {
		if (level == 0) {
			$('sysmenu').tree('append', {
				data : [ {
					text : ''
				} ]
			});
		} else if (level == 1) {
			$('#sysmenu').tree('insert', {
				after : node.target,
				data : {
					text : ''
				}
			});
		}
	}
}
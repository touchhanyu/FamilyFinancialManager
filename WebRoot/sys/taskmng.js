$(function() {
//	document.onkeydown = keyDown;
	$('#info').datagrid({
		url : basePath + '/sys/querySysTask',
		method : 'post',
		title : '系统定时任务',
		fitColumns : true,
		rownumbers : true,
		autoRowHeight : false,
		singleSelect : true,
		nowrap : false,
		toolbar : [ {
			text : '刷新',
			iconCls : 'icon-reload',
			width : 80,
			handler : function() {
				$('#info').propertygrid('load');
			}
		}, {
			text : '保存',
			iconCls : 'icon-save',
			width : 80,
			handler : function() {
				saveData();
			}
		}, {
			text : '新增',
			iconCls : 'icon-add',
			width : 80,
			handler : function() {
				$('#info').datagrid('appendRow', {});
			}
		}, {
			text : '启动任务',
			iconCls : 'icon-go',
			width : 80,
			handler : function() {
				runTask();
			}
		} ],
		columns : [ [ {
			field : 'taskId',
			title : '任务编号',
			align : 'center',
			hidden : true
		}, {
			field : 'taskName',
			title : '任务名称',
			align : 'center',
			width : 80,
			editor : 'textbox'
		}, {
			field : 'taskStartTime',
			title : '任务开始时间',
			align : 'center',
			width : 70,
			editor : {
				type : 'timespinner',
				options : {
					showSeconds : true
				}
			}
		}, {
			field : 'taskTimes',
			title : '任务执行次数',
			align : 'center',
			width : 40,
			editor : {
				type : 'numberbox',
				options : {
					precision : 0
				}
			}
		}, {
			field : 'taskPer',
			title : '任务间隔时间',
			align : 'center',
			width : 40,
			editor : 'numberbox'
		}, {
			field : 'taskPath',
			title : '任务类全路径',
			align : 'center',
			width : 140,
			editor : 'textbox'
		}, {
			field : 'taskStatus',
			title : '任务状态',
			align : 'center',
			width : 40,
			formatter : function(value, row, index) {
				return row['taskStatusValue'];
			},
			editor : {
				type : 'combobox',
				options : {
					url : basePath + '/sys/dictQuery?param=status',
					valueField : 'dictCode',
					textField : 'dictValue',
					panelHeight : 'auto',
					onSelect : function(record) {
						var row = $('#info').datagrid('getSelected');
						row.taskStatusValue = record.dictValue;
					}
				}
			}
		}, {
			field : 'oper',
			title : '操作',
			align : 'center',
			width : 40,
			formatter : function(value, row, index) {
				var html = '<a href="#" onclick="updateRow(' + index + ');">编辑</a>&nbsp';
				html += '<a href="#" onclick="clickRowFn(' + row.taskId + ');">删除</a>';
				return html;
			}
		} ] ],
		pagination : true,
		onClickRow : function(index, row) {
		}
	});
})
function updateRow(index) {
	var row = $('#info').datagrid('getSelected');
	$('#info').datagrid('beginEdit', index);
	var ed = $('#info').datagrid('getEditor', {
		index : index,
		field : 'taskStatus'
	});
	$(ed.target).combobox('setValue', row.taskStatusValue);
}
function clickRowFn(id) {
	$.messager.confirm('警告', '是否删除？', function(r) {
		if (r) {
			var url = basePath + '/sys/saveSysTask?sysTask.taskId=' + id;
			$.post(url, function(data) {
				$.messager.alert('提示', '操作成功！');
				$('#info').datagrid('reload');
			}, 'text');
		}
	});
}
function saveData() {
	var rows = $('#info').datagrid('getRows');
	for (var i = 0; i < rows.length; i++) {
		$('#info').datagrid('endEdit', i);
	}
//	$('#info').datagrid('acceptChanges');
//	$('#info').datagrid('endEdit', lastEditRowIndex);
	var insChanges = $('#info').datagrid('getChanges', 'inserted');
	var updChanges = $('#info').datagrid('getChanges', 'updated');
	var insData, updData;
	if (insChanges.length > 0) {
		for (var i = 0; i < insChanges.length; i++) {// 属性转换为数字类型，防止后台报错
			var row = insChanges[i];
			row.taskTimes = row.taskTimes * 1;
			row.taskPer = row.taskPer * 1;
		}
		var objs = {
			data : insChanges
		}
		insData = JSON.stringify(objs);
	}
	if (updChanges.length > 0) {
		var objs = {
			data : updChanges
		}
		updData = JSON.stringify(objs);
	}
	var url = basePath + '/sys/saveSysTask';
	$.post(url, {
		insData : insData,
		updData : updData
	}, function(data) {
		$.messager.alert('提示', '保存成功');
		$('#info').datagrid('load');
	});
}
function runTask() {
	var row = $('#info').datagrid('getSelected');
	if (row == null || row == undefined || row.taskId == null ||row.taskId == '') {
		$.messager.alert('警告', '请选中要执行的任务');
		return false;
	}
	var url = basePath + '/sys/runTask?sysTask.taskId=' + row.taskId;
	$.messager.progress();// 进度条
	$.post(url, function(data) {
		$.messager.progress('close');
		$.messager.alert('提示', '启动成功');
		$('#info').datagrid('load');
	});
}
/*function keyDown() {
	if (event.keyCode == 27) {
		if (lastEditRowIndex > -1) {
			$('#info').datagrid('cancelEdit', lastEditRowIndex);
		}
	}
}*/
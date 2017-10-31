$(function() {
	$('#dataform').form({
		url : basePath + '/account/',
		onSubmit : function() {
			if (!$('#dataform').form('validate')) {
				return false;
			}
			$.messager.progress();// 进度条
		},
		success : function() {
			$.messager.progress('close');
			$.messager.alert('提示', '保存成功');
			$('#info').datagrid('load');
			$('#dataform').form('clear');
		}
	});
	$('#accountinfo').propertygrid({
		url : basePath + '/account/accountInfo',
		method : 'get',
		showGroup : true,
		scrollbarSize : 0,
		toolbar : [ {
			text : '刷新',
			iconCls : 'icon-reload',
			width : 80,
			handler : function() {
				$('#accountinfo').propertygrid('load');
			}
		}, {
			text : '保存',
			iconCls : 'icon-save',
			width : 80,
			handler : function() {
				saveData();
			}
		} ],
		columns : [ [ {
			field : 'id',
			title : 'ID',
			width : 100,
			hidden : true
		}, {
			field : 'dictCode',
			title : 'DICTCODE',
			width : 100,
			hidden : true
		}, {
			field : 'name',
			title : '账户类别',
			width : 100,
			align : 'center',
		}, {
			field : 'value',
			title : '账户余额（RMB）',
			width : 100,
			align : 'right',
			editor : {
				type : 'numberbox',
				options : {
					precision : 2,
					groupSeparator : ','
				}
			},
			formatter : function(value, row, index) {
				return currencyFormat(value);
			}
		} ] ]
	});
})
function saveData() {
	$.messager.confirm('警告', '是否提交保存？', function(r) {
		if (r) {
			var grid = $('#accountinfo').propertygrid('getChanges');
			if (grid.length == 0)
				return false;
			var submitData = new Array();
			for (var i = 0; i < grid.length; i++) {
				var row = grid[i];
				var id,value;
				if (row.id == null || row.id == '' || row.id == undefined) {
					id = '';
				} else {
					id = row.id;
				}
				if (row.value == null || row.value == '' || row.value == undefined) {
					value = 0;
				} else {
					value = row.value;
				}
				var obj = {
					id : id + '',
					dictCode : row.dictCode,
					value : value + ''
				}
				submitData[i] = obj;
			}
			var objs = {
				data : submitData
			};
			var data = JSON.stringify(objs);
			var url = basePath + '/account/saveAccountInfo';
			$.post(url, {
				data : data
			}, function(data) {
				$.messager.alert('提示', '保存成功');
				$('#accountinfo').propertygrid('load');
			}, "html");
		}
	});
}
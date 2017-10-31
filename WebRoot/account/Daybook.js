$(function() {
	$('#dataform').form({
		url : basePath + '/account/saveDaybook',
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
	initCombobox('#direction', basePath + '/sys/dictQuery?param=moneydirection');
	initCombobox('#type', basePath + '/sys/dictQuery?param=moneytype');
	$('#type').combobox({
		onSelect : function(record) {
			$.ajax({
				url : basePath + '/account/accountChk?data=' + record.dictCode,
				dataType : 'text',
				success : function(data, textStatus) {
					if (data == 'error') {
						$.messager.alert('提示', '请先在资金账户功能中初始化' + record.dictValue + '资金！');
						$('#submitbtn').linkbutton('disable');
					} else {
						$('#submitbtn').linkbutton('enable');
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}
	});
	initCombobox('#purpose', basePath + '/sys/dictQuery?param=moneypurpose');
	initCombobox('#gdirection', basePath + '/sys/dictQuery?param=moneydirection');
	$('#info').datagrid({
		url : basePath + '/account/daybookList',
		method : 'post',
		fitColumns : true,
		rownumbers : true,
		autoRowHeight : false,
		singleSelect : true,
		toolbar : '#tb',
		columns : [ [ {
			field : 'id',
			title : '流水号',
			align : 'center',
			hidden : true
		}, {
			field : 'direction',
			title : '资金方向',
			align : 'center',
			width : 70,
			formatter : function(value, row, index) {
				return row['directionValue'];
			},
			editor : {
				type : 'combobox',
				options : {
					url : basePath + '/sys/dictQuery?param=moneydirection',
					valueField : 'id',
					textField : 'dictValue',
					panelHeight : 'auto'
				}
			}
		}, {
			field : 'amount',
			title : '金额',
			align : 'center',
			width : 70,
			editor : {
				type : 'numberbox'
			},
			formatter : function(value, row, index) {
				return currencyFormat(value);
			}
		}, {
			field : 'type',
			title : '资金方式',
			align : 'center',
			width : 70,
			formatter : function(value, row, index) {
				return row['typeValue'];
			},
			editor : {
				type : 'combobox',
				options : {
					url : basePath + '/sys/dictQuery?param=moneytype',
					valueField : 'id',
					textField : 'dictValue',
					panelHeight : 'auto'
				}
			}
		}, {
			field : 'purpose',
			title : '用途',
			align : 'center',
			width : 70,
			formatter : function(value, row, index) {
				return row['purposeValue'];
			},
			editor : {
				type : 'combobox',
				options : {
					url : basePath + '/sys/dictQuery?param=moneydirection',
					valueField : 'id',
					textField : 'dictValue',
					panelHeight : 'auto'
				}
			}
		}, {
			field : 'remark',
			title : '备注',
			align : 'center',
			width : 180
		}, {
			field : 'tranDate',
			title : '交易日期',
			align : 'center',
			width : 70,
			formatter : function(value, row, index) {
				var date = new Date(value);
				return dateFormat(date);
			}
		}, {
			field : 'tranTime',
			title : '交易时间',
			align : 'center',
			width : 70
		}, {
			field : 'oper',
			title : '操作',
			align : 'center',
			width : 70,
			formatter : function(value, row, index) {
				return '<a href="#" onclick="clickRowFn(\'u\');">修改</a>&nbsp<a href="#" onclick="clickRowFn(\'d\',' + row.id + ');">删除</a>';
			}
		} ] ],
		pagination : true,
		onClickRow : function(index) {
//			var row = $('#info').datagrid('getSelected');
//			clickRowFn(row);
		}
	});
	var date = new Date();
	$('#tranDate').datebox('setValue', date.toLocaleDateString());
})
function queryInfo() {
	$('#info').datagrid('load', {
		gdirection : $('#gdirection').combobox('getValue'),
		date1 : $('#tb_date1').datebox('getValue'),
		date2 : $('#tb_date2').datebox('getValue')
	});
}
function clickRowFn(type, id) {
	if (type == 'u') {
		$.messager.confirm('警告', '是否修改？', function(r) {
			if (r) {
				var row = $('#info').datagrid('getSelected');
				$('#id').val(row.id);
				$('#direction').combobox('setValue', row.direction);
				$('#amount').numberbox('setValue', row.amount);
				$('#type').combobox('setValue', row.type);
				$('#purpose').combobox('setValue', row.purpose);
				$('#tranDate').datebox('setValue', row.tranDate);
				try {
					$('#tranTime').timespinner('setValue', row.tranTime);
				} catch (e) {}
				try {
					$('#remark').textbox('setValue', row.remark);
				} catch (e) {}
				$('#opertype').val(type);
			}
		});
	} else if (type == 'd') {
		$.messager.confirm('警告', '是否删除？', function(r) {
			if (r) {
				var url = basePath + '/account/deleteDaybook?dayBook.id=' + id;
				$.post(url, function(data) {
					$.messager.alert('提示', '操作成功！');
					$('#info').datagrid('reload');
				}, 'text');
			}
		});
	}
}
function saveData() {
	var type = $('#opertype').val();
	var msg = '是否提交保存？';
	if (type == 'u') {
		msg = '是否确认修改？';
	}
	$.messager.confirm('警告', msg, function(r) {
		if (r && type == 'u') {
			$('#dataform').form('submit', {
				url : basePath + '/account/modifyDaybook'
			});
		} else if (r) {
			$('#dataform').submit();
		}
	});
}
function clearData() {
	$.messager.confirm('警告', '是否重置表单数据？', function(r) {
		if (r) {
			$('#opertype').val('');
			$('#dataform').form('clear');
			$('#submitbtn').linkbutton('enable');
		}
	});
}
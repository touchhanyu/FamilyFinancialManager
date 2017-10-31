var detailStatus = -1;
$(function() {
	$('#info').form({
		url : basePath + '/account/monthlyReporting',
		onSubmit : function() {
			if (!$('#info').form('validate')) {
				return false;
			}
			$.messager.progress();// 进度条
		},
		success : function(data) {
			$.messager.progress('close');
			data = data.trim();
			if (data == null || data == '') {
				$('#detailbtn').linkbutton('disable');
				$.messager.alert('提示', '当月还没有账务数据！');
			} else {
				$.messager.alert('提示', '操作成功！');
				$('#reportPic').attr("src", basePath + data);
				detailPropertyInit();
				$('#detailbtn').linkbutton('enable');
			}
		}
	});
	$('#year').combobox({
		url : basePath + '/account/daybookReportingMonth',
		valueField : 'dictCode',
		textField : 'dictValue',
		panelHeight : 'auto'
	});
	initCombobox('#month', basePath + '/sys/dictQuery?param=month');
	$('#detailbtn').linkbutton('disable');
})
function reporting() {
	$('#info').submit();
}
/**
 * 初始化属性表格
 */
function detailPropertyInit() {
	var year = $('#year').combobox('getValue');
	var month = $('#month').combobox('getValue');
	$('#detailinfo').propertygrid({
		url : basePath + '/account/monthlyFinancialDetail?year=' + year + '&month=' + month,
		method : 'get',
		showGroup : true,
		scrollbarSize : 0,
		showFooter : true,
		width : 300,
		toolbar : [ {
			text : '刷新',
			iconCls : 'icon-reload',
			width : 70,
			handler : function() {
				$('#detailinfo').propertygrid('load');
			}
		} ],
		columns : [ [ {
			field : 'name',
			title : '账户类别',
			width : 10,
			align : 'center',
		}, {
			field : 'total',
			title : '金额汇总（RMB）',
			width : 10,
			align : 'right',
			editor : {
				type : 'numberbox',
				options : {
					precision : 2,
					groupSeparator : ','
				}
			}
		} ] ]
	});
}
function showDetailDialog() {
	if (detailStatus < 0) {
		$('#detailDialog').window('open');
	} else {
		$('#detailDialog').window('close');
	}
	detailStatus = detailStatus * -1;
}
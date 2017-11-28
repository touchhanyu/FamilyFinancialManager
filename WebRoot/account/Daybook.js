var dt = $('#datatable').DataTable({
	autoWidth : true,
	info : true,
	lengthChange : true,
	ordering : true,
	paging : true,
	processing : true,
	serverSide : false,
	deferRender : true,
	pagingType : 'full',
	language : {
		lengthMenu : '显示 _MENU_ 条记录',
		search : '',
		searchPlaceholder : '搜 索...',
		processing : '正在处理中。。。',
		loadingRecords : '',
		infoEmpty : '暂无记录',
		info : '第_PAGE_页，共_PAGES_页',
		paginate : {
			first : '首页', //&laquo;
			previous : '上一页',
			next : '下一页',
			last : '末页' //&raquo;
		}
	},
	columns : [
		{ data : 'id', title : 'ID', searchable : false, orderable : false, visible : false },
		{	data : null,
			title : '<button type="button" class="btn btn-default btn-sm checkbox-toggle"><i class="fa fa-square-o"><i/></button>',
			orderable : false,
			render : function(date, type, full, meta) { return '<input type="checkbox" value=""/>'; }
		},
		{ data : 'direction', title : '资金方向', visible : false },
		{ data : 'directionValue', title : '资金方向' },
		{ data : 'amount', title : '金额', type : 'num-fmt', orderable : true, render : function(data, type, full, meta) { return currencyFormat(data); } },
		{ data : 'type', title : '资金方式', visible : false },
		{ data : 'typeValue', title : '资金方式' },
		{ data : 'purpose', title : '用途', visible : false },
		{ data : 'purposeValue', title : '用途' },
		{ data : 'remark', title : '备注', orderable : false },
		{ data : 'tranDate', title : '交易日期', type : 'date', render : function(data, type, full, meta) { return dateFormat(data); } },
		{ data : null, title : '交易时间', orderable : false, render : function(data, type, full, meta) { return '' } },
		{ data : 'oper', title : '操作', orderable : false, render : function(data, type, full, meta) {
			return '<div class="tools"><i class="fa fa-edit" onclick="void(0);"></i>&nbsp;&nbsp;&nbsp;<i class="fa fa-trash-o"></i></div>';
		} } ],
	ajax : {
		url : basePath + '/account/daybookList',
		type : 'POST',
		data : {
			date1 : '2017-11-01',
			date2 : '2017-11-20',
			page : 1,
			rows : 10
		},
		dataType : 'json',
		dataSrc : 'rows'
	},
	initComplete : function() {
		/* 复选框 */
		$('table input[type="checkbox"]').iCheck({
			checkboxClass : 'icheckbox_flat-blue',
			radioClass : 'iradio_flat-blue'
		});
		$(".checkbox-toggle").click(function() {
			var clicks = $(this).data('clicks');
			if (clicks) {
				$(".table input[type='checkbox']").iCheck("uncheck");
				$(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
			} else {
				$(".table input[type='checkbox']").iCheck("check");
				$(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
			}
		});
	}
});
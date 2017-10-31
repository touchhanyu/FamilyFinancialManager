$(function() {
	$('#stockname').autocomplete(basePath + '/stock/stock',{
		width: 250,
		delay: 400,
		dataType: 'json',
		parse: function(data) {
			return $.map(data, function(row) {
				return {
					data: row,
					value: row.gname,
					result: row.gname
				}
			});
		},
		formatItem: function(row, i, max) {
			var html = "<table width='250px'><tr><td align='left' height='140%'><font style='font-size:small'>";
			html += row.gname + "</font></td><td align='right'><font style='font-size:x-small;color:green'>";
			html += row.gid + "<font/>&nbsp;</td></tr><td><font style='font-size:x-small'>" + row.pinyin;
			html += "</font></td></tr></table>";
			return html;
		},
		formatMatch: function(row, i, max){
			return "<font color=red>" + row.name + "</font>";
		}
	});
});
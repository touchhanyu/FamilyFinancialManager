$(function() {
	$('#calendar').datepicker();
	$('.daterange').daterangepicker({
		ranges : {
			'Today' : [ moment(), moment() ],
			'Yesterday' : [ moment().subtract(1, 'days'), moment().subtract(1, 'days') ],
			'Last 7 Days' : [ moment().subtract(6, 'days'), moment() ],
			'Last 30 Days' : [ moment().subtract(29, 'days'), moment() ],
			'This Month' : [ moment().startOf('month'), moment().endOf('month') ],
			'Last Month' : [ moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month') ]
		},
		startDate : moment().subtract(29, 'days'),
		endDate : moment()
	}, function(start, end) {
		window.alert('You chose: ' + start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
	});
	var visitorsData = {
		US : 398, // USA
		SA : 400, // Saudi Arabia
		CA : 1000, // Canada
		DE : 500, // Germany
		FR : 760, // France
		CN : 300, // China
		AU : 700, // Australia
		BR : 600, // Brazil
		IN : 800, // India
		GB : 320, // Great Britain
		RU : 3000 // Russia
	};
	// World map by jvectormap
	$('#world-map').vectorMap({
		map : 'world_mill_en',
		backgroundColor : 'transparent',
		regionStyle : {
			initial : {
				fill : '#e4e4e4',
				'fill-opacity' : 1,
				stroke : 'none',
				'stroke-width' : 0,
				'stroke-opacity' : 1
			}
		},
		series : {
			regions : [
				{
					values : visitorsData,
					scale : [ '#92c1dc', '#ebf4f9' ],
					normalizeFunction : 'polynomial'
				}
			]
		},
		onRegionLabelShow : function(e, el, code) {
			if (typeof visitorsData[code] != 'undefined')
				el.html(el.html() + ': ' + visitorsData[code] + ' new visitors');
		}
	});
	$('#menu').adminmenu({
		header : '菜单',
		url : basePath + '/sys/menus',
		onclick : function(obj) {
			var href = $(obj).attr("data");
			if (href != null && href != undefined) {

			}
		}
	});
})
function searchMenu() {
}
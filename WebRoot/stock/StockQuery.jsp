<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-autocomplete/lib/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-autocomplete/jquery.autocomplete.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/jquery-autocomplete/jquery.autocomplete.css">
		<script type="text/javascript" src="<%=request.getContextPath() %>/stock/StockQuery.js"></script>
		<script type="text/javascript">
			var basePath = '<%=request.getContextPath()%>';
		</script>
	</head>
	<body>
		<table>
			<tr>
				<td>股票名称</td>
				<td>
					<input id="stockname" type="text" style="width: 250"/>
				</td>
				<td>当前价格</td>
				<td>
					<input id="price" type="text"/>
				</td>
			</tr>
		</table>
	</body>
</html>
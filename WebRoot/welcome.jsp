<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
	var basePath = '${pageContext.request.contextPath}';
	var client = browserVersion();
	if (client == 'Android' || client == 'iPhone' || client == 'iPad' || client == 'WeChat') {
		window.location.href = basePath + '/Login/LoginMobile.jsp';
	} else if (client == 'IE' || client == 'Edge' || client == 'Chrome') {
		window.location.href = basePath + '/Login/Login.jsp';
	}
</script>
</head>
<body></body>
</html>
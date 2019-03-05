<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		
	%>
	
	${param.username}<br>
	${param}<br>
	${paramValues}<br>
	${paramValues.hobby[0]}<br>
	
	<hr>
	${header.referer }<br><hr>
	${headerValues["user-agent"][0] }<br>
</body>
</html>
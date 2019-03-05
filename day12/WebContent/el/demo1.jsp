<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>获取域中数据--简单 数据</title>
</head>
<body>
	<%
		request.setAttribute("rkey", "rvalue");
		session.setAttribute("skey", "svalue");
		application.setAttribute("akey", "avalue");
	%>
	
	获取request中的数据：<br>
	request.getxxx:    <%= request.getAttribute("rkey") %>  <br>
	el:
	${requestScope.rkey} <br>
	${requestScope["rkey"]}
	<hr>
</body>
</html>
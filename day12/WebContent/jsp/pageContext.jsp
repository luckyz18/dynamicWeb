<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pageContext 操作其他域对象</title>
</head>
<body>
	<% 
		pageContext.setAttribute("rkey", "rvalue",pageContext.REQUEST_SCOPE );
	%>
	
	<%="request.getAttribute('rkey'):"+request.getAttribute("rkey") %>
	
	<%=
		pageContext.findAttribute("rkey")
	%>
</body>
</html>
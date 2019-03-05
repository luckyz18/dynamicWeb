<%@page import="com.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>javabean</title>
</head>
<body>
	<%
		User u = new User();
		u.setId("1");
		u.setName("tom");
		request.setAttribute("user", u);
	%>
	
	<%=((User)request.getAttribute("user")).getName() %>
	
	${user.name}  
	<!--
		错误的：${user.username}
		
	 -->
	
</body>
</html>
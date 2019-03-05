<%@page import="com.domain.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>执行运算</title>
</head>
<body>
	<%
		request.setAttribute("i", 1);
		request.setAttribute("j", "2");
		request.setAttribute("k", "q");
		
		List l = null;
		request.setAttribute("list", l);
		
		List l_ = new ArrayList();
		l_.add("222");
		request.setAttribute("list_", l_);
		
		User u = new User();
		request.setAttribute("user", u);  
		
		
		
		
		
	%>
	${i+j}<br>
	<%--  ${i+k}<br>    错误   --%>
	
	${empty list}<br>
	${empty list_ }<br>
	
	${empty user}<br>   
	<!-- 	false -->
	
	
	  
</body>
</html>
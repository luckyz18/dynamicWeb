<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="u" class="com.domain.User"></jsp:useBean>
	<jsp:setProperty property="name" name="u"/>
	<jsp:setProperty property="password" name="u"/>
	
	<jsp:getProperty property="name" name="u"/>
	<jsp:getProperty property="password" name="u"/>
</body>
</html>
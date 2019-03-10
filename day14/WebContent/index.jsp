<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<a href="${pageContext.request.contextPath }/findAll">查询所有商品</a>
	<a href="${pageContext.request.contextPath }/add.jsp">添加商品</a>
	<a href="${pageContext.request.contextPath }/showProductByPage?currentPage=1">分页展示商品</a>
</body>
</html>
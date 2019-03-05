<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1px" align="center">
		<tr>
			<td>id</td>
			<td>pname</td>
			<td>price</td>
			<td>pdesc</td>
		</tr>
		
		<c:forEach items="${list}" var="i">
			<!-- JavaBean -->
			<tr>
				<td>${i.id }</td>
				<td>${i.pname}</td>
				<td>${i.price}</td>
				<td>${i.pdesc}</td>
			</tr>
		</c:forEach>
		
	</table>
</body>
</html>